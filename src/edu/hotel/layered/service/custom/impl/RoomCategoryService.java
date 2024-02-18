/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.service.custom.impl;

import edu.hotel.layered.dto.RoomCategoryDto;
import edu.hotel.layered.entity.RoomCategoryEntity;
import edu.hotel.layered.repository.RoomCategoryRepository;
import edu.hotel.layered.util.SessionFactoryConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yasas Randeeka
 */
public class RoomCategoryService {
     private RoomCategoryRepository roomCategoryRepository=new RoomCategoryRepository();
    
    public String saveCustomer(RoomCategoryDto dto){
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            int result=roomCategoryRepository.save(dto,session);
        
            if(result==1){
                transaction.commit();
                return "succeed";
            }else{
                transaction.rollback();
                return "failed";
            }
        } catch (Exception e) {
             transaction.rollback();
             return "Error-Save Room Category";
        }  
    }   

    public List<RoomCategoryDto> getAllRoomCategories() {
             Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction(); 
        
        List<RoomCategoryDto> dtos=new ArrayList<>();
        
        List<RoomCategoryEntity> roomCategoryEntities=roomCategoryRepository.getAll(session);
        if(!roomCategoryEntities.isEmpty()){
            for (int i = 0; i < roomCategoryEntities.size(); i++) {
                dtos.add(new RoomCategoryDto(
                        roomCategoryEntities.get(i).getId(),
                        roomCategoryEntities.get(i).getOccupancy(),
                        roomCategoryEntities.get(i).getBedSize(),
                        roomCategoryEntities.get(i).getRate()
                ));
            }
        }
        return dtos;
    }

    public RoomCategoryDto getRoomCategory(Integer id) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction(); 
        RoomCategoryEntity entity=roomCategoryRepository.get(id,session);
        
        RoomCategoryDto dto=new RoomCategoryDto();
        
        dto.setOccupancy(entity.getOccupancy());
        dto.setBedSize(entity.getBedSize());
        dto.setRate(entity.getRate());
        
        return dto;
    }

    public String updateRoomCategory(RoomCategoryDto dto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            int count=roomCategoryRepository.update(dto,session);
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
            return "Error-Update Room Category";
        }
    }

    public String deleteRoomCategory(Integer id) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        try {
            int count=roomCategoryRepository.delete(session,id);
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
            return "Error-Delete Room Category";
        }
    }

}
