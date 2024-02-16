/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.repository;

import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.entity.CustomerEntity;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        System.out.println("firstName"+dto.getFirstName());
        System.out.println("lastName"+dto.getLastName());
        System.out.println("contactNo"+dto.getContactNo());
        System.out.println("address"+dto.getAddress());
        System.out.println("email"+dto.getEmail());

        String hql = "insert into customer(address,contact_no,email,first_name,last_name)"
                + " VALUES(:address, :contactNo, :email, :firstName, :lastName)";
        Query query=session.createSQLQuery(hql);
        query.setParameter("address", dto.getAddress());
        query.setParameter("contactNo", dto.getContactNo());
        query.setParameter("email", dto.getEmail());
        query.setParameter("firstName", dto.getFirstName());
        query.setParameter("lastName", dto.getLastName());
        
        int rowCount=query.executeUpdate();
        System.out.println("rowCount"+rowCount);

        return rowCount;
    }
    
    
    
    
}
