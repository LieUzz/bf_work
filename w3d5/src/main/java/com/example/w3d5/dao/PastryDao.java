package com.example.w3d5.dao;

import com.example.w3d5.domain.Pastry;
import com.example.w3d5.mapper.PastryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PastryDao {

    JdbcTemplate jdbcTemplate;
    PastryRowMapper pastryRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PastryDao(JdbcTemplate jdbcTemplate, PastryRowMapper pastryRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.pastryRowMapper = pastryRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Integer getPastryCount(){
        String query = "SELECT COUNT(*) FROM pastry";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public void createNewPastry(String name, Float price) {
        String query = "INSERT INTO pastry (name, price) values (?, ?)";
        jdbcTemplate.update(query, name, price);
    }

    public List<Pastry> getAllPastry(){
        String query = "SELECT * FROM pastry";
        List<Pastry> pastries =  jdbcTemplate.query(query, pastryRowMapper);
        return pastries;
    }

    public Pastry getPastryById(Integer id){
        String query = "SELECT * FROM pastry WHERE id = ?";
        List<Pastry> pastries =  jdbcTemplate.query(query, pastryRowMapper, id);
        return pastries.size() == 0 ? null : pastries.get(0);
    }

    public void deletePastryById(Integer id){
        String query = "DELETE FROM pastry WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public void updatePastryById(Integer id, String name, Float price){
        String query = "UPDATE pastry SET name = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(query, name, price, id);
    }



}
