package com.booking.room.entity;

import com.booking.room.model.request.RoomPriceRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "room_price")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class RoomPriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_price_id")
    private Long roomPriceId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "price_range_from")
    private Date priceRangeFrom;

    @Column(name = "price_range_to")
    private Date priceRangeTo;

    @Column(name = "base_price")
    private Double basePrice;

    @Column(name = "extra_adult_price")
    private Double extraAdultPrice;

    @Column(name = "extra_child_price")
    private Double extraChildPrice;

    @Column(name = "base_adult_count")
    private Integer baseAdultCount;

    public RoomPriceEntity(RoomPriceRequest roomPriceRequest){
        this.roomId= roomPriceRequest.getRoomId();
        this.basePrice = roomPriceRequest.getBasePrice();
        this.priceRangeFrom=roomPriceRequest.getPriceRangeFrom();
        this.priceRangeTo=roomPriceRequest.getPriceRangeTo();
        this.extraAdultPrice=roomPriceRequest.getExtraAdultPrice();
        this.extraChildPrice=roomPriceRequest.getExtraChildPrice();
        this.baseAdultCount=2;
    }

}
