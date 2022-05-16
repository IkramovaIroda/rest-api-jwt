package com.restapijwt.repository;


import com.restapijwt.entity.TurniketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurniketHistoryRepository extends JpaRepository<TurniketHistory, Integer> {
}
