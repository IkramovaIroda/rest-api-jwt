package com.restapijwt.oauth2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    @OneToOne
//    private OAuth2User auth2User;

//    public GithubUser(OAuth2User auth2User) {
//        this.auth2User = auth2User;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return auth2User.getAttributes();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return auth2User.getAuthorities();
//    }
//
//    @Override
//    public String getName() {
//        return auth2User.getName();
//    }

}
