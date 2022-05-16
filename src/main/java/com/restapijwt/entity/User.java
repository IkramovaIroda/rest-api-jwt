package com.restapijwt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapijwt.entity.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username, password, email;

    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roleList; //user_roleList

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonIgnore
    private Date birthDate = new Date();

    @Transient //bazada column ochilmaydi frontga qaytaradi
//    private int age = LocalDate.now().getYear() -birthDate.getYear();
    private Period age = Period.between(LocalDate.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDay()), LocalDate.now());

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(String username, String password, Set<Role> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //hasAuthority
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        for (Role role : this.roleList) {
            for (PermissionEnum permissionEnum : role.getPermissionEnum()) {
                authorities.add(new SimpleGrantedAuthority(permissionEnum.name()));
            }
        }

        //preauthorize hasRole
//        for (Role role : this.roleList) {
//            authorities.add(role.getRoleName());
//        }
        return authorities;
    }
}
