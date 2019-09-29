package com.simple.server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleDTO {

    private String text;
    private String color;
    private Long userId;
}
