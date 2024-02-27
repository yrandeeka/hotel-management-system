/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.controller;

import edu.hotel.layered.dto.LoginDto;
import edu.hotel.layered.service.custom.impl.LoginService;

/**
 *
 * @author Yasas Randeeka
 */
public class LoginController {
    LoginService loginService=new LoginService();

    public String login(LoginDto dto) {
        return loginService.login(dto);
    }  
}
