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
    
    @Column(name = "reserved_date",nullable = false)
    private Date reservedDate;
    
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
    
    @ManyToMany(mappedBy = "reservationEntities",targetEntity = RoomEntity.class)
    private List<RoomEntity> roomEntities=new ArrayList<>();
}
