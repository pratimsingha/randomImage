package com.random.randomImages.repository;

import com.random.randomImages.model.RandomLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RandomImageDao extends JpaRepository<RandomLinks, Integer> {

    @Query(value = "SELECT a FROM RandomLinks a ")
    List<RandomLinks> getImages();
    @Query(value = "Select a from RandomLinks a where a.id = :id")
    RandomLinks getLink(@Param("id") Integer id);
}
