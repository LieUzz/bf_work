package com.example.petapp.repository;

import com.example.petapp.domain.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoDemoRepo extends MongoRepository<Owner, String> {
//    List<Owner> findGameByTitle(String title);
//
//    List<Owner> findGameByTitleIgnoreCase(String title);
//
//    @Query(value = "{category: ?0}")
//    List<Owner> findGamesByCategory(String category);

}