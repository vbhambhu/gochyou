package com.gochyou.services;


import com.gochyou.models.FacebookUser;
import com.gochyou.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean saveUser(FacebookUser facebookUser) {



        if(isUserExists(facebookUser.getId()) == 0){
            createUser(facebookUser);

            //HttpSession.
        }

        httpSession.setAttribute("isLoggedIn", true);

        return true;

    }

    public void createUser(FacebookUser facebookUser) {

        DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateobj = new Date();

        String gender = facebookUser.getGender().equals("male") ? "m" : "f";

        String SQL = "INSERT INTO users (username,first_name,last_name,fbid,email,gender,created_at) values (?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, new Object[] {
                facebookUser.getFirst_name(),
                facebookUser.getFirst_name(),
                facebookUser.getLast_name(),
                facebookUser.getId(),
                facebookUser.getEmail(),
                gender,
                date_format.format(dateobj) });
    }


    public int isUserExists(Long fbId) {

        String SQL = "SELECT count(*) FROM users WHERE fbid = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[] { fbId }, Integer.class);

    }


}
