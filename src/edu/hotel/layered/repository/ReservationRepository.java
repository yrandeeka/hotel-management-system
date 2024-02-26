/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.repository;

import edu.hotel.layered.dto.ReservationDto;
import edu.hotel.layered.entity.CustomerEntity;
import edu.hotel.layered.entity.ReservationEntity;
import edu.hotel.layered.entity.RoomEntity;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Yasas Randeeka
 */
public class ReservationRepository {
    public int save(ReservationEntity entity,Session session){
        Integer entityId=(Integer)session.save(entity);
        return entityId;
    }

    public ReservationEntity get(int id,Session session) {
        ReservationEntity entity=session.get(ReservationEntity.class, id);
        
        return entity;
    }

    public String update(ReservationEntity entity, Session session) {
        System.out.println("entity.getId()-"+entity.getId());
        System.out.println("entity.getReservedFrom()-"+entity.getReservedFrom());
        System.out.println("entity.getReservedFrom()-"+entity.getRoomEntities().toArray());
        
        session.update(entity);
        return "done";
    }
    
}
