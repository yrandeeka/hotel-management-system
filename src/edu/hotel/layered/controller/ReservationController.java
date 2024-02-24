/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.controller;

import edu.hotel.layered.dto.ReservationDto;
import edu.hotel.layered.service.custom.impl.ReservationService;

/**
 *
 * @author Yasas Randeeka
 */
public class ReservationController {
    private ReservationService reservationService=new ReservationService();

    public String saveReservation(ReservationDto dto) {
        return reservationService.saveReservation(dto);
    }
}
