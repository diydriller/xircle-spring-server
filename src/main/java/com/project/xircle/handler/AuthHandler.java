package com.project.xircle.handler;

import com.project.xircle.dto.auth.CheckEmailRequestDto;
import com.project.xircle.repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.stream.Collectors;

//
//@Component
//@AllArgsConstructor
//public class AuthHandler {
//
//    private final AuthRepository authRepository;
//    private final Validator validator;
//
//    public Mono<ServerResponse> checkEmail(ServerRequest request){
//        return request.bodyToMono(CheckEmailRequestDto.class)
//                .doOnNext(this::validate)
//                .flatMap(ServerResponse.status(HttpStatus.ACCEPTED)::bodyValue);
//    }
//
//    private void validate(CheckEmailRequestDto emailRequestDto) throws RuntimeException {
//        var constraintViolations=validator.validate(emailRequestDto);
//        if(constraintViolations.size()>0) {
//            var errorMsg=constraintViolations
//                    .stream()
//                    .map(ConstraintViolation::getMessage)
//                    .sorted()
//                    .collect(Collectors.joining(" , "));
//            throw new RuntimeException(errorMsg);
//        }
//    }
//
//
//}
