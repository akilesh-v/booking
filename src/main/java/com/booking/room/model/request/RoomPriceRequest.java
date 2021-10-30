package com.booking.room.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomPriceRequest {
    private Long roomId;
    private Date priceRangeFrom;
    private Date priceRangeTo;
    private Double basePrice;
    private Double extraAdultPrice;
    private Double extraChildPrice;
}
