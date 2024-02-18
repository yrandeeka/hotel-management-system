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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomCategoryDto {
    private Integer roomCategoryId;
    private String occupancy;
    private String bedSize;
    private Double rate;
}
