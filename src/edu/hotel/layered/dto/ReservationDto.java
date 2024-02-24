/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.dto;

import java.util.Date;
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
public class ReservationDto {
    private Integer reservationId;
    private String customerName;
    private String roomDescription;
    private String bookingDate;
    private String reservedFrom;
    private String reservedTo;
    private String checkIn;
    private String checkOut;
    private String packageType;
    private Double totalCharge;
    private Double deposit;
}
