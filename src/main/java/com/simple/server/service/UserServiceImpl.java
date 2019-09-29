package com.simple.server.service;

import com.simple.server.enums.Color;
import com.simple.server.model.Article;
import com.simple.server.model.User;
import com.simple.server.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User add(final User user) {

        return userRepository.save(user);
    }

    @Override
    public User get(final Long id) {

        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public User addArticle(final Long userId, final Article article) {

        final User user = userRepository.findById(userId).orElseThrow();
        user.getArticles().add(article);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsersWithAgeRestriction(final int age) {

        return userRepository.findByAgeAfter(age);
    }

    @Override
    public List<User> getUsersByArticleColor(final Color color) {

        return userRepository.findUsersByArticles_color(color);
    }

    @Override
    public List<User> getUsersByMinArticleNumber(final long number) {

        return userRepository.findByArticlesCount(number);
    }

}
