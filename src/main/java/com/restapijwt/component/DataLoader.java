package com.restapijwt.component;

import com.restapijwt.entity.Company;
import com.restapijwt.entity.Role;
import com.restapijwt.entity.User;
import com.restapijwt.entity.enums.PermissionEnum;
import com.restapijwt.entity.enums.RoleEnum;
import com.restapijwt.repository.CompanyRepository;
import com.restapijwt.repository.RoleRepository;
import com.restapijwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    //field metod
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;
    @Value("${spring.sql.init.mode}")
    String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            PermissionEnum[] values = PermissionEnum.values();

            Set<Role> roleList = new LinkedHashSet<>();
            Role userRole = roleRepository.save(new Role(RoleEnum.ROLE_USER, new ArrayList<>(Arrays.asList(PermissionEnum.COMPANY_READ, PermissionEnum.COMPANY_CRUD))));
            Role adminRole = roleRepository.save(new Role(RoleEnum.ROLE_ADMIN, Arrays.asList(values)));
            Role moderRole = roleRepository.save(new Role(RoleEnum.ROLE_MODERATOR, Arrays.asList(PermissionEnum.COMPANY_READ, PermissionEnum.COMPANY_EDIT, PermissionEnum.COMPANY_CRUD)));
            roleList.add(userRole);
            roleList.add(adminRole);
            roleList.add(moderRole);

            User jafar = userRepository.save(new User("Jafar", passwordEncoder.encode("123"), roleList));
            companyRepository.save(new Company(1l, "PDP", jafar));
        }
    }
}
