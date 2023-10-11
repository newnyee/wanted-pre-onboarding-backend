package com.preonboarding.repository;

import com.preonboarding.entity.Employ;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployRepository extends JpaRepository<Employ, Long> {
}
