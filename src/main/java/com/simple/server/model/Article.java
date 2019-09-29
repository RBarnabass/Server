package com.simple.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simple.server.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "articles")
public class Article extends BaseEntity {

    private String text;

    @Enumerated(EnumType.ORDINAL)
    private Color color;

    @JsonIgnore
    @ManyToOne
    private User user;

    public Article(String text, Color color) {
        this.text = text;
        this.color = color;
    }
}
