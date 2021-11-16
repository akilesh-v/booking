package com.booking.room.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomPriceRequest {
    private Long roomId;
    private String priceFrom;
    private String priceTo;
    private Double basePrice;
    private Double extraAdultPrice;
    private Double extraChildPrice;
    @JsonIgnoreProperties
    private Integer baseAdultCount;

    @JsonIgnoreProperties
    private Date priceRangeFrom;

    @JsonIgnoreProperties
    private Date priceRangeTo;
}
