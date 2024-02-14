/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.util;

import org.hibernate.SessionFactory;
import edu.hotel.layered.entity.CustomerEntity;
import edu.hotel.layered.entity.ReservationEntity;
import edu.hotel.layered.entity.RoomCategoryEntity;
import edu.hotel.layered.entity.RoomEntity;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Yasas Randeeka
 */
public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    
    private SessionFactory sessionFactory;
    
    private SessionFactoryConfiguration(){
        Configuration configuration=new Configuration().configure()
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(ReservationEntity.class)
                .addAnnotatedClass(RoomEntity.class)
                .addAnnotatedClass(RoomEntity.class)
                .addAnnotatedClass(RoomCategoryEntity.class);
        
        sessionFactory=configuration.buildSessionFactory();
    
    }
    public static SessionFactoryConfiguration getInstance(){
        if(sessionFactoryConfiguration == null){
            sessionFactoryConfiguration = new SessionFactoryConfiguration();
        }

        return sessionFactoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
