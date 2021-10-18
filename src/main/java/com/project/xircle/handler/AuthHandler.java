package com.project.xircle.handler;

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
