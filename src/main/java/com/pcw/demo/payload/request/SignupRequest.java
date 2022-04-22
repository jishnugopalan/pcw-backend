package com.pcw.demo.payload.request;

import java.util.Set;

import lombok.Data;

@Data
public class SignupRequest {
   
    private String username;
    private String fullname;
    private Long phone;
    private Set<String> role;
    private String password;
  

}
