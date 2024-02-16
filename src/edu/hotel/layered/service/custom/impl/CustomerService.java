/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.service.custom.impl;

import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.entity.CustomerEntity;
import edu.hotel.layered.repository.CustomerRepository;
import edu.hotel.layered.util.SessionFactoryConfiguration;
import java.util.ArrayList;
import java.util.List;
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
            transaction.commit();
            return "Succeed";
        }else{
            transaction.rollback();
            return "failed";
        }
        
    }   

    public List<CustomerDto> getAllCustomer() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction(); 
        
        List<CustomerDto> custDto=new ArrayList<>();
        
        List<CustomerEntity> customerEntities=customerRepository.getAll(session);
        if(!customerEntities.isEmpty()){
            for (int i = 0; i < customerEntities.size(); i++) {
                custDto.add(new CustomerDto(
                        customerEntities.get(i).getCustomerId(),
                        customerEntities.get(i).getFirstName(),
                        customerEntities.get(i).getLastName(),
                        customerEntities.get(i).getContactNo(),
                        customerEntities.get(i).getAddress(),
                        customerEntities.get(i).getEmail()
                ));
            }
        }
        return custDto;
    }

    public CustomerDto getCustomer(int id) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        CustomerEntity custEntity=customerRepository.get(id,session);
        CustomerDto cusDto=new CustomerDto();
        
        cusDto.setCustomerId(custEntity.getCustomerId());
        cusDto.setFirstName(custEntity.getFirstName());
        cusDto.setLastName(custEntity.getLastName());
        cusDto.setContactNo(custEntity.getContactNo());
        cusDto.setAddress(custEntity.getAddress());
        cusDto.setEmail(custEntity.getEmail());
        
        return cusDto;
    }
}
