/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.service.custom.impl;

import edu.hotel.layered.dto.LoginDto;
import edu.hotel.layered.repository.LoginRepository;
import edu.hotel.layered.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yasas Randeeka
 */
public class LoginService {
    LoginRepository loginRepository=new LoginRepository();
    public String login(LoginDto dto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        try {
            int count=loginRepository.login(dto,session);
        
        if(count==1){
            transaction.commit();
            return "succeed";
        }
        else{
            transaction.rollback();
            return "failed";
        }
        } catch (Exception e) {
            transaction.rollback();
            return "Error-Login Fail";
        }
        
    }
    
}
