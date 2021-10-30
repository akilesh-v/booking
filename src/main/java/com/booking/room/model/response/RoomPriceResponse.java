package com.booking.room.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomPriceResponse extends StatusAndMessageAPIResponse{
    private Long roomId;

    public RoomPriceResponse(boolean pstatus, String pMessage, Long roomId) {
        super(pstatus,pMessage);
        this.roomId = roomId;
    }
    public RoomPriceResponse(boolean pStatus, String pMessage) {
        super(pStatus, pMessage);
    }
    public static RoomPriceResponse success(Long roomId) {
        return new RoomPriceResponse(true, "Success", roomId);
    }

    public static RoomPriceResponse error(String pMessage) {
        return new RoomPriceResponse(false, pMessage);
    }

    public static RoomPriceResponse failed(String pMessage) {
        return new RoomPriceResponse(false, pMessage);
    }
}
