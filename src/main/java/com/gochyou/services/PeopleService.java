package com.gochyou.services;

import com.gochyou.models.Conversation;
import com.gochyou.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<People> searchPeople() {

        String sql= "SELECT * from users";

        List<People> people = jdbcTemplate.query(sql, new BeanPropertyRowMapper<People>(People.class));

        return people;


    }
}
