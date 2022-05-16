package com.restapijwt.repository;


import com.restapijwt.entity.Turniket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurniketRepository extends JpaRepository<Turniket, Integer> {
}
