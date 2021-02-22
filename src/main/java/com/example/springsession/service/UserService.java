package com.example.springsession.service;

import com.example.springsession.dto.MyRequestDTO;
import com.example.springsession.dto.MyResponseDTO;

public interface UserService {
    boolean updateEmployee(MyRequestDTO request,String id);
}
