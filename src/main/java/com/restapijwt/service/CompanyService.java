package com.restapijwt.service;

import com.restapijwt.dto.ApiResponse;
import com.restapijwt.dto.CompanyDto;
import com.restapijwt.entity.Company;
import com.restapijwt.entity.Role;
import com.restapijwt.entity.User;
import com.restapijwt.repository.CompanyRepository;
import com.restapijwt.repository.RoleRepository;
import com.restapijwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
//ichida har bir alohida tranzaksiya sifatida bajarilishini kutadi
public class CompanyService {
    final RoleRepository roleRepository;
    final CompanyRepository companyRepository;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;


    public ApiResponse add(CompanyDto companyDto) {

        Company company = new Company();
        company.setName(companyDto.getName());

        User director = new User();
        director.setEmail(companyDto.getEmail());
        director.setUsername(companyDto.getUsername());
        List<Role> all = roleRepository.findAll();
        director.setRoleList(new LinkedHashSet<>(all));
        director.setPassword(passwordEncoder.encode("1111"));

        company.setDirector(director); //bazaga soxranit qiladi
        //cascade bo'lmasa kerak
//        userRepository.save(director);

        companyRepository.save(company);
        return ApiResponse.builder().success(true).message("Company created!").build();
    }

    public ApiResponse edit(Long id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return ApiResponse.builder().message("Company not found").build();
        }
        Company company = optionalCompany.get();

        if (companyDto.getName() != null) {
            if (companyDto.getName().length() < 3) {
                return ApiResponse.builder().message("Company name must be min 3 length").build();
            }
            company.setName(companyDto.getName());
            User director = company.getDirector();
            director.setUsername(companyDto.getUsername());
            director.setEmail(companyDto.getEmail());
            company.setDirector(director);
        }
        companyRepository.save(company);
        return ApiResponse.builder().message("Company edited!").success(true).build();
    }

    public ApiResponse delete(Long id) {
        if (!companyRepository.existsById(id)) {
            return ApiResponse.builder().message("Company not found").build();
        }
        companyRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Company deleted!").build();
    }
}
