package com.simple.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Starter {

    private static final int INITIAL_DELAY = 3000;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Starter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(initialDelay = INITIAL_DELAY, fixedDelay = Long.MAX_VALUE)
    public void start() {

        jdbcTemplate.execute("INSERT INTO users(id, name, age) VALUES (1, 'Roman', '30')");
        jdbcTemplate.execute("INSERT INTO users(id, name, age) VALUES (2, 'Kate', '20')");
        jdbcTemplate.execute("INSERT INTO users(id, name, age) VALUES (3, 'Anna', '18')");
        jdbcTemplate.execute("INSERT INTO users(id, name, age) VALUES (4, 'Alice', '21')");
        jdbcTemplate.execute("INSERT INTO users(id, name, age) VALUES (5, 'Tina', '24')");

        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (1, 'Jacoco', 0, 1)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (2, 'Lombok', 1, 1)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (3, 'MySQL', 2, 1)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (4, 'Spring', 3, 1)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (5, 'Hibernate', 4, 1)");

        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (6, 'JDBC', 5, 2)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (7, 'Servlet', 0, 2)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (8, 'JSP', 1, 2)");

        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (9, 'Oracle', 2, 3)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (10, 'PostgreSQL', 3, 3)");

        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (11, 'RabbitMQ', 4, 4)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (12, 'Apache Kafka', 5, 4)");

        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (13, 'JUnit', 0, 5)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (14, 'Mockito', 1, 5)");
        jdbcTemplate.execute("INSERT INTO articles(id, text, color, user_id) VALUES (15, 'Harmcrest', 2, 5)");
    }
}
