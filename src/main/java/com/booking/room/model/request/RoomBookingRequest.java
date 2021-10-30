package com.booking.room.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingRequest {
    private Long roomId;
    private Integer maxAdult;
    private Date bookingFromDate;
    private Date bookingToDate;
    private Integer adultCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Integer> childsAge;

}
