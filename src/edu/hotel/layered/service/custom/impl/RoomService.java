/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.service.custom.impl;

import edu.hotel.layered.dto.RoomCategoryDto;
import edu.hotel.layered.dto.RoomDto;
import edu.hotel.layered.entity.RoomCategoryEntity;
import edu.hotel.layered.entity.RoomEntity;
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

    public String saveRoom(RoomDto dto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            int result=roomRepository.save(dto,session);
        
            if(result==1){
                transaction.commit();
                return "succeed";
            }else{
                transaction.rollback();
                return "failed";
            }
        } catch (Exception e) {
             transaction.rollback();
             return "Error-Save Room";
        }
    }

    public List<RoomDto> getAllRooms() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction(); 
        
        List<RoomDto> dtos=new ArrayList<>();
        
        List<RoomEntity> roomEntities=roomRepository.getAll(session);
        
        if(!roomEntities.isEmpty()){
            for (int i = 0; i < roomEntities.size(); i++) {
                dtos.add(new RoomDto(
                        roomEntities.get(i).getRoomId(),
                        roomEntities.get(i).getDescription(),
                        "",
                        roomEntities.get(i).getAvailable()
                ));
            }
        }
        return dtos;
    }
}
