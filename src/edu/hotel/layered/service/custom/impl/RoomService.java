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
        
        List<RoomEntity> entities=roomRepository.getAll(session);
        
        if(!entities.isEmpty()){
            for (int i = 0; i < entities.size(); i++) {
                dtos.add(new RoomDto(
                        entities.get(i).getRoomId(),
                        entities.get(i).getDescription(),
                        entities.get(i).getRoomCategoryEntity().getOccupancy()+
                                ","+entities.get(i).getRoomCategoryEntity().getBedSize(),
                        entities.get(i).getAvailable()
                ));
            }
        }
        return dtos;
    }

    public String updateRoom(RoomDto dto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction(); 
        
        try {
            int count=roomRepository.update(dto,session);
            if(count==1){
                transaction.commit();
                return "succeed";
            }
            else{
                transaction.rollback();
                return "failed";
            }
        } catch (Exception e) {
            transaction.rollback();
            return "Error-Update Room";
        }
    }

    public String deleteRoom(int id) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        try {
            int count=roomRepository.delete(session,id);
            System.out.println("count"+count);
            if(count==1){
                transaction.commit();
                return "succeed";
            }
            else{
                transaction.rollback();
                return "failed";
            }
        } catch (Exception e) {
            transaction.rollback();
            return "Error-Delete Customer";
        }
    }

    public RoomDto getRoom(int id) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        RoomEntity entity=roomRepository.get(id,session);
        RoomDto dto=new RoomDto();
        
        dto.setRoomId(entity.getRoomId());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getRoomCategoryEntity().getOccupancy()+","+entity.getRoomCategoryEntity().getBedSize());
        dto.setAvaiable(entity.getAvailable());
        
        return dto;
    }
}
