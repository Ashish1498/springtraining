package com.example.springsession.service.impl;

import com.example.springsession.dto.MyRequestDTO;
import com.example.springsession.dto.MyResponseDTO;
import com.example.springsession.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("inside userservice constructor");
    }

    @PostConstruct
    public void init()
    {
        System.out.println("inside user service post constructor");
    }

    @Override
    public boolean updateEmployee(MyRequestDTO request, String id) {
        //process
        System.out.println("Inside user Service "+request +"id "+id);
        return false;
    }
}
