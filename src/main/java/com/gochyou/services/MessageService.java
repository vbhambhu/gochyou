package com.gochyou.services;


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
}
