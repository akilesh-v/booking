package com.booking.room.model.request;

import com.booking.room.entity.RoomBookingDtlEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String bookingFrom;
    private String bookingTo;
    private Integer adultCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Integer> childsAge;

    @JsonIgnore
    private Integer processAdultCount;
    @JsonIgnore
    private Integer processChildCount;
    @JsonIgnore
    private Integer maxChildCount;
    @JsonIgnore
    private Integer maxAdultCount;
    @JsonIgnore
    private Integer maxChildAgeLimit;
    @JsonIgnore
    private Integer minAdultCount;
    @JsonIgnore
    private Double totalRoomCost;
    @JsonIgnore
    private Date bookingFromDate;
    @JsonIgnore
    private Date bookingToDate;
    @JsonIgnore
    private List<RoomBookingDtlEntity> roomBookingDtlEntities;

}
