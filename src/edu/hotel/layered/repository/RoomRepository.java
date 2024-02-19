
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

    public int save(RoomDto dto, Session session) {
        String occupancy=dto.getCategory().split(",")[0];
        String bedSize=dto.getCategory().split(",")[1];
        String sql_1="SELECT id FROM room_category WHERE occupancy=:occupancy AND bed_size=:bedSize";
        Query<Integer> query_1=session.createSQLQuery(sql_1);
        query_1.setParameter("occupancy", occupancy);
        query_1.setParameter("bedSize", bedSize);
        
        int categoryId=query_1.uniqueResult();
        
        String sql_2 = "insert into room(description,available,category_id)"
                + " VALUES(:description, :available, :category_id)";
        Query query=session.createSQLQuery(sql_2);
        query.setParameter("description", dto.getDescription());
        query.setParameter("available", dto.getAvaiable());
        query.setParameter("category_id", categoryId);
        int rowCount=query.executeUpdate();
        return rowCount;
    }

    public List<RoomEntity> getAll(Session session) {
        String hql="SELECT room.id,room.available,room.category_id,room.description,"
                + "room_category.bed_size,room_category.occupancy FROM room INNER JOIN room_category "
                + "ON room.category_id=room_category.id;";
        
//        Query query = session.createSQLQuery(sql)
//                    .addScalar("id", LongType.INSTANCE)
//                    .addScalar("available", BooleanType.INSTANCE)
//                    .addScalar("category_id", LongType.INSTANCE)
//                    .addScalar("description", StringType.INSTANCE)
//                    .addScalar("bed_size", StringType.INSTANCE)
//                    .addScalar("occupancy", IntegerType.INSTANCE)
//                    .setResultTransformer(Transformers.aliasToBean(RoomCategoryDTO.class));
//
//        List<RoomCategoryDTO> resultList = query.list();
        
        Query query=session.createQuery(hql);
        List<RoomEntity> roomEntities=query.list();
        return roomEntities;
    }
    
}
