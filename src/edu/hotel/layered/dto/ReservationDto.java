/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.dto;

import java.util.Date;
import java.util.List;
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
    private List roomDescription;
    private Date bookingDate;
    private Date reservedFrom;
    private Date reservedTo;
    private Date checkIn;
    private Date checkOut;
    private String packageType;
    private Double packageRate;
    private Integer noOfPkgs;
    private Double extraPayPercentage;
    private Double totalCharge;
    private Double deposit;
    private String cancellation;
}
