/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Yasas Randeeka
 */
@Entity
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "booking_date",nullable = false)
    private Date bookingDate;
    
    @Column(name = "reserved_from",nullable = false)
    private Date reservedFrom;
    
    @Column(name = "reserved_to",nullable = false)
    private Date reservedTo;
    
    @Column(name = "check_in",nullable = true)
    private Date checkIn;
    
    @Column(name = "check_out",nullable = true)
    private Date checkOut;
    
    @Column(name = "pkg_type",nullable = false,length = 150)
    private String pkgType;
    
    @Column(name = "pkg_rate",nullable = false)
    private Double pkgRate;
    
    @Column(name = "deposit",nullable = true)
    private Double deposit;
    
    @Column(name = "total_charge",nullable = false)
    private Double totalCharge;
    
    @Column(name = "cancellation",nullable = false,length = 150)
    private String cancellation;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomerEntity customerEntity;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reservation_room",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")}
    )
    private List<RoomEntity> roomEntities=new ArrayList<>();
}
