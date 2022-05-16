package com.restapijwt.service;

import com.restapijwt.dto.ApiResponse;
import com.restapijwt.dto.UserDTO;
import com.restapijwt.entity.Role;
import com.restapijwt.entity.Turniket;
import com.restapijwt.entity.User;
import com.restapijwt.entity.enums.RoleEnum;
import com.restapijwt.exception.ResourceNotFoundException;
import com.restapijwt.repository.RoleRepository;
import com.restapijwt.repository.TurniketRepository;
import com.restapijwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final TurniketRepository turniketRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public ApiResponse add(UserDTO dto) {
        String role = dto.getRole();
        RoleEnum roleEnum = RoleEnum.valueOf(role);
        //kim qoshilyapti user moderator
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        for (Role role1 : principal.getRoleList()) {
            //director
            if (role1.getRoleName().equals(RoleEnum.ROLE_ADMIN)) {
                //admin ekan moderator va user qo'sha olishi kerak
                if (roleEnum.equals(RoleEnum.ROLE_MODERATOR) || roleEnum.equals(RoleEnum.ROLE_USER)) {
                    User user = new User();
                    user.setEmail(dto.getEmail());
                    user.setUsername(dto.getUsername());
                    user.setPassword(passwordEncoder.encode("1111"));

                    Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
                    user.setRoleList(new LinkedHashSet(Collections.singleton(byRoleName.get())));

                    User save = userRepository.save(user);

                    Turniket turniket = new Turniket();
                    turniket.setUser(save);
                    turniketRepository.save(turniket);
                }
            } else if (role1.getRoleName().equals(RoleEnum.ROLE_MODERATOR)) {
                if (role.equals(RoleEnum.ROLE_USER.name())) {
                    User user = new User();
                    user.setEmail(dto.getEmail());
                    user.setUsername(dto.getUsername());
                    user.setPassword(passwordEncoder.encode("1111"));

                    Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
                    user.setRoleList(new LinkedHashSet(Collections.singleton(byRoleName.get())));

                    Turniket turniket = new Turniket();
                    turniket.setUser(user);
                    turniketRepository.save(turniket);
                    userRepository.save(user);
                }
            }
//            } else {
//                throw new ResourceNotFoundException("Szda bunaqa huquq yoq");
//            }
        }
        return ApiResponse.builder().message("Added!").success(true).build();
    }

    public ApiResponse edit(Integer id, UserDTO dto) {
        return null;
//        List<Role> roles = roleRepository.findAllById(dto.getRoleList());
//        if (roles.isEmpty()) return new ApiResponse("Roles not found", false);
//
//        Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser.isEmpty()) return new ApiResponse("User not found", false);
//
//        User user = optionalUser.get();
//
//        user.setEmail(dto.getEmail());
//        user.setUsername(dto.getUsername());
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));
//        user.setRoleList(new LinkedHashSet<>(roles));
//
//        userRepository.save(user);
//
//        return new ApiResponse("Edited", true);
    }
}
