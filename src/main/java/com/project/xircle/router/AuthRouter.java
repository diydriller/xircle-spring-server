package com.project.xircle.router;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

//@Configuration
//public class AuthRouter {
//    @Bean
//    public RouterFunction<ServerResponse> authRoute(AuthHandler authHandler){
//        return route()
//                .POST("/check/emails",request ->authHandler.checkEmail(request))
//                .build();
//    }
//}
