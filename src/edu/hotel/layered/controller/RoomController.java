/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.controller;

import edu.hotel.layered.dto.RoomCategoryDto;
import edu.hotel.layered.dto.RoomDto;
import edu.hotel.layered.entity.RoomCategoryEntity;
import edu.hotel.layered.service.custom.impl.RoomService;
import java.util.List;

/**
 *
 * @author Yasas Randeeka
 */
public class RoomController {
    RoomService roomService=new RoomService();
    
    public List<RoomCategoryDto> getAllRoomCategories() {
        return roomService.getAllRoomCategories();
    }

    public String saveRoom(RoomDto roomDto) {
        return roomService.saveRoom(roomDto); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<RoomDto> getAllrooms() {
        return roomService.getAllRooms();
    }
    
}