/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.service.custom.impl;

import edu.hotel.layered.dto.ReservationDto;
import edu.hotel.layered.dto.RoomDto;
import edu.hotel.layered.entity.CustomerEntity;
import edu.hotel.layered.entity.ReservationEntity;
import edu.hotel.layered.entity.RoomEntity;
import edu.hotel.layered.repository.CustomerRepository;
import edu.hotel.layered.repository.ReservationRepository;
import edu.hotel.layered.repository.RoomRepository;
import edu.hotel.layered.util.SessionFactoryConfiguration;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Yasas Randeeka
 */
public class ReservationService {
    private ReservationRepository reservationRepository=new ReservationRepository();
    private CustomerRepository customerRepository=new CustomerRepository();
    private RoomRepository roomRepository=new RoomRepository();
    
    private Date formatDate(Date date,String format){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            //Date pDate=dateFormat.parse(date);
            Timestamp timestamp=new Timestamp(date.getTime());
            return timestamp;
        } catch (Exception e) {
            return null;
        }
    }
    private List<RoomEntity> creatRoomEntities(ReservationDto dto,Session session){
        List<String> selectedRooms=dto.getRoomDescription();
            List<RoomEntity> entityRooms=new ArrayList<>();
            for (String selectedRoom : selectedRooms) {
                Integer roomId=Integer.parseInt(selectedRoom.split("-")[0]);
                RoomEntity roomEntity=roomRepository.get(roomId, session);
                entityRooms.add(roomEntity);
            }
        return entityRooms;    
    }

    public String saveReservation(ReservationDto dto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            /*Create Rooms Entity List*/
            List<RoomEntity> entityRooms=creatRoomEntities(dto, session);
//            List<String> selectedRooms=dto.getRoomDescription();
//            List<RoomEntity> entityRooms=new ArrayList<>();
//            for (String selectedRoom : selectedRooms) {
//                Integer roomId=Integer.parseInt(selectedRoom.split("-")[0]);
//                RoomEntity roomEntity=roomRepository.get(roomId, session);
//                entityRooms.add(roomEntity);
//            }
            /*create Customer Entity Object*/
            Integer custId=Integer.parseInt(dto.getCustomerName().split("-")[0]);
            CustomerEntity cusEntity=customerRepository.get(custId, session);
            /*Create Reservation Entity*/
            ReservationEntity entity=new ReservationEntity();
            
            entity.setCustomerEntity(cusEntity);
            entity.setBookingDate(formatDate(dto.getBookingDate(),"yyyy-MM-dd HH:mm:ss"));
            entity.setReservedFrom(formatDate(dto.getReservedFrom(),"yyyy-MM-dd"));
            entity.setReservedTo(formatDate(dto.getReservedTo(),"yyyy-MM-dd"));
            entity.setCheckIn(formatDate(dto.getCheckIn(),"yyyy-MM-dd HH:mm:ss"));
            entity.setCheckOut(formatDate(dto.getCheckOut(),"yyyy-MM-dd HH:mm:ss"));
            entity.setPkgType(dto.getPackageType());
            entity.setPkgRate(dto.getPackageRate());
            entity.setTotalCharge(dto.getTotalCharge());
            entity.setDeposit(dto.getDeposit());
            entity.setRoomEntities(entityRooms);
            entity.setCancellation(dto.getCancellation());
            
            int result=reservationRepository.save(entity,session);
        
            if(result>1){
                transaction.commit();
                return "succeed";
            }else{
                transaction.rollback();
                return "failed";
            }
        } catch (Exception e) {
             transaction.rollback();
             return "Error-Save Reservation";
        }  
    }

    public ReservationDto getReservation(int id) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        ReservationDto revDto=new ReservationDto();
        ReservationEntity revEntity=reservationRepository.get(id,session);
        
        List<RoomEntity> rmEntity=revEntity.getRoomEntities();
        List<String> rmsDescription=new ArrayList<>();
        for (int i = 0; i < rmEntity.size(); i++) {
            rmsDescription.add(rmEntity.get(i).getRoomId()+"-"+rmEntity.get(i).getDescription());
        }
        
        revDto.setReservationId(revEntity.getId());
        revDto.setCustomerName(revEntity.getCustomerEntity().getCustomerId()+"-"+
                revEntity.getCustomerEntity().getFirstName()+" "+revEntity.getCustomerEntity().getLastName());
        revDto.setRoomDescription(rmsDescription);
        revDto.setBookingDate(revEntity.getBookingDate());
        revDto.setReservedFrom(revEntity.getReservedFrom());
        revDto.setReservedTo(revEntity.getReservedTo());
        revDto.setCheckIn(revEntity.getCheckIn());
        revDto.setCheckOut(revEntity.getCheckOut());
        revDto.setPackageType(revEntity.getPkgType());
        revDto.setPackageRate(revEntity.getPkgRate());
        revDto.setTotalCharge(revEntity.getTotalCharge());
        revDto.setDeposit(revEntity.getDeposit());
        revDto.setCancellation(revEntity.getCancellation());

        return revDto;
                
    }

    public String updateReservation(ReservationDto dto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<RoomEntity> entityRooms=creatRoomEntities(dto, session);
            Integer custId=Integer.parseInt(dto.getCustomerName().split("-")[0]);
            CustomerEntity cusEntity=customerRepository.get(custId, session);
            /*Create Reservation Entity*/
            ReservationEntity entity=new ReservationEntity();

            entity.setId(dto.getReservationId());
            entity.setCustomerEntity(cusEntity);
            entity.setReservedFrom(formatDate(dto.getReservedFrom(),"yyyy-MM-dd"));
            entity.setReservedTo(formatDate(dto.getReservedTo(),"yyyy-MM-dd"));
            entity.setPkgType(dto.getPackageType());
            entity.setPkgRate(dto.getPackageRate());
            entity.setTotalCharge(dto.getTotalCharge());
            entity.setDeposit(dto.getDeposit());
            entity.setRoomEntities(entityRooms);

            String result=reservationRepository.update(entity,session);

            if(result.equals("done")){
                transaction.commit();
                return "succeed";
            }else{
                transaction.rollback();
                return "failed";
            }
        } catch (Exception e) {
             transaction.rollback();
             return "Error-Update Reservation";
        }
    }
    
    
}
