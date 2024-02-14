/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.service.custom.impl;

import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.repository.CustomerRepository;
import edu.hotel.layered.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yasas Randeeka
 */
public class CustomerService {
    private CustomerRepository customerRepository=new CustomerRepository();
    
    public String saveCustomer(CustomerDto dto){
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        int result=customerRepository.save(dto,session);
        
        if(result==1){
            return "Succeed";
        }else{
            return "failed";
        }
        
    }   
}
