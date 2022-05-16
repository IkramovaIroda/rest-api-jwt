//package com.restapijwt.oauth2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/oauth")
//public class OauthController {
//
//    @Autowired
//    GithubUserRepository githubUserRepository;
//
//    @GetMapping
//    public String user(@AuthenticationPrincipal OAuth2User principal) {
//        GithubUser user = new GithubUser();
//        githubUserRepository.save(user); // save db
//        return "index";
//    }
//}
