/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.repository;

import edu.hotel.layered.dto.LoginDto;
import org.hibernate.Session;

/**
 *
 * @author Yasas Randeeka
 */
public class LoginRepository {
    
    public int login(LoginDto dto,Session session) {
        System.out.println("dto"+dto.getUsername());
        System.out.println("dto"+dto.getPassword());
        Integer count=(Integer)session.createQuery("SELECt count(*) from UserEntity "
                + "WHERE userName=:username and userPassword=:userpassword").
                setParameter("username", dto.getUsername()).
                setParameter("userpassword", dto.getPassword()).uniqueResult();
        
        return count;
    }
    
}
