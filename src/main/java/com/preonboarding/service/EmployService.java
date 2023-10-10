package com.preonboarding.service;

import com.preonboarding.dto.RequestEmployRegistrationDto;
import com.preonboarding.dto.RequestEmployUpdateDto;
import com.preonboarding.dto.ResponseSavedEmployDto;
import com.preonboarding.dto.ResponseUpdateEmployDto;

public interface EmployService {

    ResponseSavedEmployDto employInsert(RequestEmployRegistrationDto requestEmployRegistrationDto);

    ResponseUpdateEmployDto employUpdate(RequestEmployUpdateDto requestEmployUpdateDto);

    void delete(Long employId);
}
