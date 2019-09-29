package com.simple.server.repository;

import com.simple.server.enums.Color;
import com.simple.server.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByAgeAfter(final int age);

    List<User> findUsersByArticles_color(final Color color);

    @Query("select u from User u where :numb < (select count(a) from Article a where a.user.id=u.id)")
    List<User> findByArticlesCount(@Param("numb") final long number);
}
