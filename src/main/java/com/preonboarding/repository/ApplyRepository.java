package com.preonboarding.repository;

import com.preonboarding.entity.Apply;
import com.preonboarding.entity.Employ;
import com.preonboarding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    List<Apply> findByUserAndEmploy(User user, Employ employ);
}
