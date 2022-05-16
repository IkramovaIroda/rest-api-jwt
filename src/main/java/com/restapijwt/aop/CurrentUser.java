package com.restapijwt.aop;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.TYPE})
@AuthenticationPrincipal
public @interface CurrentUser {
    //logika
    //logika yozsa bo'ladi
    //@AuthenticationPrincipal pasdagi kod bn bir xil ish qiladi
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
}
