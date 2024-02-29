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
import org.hibernate.type.DateType;

/**
 *
 * @author Yasas Randeeka
 */
public class ReservationRepository {
    public String save(ReservationEntity entity,Session session){
        String finalResult="failed";
        Integer entityId=(Integer)session.save(entity);
        System.out.println("entityId-"+entityId);
        if(entity.getCheckIn()!=null && entityId>0){
            List<RoomEntity> rmEntities=entity.getRoomEntities();
            finalResult=setRoomStatus(rmEntities,"No",session);
        }
        else{
            finalResult="succeed";
        }
        return finalResult;
    }

    public ReservationEntity get(int id,Session session) {
        ReservationEntity entity=session.get(ReservationEntity.class, id);  
        return entity;
    }

    public String update(ReservationEntity entity, Session session) {
        session.update(entity);
        return "succeed";        
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
    public String setRoomStatus(List<RoomEntity> entities,String status,Session session){
        int success_count=0;
        for(int i=0;i<entities.size();i++){
            Integer roomId=entities.get(i).getRoomId();
            String rmSql="UPDATE room SET available=:available WHERE id=:roomId";
            Query query_rm=session.createSQLQuery(rmSql);
            query_rm.setParameter("roomId", roomId);
            query_rm.setParameter("available", "No");
        
            int rowCount_1=query_rm.executeUpdate();
            if(rowCount_1==1) 
                success_count++;
        }
        if(success_count>0 && success_count==entities.size()){
            return "succeed";
        }
        else 
            return "failed";
    }

    public Date getBookingDate(Integer id, Session session) {
        String bkDateSql="SELECT booking_date FROM reservation WHERE id=:id";
        Query query_bk_date=session.createSQLQuery(bkDateSql).setParameter("id", id).addScalar("booking_date", DateType.INSTANCE);
        
        Date bk_date=(Date)query_bk_date.getSingleResult();
        return bk_date;
    }

    public int cancelReservation(Integer id, Session session) {
        String sql="UPDATE reservation SET cancellation=:cancel WHERE id=:revId";
        Query query=session.createSQLQuery(sql);
        query.setParameter("revId", id);
        query.setParameter("cancel", "Yes");
        
        int rowCount=query.executeUpdate();
        
        //List<RoomEntity> entity=get(id,session).getRoomEntities();
        //String finalResult=setRoomStatus(entity,"Yes",session);
        
        return rowCount;
    }
    
    
}
