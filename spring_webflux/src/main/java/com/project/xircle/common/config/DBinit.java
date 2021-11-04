package com.project.xircle.common.config;

import com.project.xircle.model.User;
import com.project.xircle.repository.UserRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Configuration
public class DBinit {

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("sql/schema.sql")));
        return initializer;
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {

        return (args) -> {
            repository.saveAll(
                    Arrays.asList(
                            User.builder()
                                    .age(22).address("서울시 관악구").adjective("열정적인")
                                    .displayName("hyun").email("aaa@naver.com").gender("남")
                                    .isGraduate(true).introduction("안녕").isLocationPublic(true)
                                    .job("개발").latitude(37.0).longitude(127.0)
                                    .phoneNumber("010-1234-1234").isPublic(true)
                                    .profileImgSrc("http://localhost:9000/static/user1.png")
                                    .password("aaa").resume("스프링").university("고려대")
                                    .workPlace("").createdAt(LocalDateTime.now()).build(),
                            User.builder()
                                    .age(25).address("서울시 서초구").adjective("사냥한")
                                    .displayName("kim").email("bbb@naver.com").gender("여")
                                    .isGraduate(true).introduction("안녕").isLocationPublic(true)
                                    .job("개발").latitude(37.0).longitude(127.0)
                                    .phoneNumber("010-4321-4321").isPublic(true)
                                    .profileImgSrc("http://localhost:9000/static/user2.png")
                                    .password("bbb").resume("스프링").university("연세대")
                                    .workPlace("").createdAt(LocalDateTime.now()).build())
                    ).blockLast(Duration.ofSeconds(10));

            repository.findAll().doOnNext(customer -> {
                log.info(customer.toString());
            }).blockLast(Duration.ofSeconds(10));
        };
    }
}
