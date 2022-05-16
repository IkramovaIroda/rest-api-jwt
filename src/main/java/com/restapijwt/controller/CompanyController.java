package com.restapijwt.controller;

import com.restapijwt.dto.ApiResponse;
import com.restapijwt.dto.CompanyDto;
import com.restapijwt.entity.Company;
import com.restapijwt.repository.CompanyRepository;
import com.restapijwt.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    final CompanyRepository companyRepository;
    final CompanyService companyService;

    @PreAuthorize("hasAnyAuthority('COMPANY_CRUD', 'COMPANY_READ')")
    @GetMapping
    public HttpEntity<?> getAll(){
        System.out.println(companyRepository.getAll());
        return ResponseEntity.ok().body(companyRepository.getAll());
    }

    @PreAuthorize("hasAnyAuthority('COMPANY_CRUD', 'COMPANY_READ')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalCompany.get());
    }

    @PreAuthorize("hasAnyAuthority('COMPANY_CRUD', 'COMPANY_ADD')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse=companyService.add(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('COMPANY_CRUD', 'COMPANY_EDIT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse=companyService.edit(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('COMPANY_CRUD', 'COMPANY_DELETE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse=companyService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
