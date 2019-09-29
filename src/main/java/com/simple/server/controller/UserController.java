package com.simple.server.controller;

import com.simple.server.dto.ArticleDTO;
import com.simple.server.dto.UserDTO;
import com.simple.server.enums.Color;
import com.simple.server.model.Article;
import com.simple.server.model.User;

import com.simple.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/server")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User add(@RequestBody final UserDTO userDTO) {

        return userService.add(mapToEntity(userDTO));
    }

    @PostMapping("/users/article")
    @ResponseStatus(HttpStatus.CREATED)
    public User addArticle(@RequestBody final ArticleDTO articleDTO) {

        return userService.addArticle(articleDTO.getUserId(), mapToEntity(articleDTO));
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable(name = "id") final Long id) {

        return userService.get(id);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {

        return userService.getAll();
    }

    @GetMapping("/users/age/restriction")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersWithAgeRestriction(@RequestParam final int minAge) {

        return userService.getUsersWithAgeRestriction(minAge);
    }

    @GetMapping("/users/article/color")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByArticleColor(@RequestParam final String color) {

        return userService.getUsersByArticleColor(mapStringToColor(color));
    }

    @GetMapping("/users/article/number")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByArticleCount(@RequestParam final long number) {

        return userService.getUsersByMinArticleNumber(number);
    }



    private User mapToEntity(final UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getAge());
    }

    private Article mapToEntity(final ArticleDTO articleDTO) {
        return new Article(articleDTO.getText(), mapStringToColor(articleDTO.getColor()));
    }

    private Color mapStringToColor(final String color) {
        return Color.valueOf(color.trim().toUpperCase());
    }
}
