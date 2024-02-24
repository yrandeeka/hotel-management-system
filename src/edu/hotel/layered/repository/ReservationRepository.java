/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.hotel.layered.repository;

import edu.hotel.layered.dto.ReservationDto;
import edu.hotel.layered.entity.ReservationEntity;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Yasas Randeeka
 */
public class ReservationRepository {
    private ReservationEntity reservationEntity=new ReservationEntity();
    private Date stringToDate(String date){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date pDate=dateFormat.parse(date);
            Timestamp timestamp=new Timestamp(pDate.getTime());
            return timestamp;
        } catch (Exception e) {
            return null;
        }
    }

    public int save(ReservationDto dto,Session session){
        int finalRowCount=0;
        Integer custId=Integer.parseInt(dto.getCustomerName().split("-")[0]);
        Integer roomId=Integer.parseInt(dto.getRoomDescription().split("-")[0]);
        System.out.println("roomId-"+roomId);
        String sql = "insert into reservation(cancellation,check_in,check_out,deposit,pkg_rate,"
                + "pkg_type,total_charge, customer_id,booking_date,reserved_from,reserved_to)"
                + " VALUES(:cancellation,:checkIn,:checkOut,:deposit,:pkgRate,:pkgType,"
                + ":totalCharge,:customerId,:bookingDate,:reservedFrom,:reservedTo)";
        
        Query query=session.createSQLQuery(sql);
        
        System.out.println("dto.getCancellation()-"+dto.getCancellation());
        System.out.println("dto.getBookingDate()-"+dto.getBookingDate());
        System.out.println("stringToDate(dto.getBookingDate())-"+stringToDate(dto.getBookingDate()));
        
        query.setParameter("cancellation", dto.getCancellation());
        query.setParameter("checkIn",stringToDate(dto.getCheckIn()));
        query.setParameter("checkOut", stringToDate(dto.getCheckOut()));
        query.setParameter("deposit", dto.getDeposit());
        query.setParameter("pkgRate", dto.getPackageRate());
        query.setParameter("pkgType", dto.getPackageType());
        query.setParameter("totalCharge", dto.getTotalCharge());
        query.setParameter("customerId", custId);
        query.setParameter("bookingDate", stringToDate(dto.getBookingDate()));
        query.setParameter("reservedFrom", stringToDate(dto.getReservedFrom()));
        query.setParameter("reservedTo", stringToDate(dto.getReservedTo()));
        
        int rowCount=query.executeUpdate();
        System.out.println("rowCount-----"+rowCount);
        Integer reservedId=0;
        if(rowCount>0){
            reservedId=(Integer)session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
            //Serializable generatedId=session.getIdentifier(reservationEntity);
            //reservedId=Integer.parseInt(generatedId.toString());
            System.out.println("reservedId-"+reservedId);
        }
        String sql_1="INSERT INTO reservation_room(room_id,reservation_id) VALUES(:roomId,:reservationId)";

        Query query_1=session.createSQLQuery(sql_1);
        query_1.setParameter("roomId",roomId);
        query_1.setParameter("reservationId",reservedId);

        finalRowCount=query_1.executeUpdate();
        System.out.println("finalRowCount-"+finalRowCount);
        return finalRowCount;
    }
}
