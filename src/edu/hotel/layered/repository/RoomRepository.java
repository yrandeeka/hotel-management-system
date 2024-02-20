
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.repository;

import edu.hotel.layered.dto.RoomDto;
import edu.hotel.layered.entity.RoomCategoryEntity;
import edu.hotel.layered.entity.RoomEntity;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Yasas Randeeka
 */
public class RoomRepository {

    public List<RoomCategoryEntity> getAllCategories(Session session) {
        String hql="FROM RoomCategoryEntity";
        Query<RoomCategoryEntity> query=session.createQuery(hql);
        List<RoomCategoryEntity> roomCategoryList=query.list();
        return roomCategoryList;
    }

    private int getCategoryId(String category,Session session){
        String occupancy=category.split(",")[0];
        String bedSize=category.split(",")[1];
        String sql="SELECT id FROM room_category WHERE occupancy=:occupancy AND bed_size=:bedSize";
        Query<Integer> query=session.createSQLQuery(sql);
        query.setParameter("occupancy", occupancy);
        query.setParameter("bedSize", bedSize);
        
        return query.uniqueResult();
    }
    public int save(RoomDto dto, Session session) {
        int categoryId=getCategoryId(dto.getCategory(),session);
        
        String sql = "insert into room(description,available,category_id)"
                + " VALUES(:description, :available, :category_id)";
        Query query=session.createSQLQuery(sql);
        query.setParameter("description", dto.getDescription());
        query.setParameter("available", dto.getAvaiable());
        query.setParameter("category_id", categoryId);
        int rowCount=query.executeUpdate();
        return rowCount;
    }

    public List<RoomEntity> getAll(Session session) {
        String hql="FROM RoomEntity";
        
        Query<RoomEntity> query=session.createQuery(hql);
        List<RoomEntity> roomEntities=query.list();
        return roomEntities;
    }

    public int update(RoomDto dto, Session session) {
        int categoryId=getCategoryId(dto.getCategory(),session);
        String sql="UPDATE room SET description=:description,available=:available,"
                + "category_id=:categoryId WHERE id=:roomId";
        Query query=session.createSQLQuery(sql);
        query.setParameter("roomId", dto.getRoomId());
        query.setParameter("description", dto.getDescription());
        query.setParameter("available", dto.getAvaiable());
        query.setParameter("categoryId", categoryId);
        
        int rowCount=query.executeUpdate();
        return rowCount;
    }

    public int delete(Session session, int id) {
        String sql="DELETE FROM room WHERE id=:roomId";
        Query query=session.createSQLQuery(sql);
        query.setParameter("roomId", id);
        System.out.println("FFFF");
        
        return query.executeUpdate();
    }

    public RoomEntity get(int id, Session session) {
        RoomEntity entity=session.get(RoomEntity.class, id);
        return entity;
    }
    
}
