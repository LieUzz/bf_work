package com.example.w3d5.mapper;

import com.example.w3d5.domain.Pastry;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PastryRowMapper implements RowMapper<Pastry> {

    @Override
    public Pastry mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pastry pastry = new Pastry();
        pastry.setId(rs.getInt("id"));
        pastry.setName(rs.getString("name"));
        pastry.setPrice(rs.getFloat("price"));
        return pastry;
    }

}
