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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        session.update(entity);
        return "done";
    }

    public List<ReservationEntity> getReservationsAboveDate(Session session,String column,Date date) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ReservationEntity> criteriaQuery = criteriaBuilder.createQuery(ReservationEntity.class);
        Root<ReservationEntity> root = criteriaQuery.from(ReservationEntity.class);
        
        // Define the condition for dates later than the current day
        Predicate condition = criteriaBuilder.greaterThan(root.get(column), date);
        
        criteriaQuery.select(root).where(condition);
        List<ReservationEntity> resultList = session.createQuery(criteriaQuery).getResultList();
        
        return resultList;
    }

    public List<ReservationEntity> getAll(Session session) {
        String hql="FROM ReservationEntity";
        
        var query=session.createQuery(hql);
        List<ReservationEntity> entities=query.list();
        return entities;
    }
    
    
}
