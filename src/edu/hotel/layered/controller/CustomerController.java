/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.controller;

import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.service.custom.impl.CustomerService;

/**
 *
 * @author Yasas Randeeka
 */
public class CustomerController {
    private CustomerService customerService = new CustomerService();
    
    public String saveCustomer(CustomerDto dto){
        
        return customerService.saveCustomer(dto);
    }
}
