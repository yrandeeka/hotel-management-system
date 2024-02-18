/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Yasas Randeeka
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDto {
    private Integer roomId;
    private String category;
    private String avaiable; 
}
