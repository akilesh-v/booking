package com.booking.room.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@Table(name="booking_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingDtlEntity {

    @Column(name="room_id")
    private Long roomId;

    @Column(name = "price_from_date")
    private Date priceFromDate;

    @Column(name = "price_to_date")
    private Date priceToDate;

    @Column(name="base_price")
    private Double basePrice;

    @Column(name="extra_adult_price")
    private Double extraAdultPrice;

    @Column(name="extra_child_price")
    private Double extraChildPrice;

    @Column(name="total_period_cost")
    private Double totalPeriodCost;
}
