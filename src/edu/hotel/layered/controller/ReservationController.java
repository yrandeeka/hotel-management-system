/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.controller;

import com.toedter.calendar.JDateChooser;
import edu.hotel.layered.dto.ReservationDto;
import edu.hotel.layered.service.custom.impl.ReservationService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Yasas Randeeka
 */
public class ReservationController {
    private ReservationService reservationService=new ReservationService();

    public String saveReservation(ReservationDto dto) {
        return reservationService.saveReservation(dto);
    }

    public ReservationDto getReservation(int id) {
        return reservationService.getReservation(id);
    }

    public String updateReservation(ReservationDto dto) {
        return reservationService.updateReservation(dto);
    }

    public List<ReservationDto> getReservationsAboveDate(Date date) {
        return reservationService.getReservationsAboveDate(date); 
    }

    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }
}
