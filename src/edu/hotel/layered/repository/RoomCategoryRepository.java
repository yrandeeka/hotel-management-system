/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.repository;

import edu.hotel.layered.dto.RoomCategoryDto;
import edu.hotel.layered.entity.RoomCategoryEntity;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;

/**
 *
 * @author Yasas Randeeka
 */
public class RoomCategoryRepository {
     public RoomCategoryEntity get(Integer id,Session session){
        RoomCategoryEntity entity=session.get(RoomCategoryEntity.class, id);
        return entity;
    }
    public static List<RoomCategoryEntity> getAll(Session session) {
        String hql="FROM RoomCategoryEntity";
        
        Query<RoomCategoryEntity> query=session.createQuery(hql);
        List<RoomCategoryEntity> roomCategoryEntities=query.list();
        return roomCategoryEntities;
    }
    public int save(RoomCategoryDto dto,Session session){
        String sql = "insert into room_category(occupancy,bed_size,rate)"
                + " VALUES(:occupancy, :bedSize, :rate)";
        Query query=session.createSQLQuery(sql);
        query.setParameter("occupancy", dto.getOccupancy());
        query.setParameter("bedSize", dto.getBedSize());
        query.setParameter("rate", dto.getRate());
        
        int rowCount=query.executeUpdate();

        return rowCount;
    }

    public int update(RoomCategoryDto dto,Session session) {
        String sql="UPDATE room_category SET occupancy=:occupancy, bed_size=:bedSize,"
                + "rate=:rate WHERE id=:roomCategoryId";
        
        Query query=session.createSQLQuery(sql);
        query.setParameter("roomCategoryId", dto.getRoomCategoryId());
        query.setParameter("occupancy", dto.getOccupancy());
        query.setParameter("bedSize", dto.getBedSize());
        query.setParameter("rate", dto.getRate());

        return query.executeUpdate();
    }

    public int delete(Session session, Integer id) {
        String sql="DELETE FROM room_category WHERE id=:roomCategoryId";
        Query query=session.createSQLQuery(sql);
        query.setParameter("roomCategoryId", id);
        
        return query.executeUpdate(); 
    }

    public Double getRate(Integer id, Session session) {
        System.out.println("Repo pass id"+id);
        String sqlCategoryId="SELECT category_id FROM room WHERE id=:id";
        Query<Integer> QueryCategoryId=session.createSQLQuery(sqlCategoryId);
        QueryCategoryId.setParameter("id", id);
        Integer catId=QueryCategoryId.uniqueResult();
        
        String sqlRate="SELECT rate  FROM room_category WHERE id=:id";
        Query<Double> queryRate=session.createSQLQuery(sqlRate);
        queryRate.setParameter("id", catId);
        return queryRate.uniqueResult();
    }
}
