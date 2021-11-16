package com.booking.room.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomBookingResponse extends StatusAndMessageAPIResponse{
    private Long roomBookingId;
    private Double roomCost;

    public RoomBookingResponse(boolean pstatus, String pMessage, Long roomBookingId,Double roomCost) {
        super(pstatus, pMessage);
        this.roomBookingId = roomBookingId;
        this.roomCost=roomCost;
    }

    public RoomBookingResponse(boolean pStatus, String pMessage) {
        super(pStatus, pMessage);
    }

    public static RoomBookingResponse success(Long roomId,Double roomCost) {
        return new RoomBookingResponse(true, "Success", roomId , roomCost);
    }

    public static RoomBookingResponse error(String pMessage) {
        return new RoomBookingResponse(false, pMessage);
    }

    public static RoomBookingResponse failed(String pMessage) {
        return new RoomBookingResponse(false, pMessage);
    }
}
