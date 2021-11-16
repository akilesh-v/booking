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

    @Column(name="child_count")
    private Integer childCount;

    @ElementCollection
    @Column(name="child_age")
    private List<Integer> childAge;

    @Column(name="max_child_age_limit")
    private Integer maxChildAgeLimit;

    @Column(name="total_amount")
    private Double totalAmount;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "booking_detail",joinColumns = @JoinColumn(name = "booking_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "priceFromDate",column = @Column(name = "price_from_date")),
            @AttributeOverride(name = "priceToDate",column = @Column(name = "price_to_date")),
            @AttributeOverride(name = "basePrice",column = @Column(name = "base_price")),
            @AttributeOverride(name = "extraAdultPrice",column = @Column(name = "extra_adult_price")),
            @AttributeOverride(name = "extraChildPrice",column = @Column(name = "extra_child_price")),
            @AttributeOverride(name = "totalPeriodCost",column = @Column(name = "total_period_cost")),
    })
    private List<RoomBookingDtlEntity> roomBookingDtlEntities;
    
    public RoomBookingEntity(RoomBookingRequest roomBookingRequest){
        this.roomId=roomBookingRequest.getRoomId();
        this.bookingFromDate=roomBookingRequest.getBookingFromDate();
        this.bookingToDate=roomBookingRequest.getBookingToDate();
        this.adultCount=roomBookingRequest.getProcessAdultCount();
        this.childCount=roomBookingRequest.getProcessChildCount();
        this.childAge=roomBookingRequest.getChildsAge();
        this.maxChildAgeLimit = roomBookingRequest.getMaxChildAgeLimit();
        this.totalAmount = roomBookingRequest.getTotalRoomCost();
        this.roomBookingDtlEntities = roomBookingRequest.getRoomBookingDtlEntities();
    }

}
