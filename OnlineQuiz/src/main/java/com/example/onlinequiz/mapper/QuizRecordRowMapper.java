package com.example.onlinequiz.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.onlinequiz.domain.QuizRecord;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRecordRowMapper implements RowMapper<QuizRecord> {

    @Override
    public QuizRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizRecord quizRecord = new QuizRecord();
        quizRecord.setId(rs.getInt("id"));
        quizRecord.setUid(rs.getInt("uid"));
        quizRecord.setQid(rs.getInt("qid"));
        quizRecord.setQid(rs.getInt("score"));
        quizRecord.setEndtime(rs.getString("starttime"));
        quizRecord.setEndtime(rs.getString("endtime"));

        return quizRecord;
    }

}
