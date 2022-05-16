package com.restapijwt.oauth2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "oauth2")
public interface GithubUserRepository extends JpaRepository<GithubUser, Integer> {

}
