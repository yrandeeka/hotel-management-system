/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.controller;

import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.service.custom.impl.CustomerService;
import java.util.List;

/**
 *
 * @author Yasas Randeeka
 */
public class CustomerController {
    private CustomerService customerService = new CustomerService();
    
    public String saveCustomer(CustomerDto dto){
        
        return customerService.saveCustomer(dto);
    }

    public List<CustomerDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    public CustomerDto getCustomer(int id) {
        return customerService.getCustomer(id);
    }

    public String updateCustomer(CustomerDto custDto) {
        System.out.println("COntroller");
        return customerService.updateCustomer(custDto);
    }

    public String deleteCustomer(int id) {
        return customerService.deleteCustomer(id);
    }
}
