package com.preonboarding.service;

import com.preonboarding.dto.RequestEmployRegistrationDto;
import com.preonboarding.dto.ResponseSavedEmployDto;
import com.preonboarding.entity.Employ;

public interface EmployService {

    ResponseSavedEmployDto employInsert(RequestEmployRegistrationDto requestEmployRegistrationDto);
}
