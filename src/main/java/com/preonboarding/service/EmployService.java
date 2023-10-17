package com.preonboarding.service;

import com.preonboarding.dto.*;

import java.util.List;

public interface EmployService {

    void employInsert(RequestEmployRegistrationDto requestEmployRegistrationDto);

    ResponseUpdateEmployDto employUpdate(RequestEmployUpdateDto requestEmployUpdateDto);

    void delete(Long employId);

    List<ResponseEmployInfoDto> findEmploys();

    ResponseEmployDetailsDto detailsEmploy(Long employId);
}
