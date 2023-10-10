package com.preonboarding.repository;

import com.preonboarding.entity.Employ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployRepository extends JpaRepository<Employ, Long> {
}
