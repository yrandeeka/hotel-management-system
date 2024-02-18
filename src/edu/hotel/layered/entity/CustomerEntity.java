/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
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
@Table(name = "customer",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    
    @Column(name="first_name",nullable = false,length = 150)
    private String firstName;
    
    @Column(name="last_name",nullable = false,length = 150)
    private String lastName;
    
    @Column(name="contact_no",nullable = true)
    private Integer contactNo;
    
    @Column(name="address",nullable = false,length = 250)
    private String address;
    
    @Column(name="email",nullable = true,length = 150)
    private String email;  
    
    @OneToMany(mappedBy = "customerEntity",targetEntity = ReservationEntity.class)
    @Transient
    private List<ReservationEntity> reservationEntities;
}
