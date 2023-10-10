package com.preonboarding.service;
import com.preonboarding.repository.EmployRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployServiceImpl implements EmployService {

    EmployRepository employRepository;

}
