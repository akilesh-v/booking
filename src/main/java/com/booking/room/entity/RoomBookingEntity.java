package com.booking.room.entity;

import com.booking.room.model.request.RoomBookingRequest;
import lombok.*;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private Long bookingId;

    @Column(name="room_id")
    private Long roomId;

    @Column(name="booking_from_date")
    private Date bookingFromDate;

    @Column(name="booking_to_date")
    private Date bookingToDate;

    @Column(name="adult_count")
    private Integer adultCount;

    @ElementCollection
    @Column(name="child_age")
    private List<Integer> childAge;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id",insertable = false,updatable = false)
    private RoomsMasterEntity roomMaster;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id",insertable = false,updatable = false)
    private RoomPriceEntity roomPrice;
    
    public RoomBookingEntity(RoomBookingRequest roomBookingRequest){
        this.roomId=roomBookingRequest.getRoomId();
        this.bookingFromDate=roomBookingRequest.getBookingFromDate();
        this.bookingToDate=roomBookingRequest.getBookingToDate();
        this.adultCount=roomBookingRequest.getAdultCount();
        this.childAge=roomBookingRequest.getChildsAge();
    }

}
