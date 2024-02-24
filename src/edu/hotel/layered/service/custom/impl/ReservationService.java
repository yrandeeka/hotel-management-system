/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.service.custom.impl;

import edu.hotel.layered.dto.ReservationDto;
import edu.hotel.layered.repository.ReservationRepository;
import edu.hotel.layered.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yasas Randeeka
 */
public class ReservationService {
    private ReservationRepository reservationRepository=new ReservationRepository();

    public String saveReservation(ReservationDto dto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            int result=reservationRepository.save(dto,session);
        
            if(result==1){
                transaction.commit();
                return "succeed";
            }else{
                transaction.rollback();
                return "failed";
            }
        } catch (Exception e) {
             transaction.rollback();
             return "Error-Save Reservation";
        }  
    }
    
    
}
