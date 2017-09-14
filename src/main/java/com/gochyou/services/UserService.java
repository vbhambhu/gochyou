package com.gochyou.services;


import com.gochyou.models.FacebookUser;
import com.gochyou.models.Message;
import com.gochyou.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean saveUser(FacebookUser facebookUser) {

        User user = getUserByFBID(facebookUser.getId());

        if(user == null){ // if new user
            int id = createUser(facebookUser);
            httpSession.setAttribute("userId", id);
            user = new User();
            user.setFirstNname(facebookUser.getFirst_name());
            user.setLastName(facebookUser.getLast_name());
            user.setEmail(facebookUser.getEmail());
        } else{ // if returning user
            httpSession.setAttribute("userId", user.getId());
        }

        httpSession.setAttribute("isLoggedIn", true);


//        if(user.getEmail() == null || user.getEmail().equals("") || user.getUsername() == null || user.getUsername().equals("")){
//            httpSession.setAttribute("completeProfile", true);
//        }


        //Add logs
        createLoginLog(user);


        return true;

    }

    public void createLoginLog(User user) {

        DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateobj = new Date();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();



        String SQL = "INSERT INTO logs (user_id,type,ip_address,created_at) values (?,?,?,?)";

        jdbcTemplate.update(SQL, new Object[] {
                user.getId(),
                "login",
                request.getRemoteAddr(),
                date_format.format(dateobj) });

    }

    public int createUser(FacebookUser facebookUser) {

        DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateobj = new Date();

        String gender = facebookUser.getGender().equals("male") ? "m" : "f";

        String SQL = "INSERT INTO users (username,first_name,last_name,fbid,email,gender,created_at) values (?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, new Object[] {
                createUserName(facebookUser.getFirst_name(), facebookUser.getLast_name(),facebookUser.getEmail()),
                facebookUser.getFirst_name(),
                facebookUser.getLast_name(),
                facebookUser.getId(),
                facebookUser.getEmail(),
                gender,
                date_format.format(dateobj) });

        //return last created id
        int lastInsertId =  jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        //create profile table.
        jdbcTemplate.update("INSERT INTO profiles (user_id) values (?)", new Object[] { lastInsertId});

        return lastInsertId;
    }


    public User getUserByFBID(Long fbId) {

        User user = null;

        String SQL = "SELECT id FROM users WHERE fbid = ? LIMIT 1";
        try{
            user = jdbcTemplate.queryForObject(SQL, new Object[] { fbId },  new BeanPropertyRowMapper<User>(User.class) );
        } catch(EmptyResultDataAccessException ex){
            return user;
        }

        return user;

    }




    public String createUserName(String fn, String ln, String em) {

        String username = "";

        Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
        Matcher match= pt.matcher(fn);
        while(match.find()){
            String s= match.group();
            fn= fn.replaceAll("\\"+s, "");
        }

        Matcher matchln = pt.matcher(ln);
        while(matchln.find()){
            String s= matchln.group();
            ln = ln.replaceAll("\\"+s, "");
        }

        if(em.length() > 2 && (fn.length() == 0 || ln.length() < 2)){
            System.out.println("ddd");
            int index = em.indexOf('@');
            username = em.substring(0,index);
        } else if(fn.length() > 0 && ln.length() >= 2){
            username = String.valueOf(String.valueOf(fn.charAt(0)) + ln.split(" ")[0]).toLowerCase();
        } else{
            username = UUID.randomUUID().toString().substring(0, 8);
        }
        //check in db
        int totalUsernames =  jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE username LIKE ?", new Object[] { username+"%" }, Integer.class);

        if(totalUsernames > 0){
            username = username+(totalUsernames+1);
        }
        return username;

    }

}
