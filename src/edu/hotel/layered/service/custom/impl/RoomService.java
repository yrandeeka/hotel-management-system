/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.service.custom.impl;

import edu.hotel.layered.dto.RoomCategoryDto;
import edu.hotel.layered.entity.RoomCategoryEntity;
import edu.hotel.layered.repository.RoomRepository;
import edu.hotel.layered.util.SessionFactoryConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yasas Randeeka
 */
public class RoomService {
    RoomRepository roomRepository=new RoomRepository();
    
    public List<RoomCategoryDto> getAllRoomCategories() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<RoomCategoryDto> list=new ArrayList<>();
        try {
            List<RoomCategoryEntity> listEntity=roomRepository.getAllCategories(session);
//            for (int i = 0; i < listEntity.size(); i++) {
//                System.out.println("fffffffffff");
//                list.add(new RoomCategoryDto(
//                        listEntity.get(i).getId(),
//                        listEntity.get(i).getOccupancy(),
//                        listEntity.get(i).getBedSize(),
//                        listEntity.get(i).getRate()
//                ));
//            }
            for (RoomCategoryEntity entity : listEntity) {
                list.add(new RoomCategoryDto(
                        entity.getId(),
                        entity.getOccupancy(),
                        entity.getBedSize(),
                        entity.getRate()
                ));   
            }
        } catch (Exception e) {
           return list;
        }
        return list;
    }
}
