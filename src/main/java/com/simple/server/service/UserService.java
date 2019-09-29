package com.simple.server.service;

import com.simple.server.enums.Color;
import com.simple.server.model.Article;
import com.simple.server.model.User;

import java.util.List;

public interface UserService {

    User add(final User user);

    User get(final Long id);

    List<User> getAll();

    User addArticle(final Long userId, final Article article);

    List<User> getUsersWithAgeRestriction(final int age);

    List<User> getUsersByArticleColor(final Color color);

    List<User> getUsersByMinArticleNumber(final long number);
}
