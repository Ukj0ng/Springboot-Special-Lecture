package com.uk.person.config;

import com.uk.person.domain.dto.CommonDto;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

// @Controller, @RestController, @Configuration, @Component -> 메모리에 올라가는 것
// @Configuration은 설정 전에, 그게 아닌 모든 거는 Component로
@Component
@Aspect
public class BindingAdvice {
    // 함쉬: 앞 뒤 -> @Around
    // 함수: 앞 (username이 안들어왔으면 강제로 넣어주고 실행) -> @Before
    // 함수: 뒤 (응답만 관리) -> @After

    @Before("execution(* com.uk.person.web..*Controller.*(..))")
    public void beforeCheck() throws Throwable {
        System.out.println("beforeCheck");
    }

    @After("execution(* com.uk.person.web..*Controller.*(..))")
    public void afterCheck() throws Throwable {
        System.out.println("afterCheck");
    }
    // proceedingJoinPoint는 함수들임 ex, update, save 등
    // 프록시 메모리에서 매개변수를 확인해서 정상이면 함수를 실행하고, 비정상이면 validCheck함수를 실행함
    @Around("execution(* com.uk.person.web..*Controller.*(..))")
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String type = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        String method = proceedingJoinPoint.getSignature().getName();

        System.out.println(type + " " + method + " " + proceedingJoinPoint.getArgs());

        Object[] args = proceedingJoinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
        }

        return proceedingJoinPoint.proceed(); // 함수의 스택을 실행해라
    }
}
