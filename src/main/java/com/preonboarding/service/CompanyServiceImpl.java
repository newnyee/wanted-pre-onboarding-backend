package com.preonboarding.service;

import com.preonboarding.entity.Company;
import com.preonboarding.exception.NotFoundCompanyException;
import com.preonboarding.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company findById(Long id) {
        Optional<Company> findCompany = companyRepository.findById(id);
        if (findCompany.isEmpty()) {
            throw new NotFoundCompanyException("회사 ID를 확인해주세요.", HttpStatus.BAD_REQUEST);
        }
        return findCompany.get();
    }
}
