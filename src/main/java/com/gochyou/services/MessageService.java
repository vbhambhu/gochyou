package com.gochyou.services;


import com.gochyou.models.Conversation;
import com.gochyou.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Message> getMessagesByUserId(int userId) {

        String sqlString = "SELECT * FROM messages WHERE to_id = ?";

        List<Message> messages = jdbcTemplate.query(sqlString,new Object[] { userId }, new BeanPropertyRowMapper<Message>(Message.class));

        return messages;

    }

    public List<Message> getMessages() {

        String sqlString = "SELECT * FROM messages";

        List<Message> messages = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<Message>(Message.class));

        return messages;


    }

    public List<Conversation> getConversationList(int userId) {



//        String sql = "SELECT m.message,m.from_id,m.created_at,u.first_name FROM messages m " +
//                "INNER JOIN users u on u.id = m.from_id  WHERE m.deleted_from <> ? " +
//                "AND m.conversation_id=? LIMIT 50";

        String  sql = "SELECT c.id,IF(m.from_id = ?, m.to_id, m.from_id ) as with_id, m.message as lastmsg,m.is_read, m.created_at, " +
                "u.id as user_id,u.first_name, u.profile_img as image, u.username,u.gender " +
                "FROM conversations c " +
                "INNER JOIN messages m ON m.id = c.last_message_id " +
                "INNER JOIN users u ON u.id = IF (m.from_id = ?, m.to_id, m.from_id ) " +
                "WHERE c.last_message_id <> 0 " +
                "AND (c.user_id1=? OR c.user_id2=?) " +
                "AND m.deleted_from <> ? LIMIT 10";


        List<Conversation> conversations = jdbcTemplate.query(sql, new Object[] { userId,userId,userId,userId,userId },new BeanPropertyRowMapper<Conversation>(Conversation.class));

        return conversations;


    }
}
