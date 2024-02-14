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
@Table(name = "room_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomCategoryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "occupancy", nullable = false,length = 150)
    private String occupancy;
    
    @Column(name = "bed_size", nullable = false,length = 150)
    private String bedSize;
    
    @Column(name = "rate",nullable = false)
    private Double rate;
    
    @OneToMany(mappedBy = "roomCategoryEntity",targetEntity = RoomEntity.class)
    @Transient
    private List<RoomEntity> roomEntities;
}