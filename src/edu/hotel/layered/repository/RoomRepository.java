
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.repository;

import edu.hotel.layered.entity.RoomCategoryEntity;
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
        String hql="SELECT RoomCategoryEntity";
        Query<RoomCategoryEntity> query=session.createQuery(hql);
        List<RoomCategoryEntity> roomCategoryList=query.list();
        return roomCategoryList;
    }
    
}
