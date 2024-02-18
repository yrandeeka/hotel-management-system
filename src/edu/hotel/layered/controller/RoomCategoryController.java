/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.controller;

import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.dto.RoomCategoryDto;
import edu.hotel.layered.entity.RoomCategoryEntity;
import edu.hotel.layered.repository.RoomCategoryRepository;
import edu.hotel.layered.service.custom.impl.RoomCategoryService;
import edu.hotel.layered.util.SessionFactoryConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yasas Randeeka
 */
public class RoomCategoryController {
    private RoomCategoryService roomCategoryService = new RoomCategoryService();
    
    public String saveRoomCategory(RoomCategoryDto dto){
        
        return roomCategoryService.saveCustomer(dto);
    }

    public List<RoomCategoryDto> getAllRoomCategories() {
        return roomCategoryService.getAllRoomCategories();

    }

    public RoomCategoryDto getRoomCategory(Integer id) {
        return roomCategoryService.getRoomCategory(id);
    }

    public String updateRoomCategory(RoomCategoryDto dto) {
        return roomCategoryService.updateRoomCategory(dto);
    }

    public String deleteRoomCategory(Integer id) {
        return roomCategoryService.deleteRoomCategory(id);
    }
}
