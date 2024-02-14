/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.repository;

import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.entity.CustomerEntity;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Yasas Randeeka
 */
public class CustomerRepository {
    public CustomerEntity get(Integer id,Session session){
        CustomerEntity entity=session.get(CustomerEntity.class, id);
        return entity;
    }
    
    public List<CustomerEntity> getAll(Session session){
        String hql="FROM CustomerEntity";
        
        Query<CustomerEntity> query=session.createQuery(hql);
        List<CustomerEntity> customerEntities=query.list();
        return customerEntities;
    }
    
    public int save(CustomerDto dto,Session session){
        String hql = "INSERT INTO CustomerEntity (firstName,lastName,contactNo,address,email)"
                + " VALUES (:firstName, :lastName, :contactNo,:address,:email)";
        Query query=session.createQuery(hql);
        
        query.setParameter("firstName", dto.getFirstName());
        query.setParameter("lastName", dto.getLastName());
        query.setParameter("contactNo", dto.getContactNo());
        query.setParameter("address", dto.getAddress());
        query.setParameter("email", dto.getEmail());
        
        int rowCount=query.executeUpdate();
        return rowCount;
    }
    
    
    
    
}
