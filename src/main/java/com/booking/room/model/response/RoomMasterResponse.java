package com.booking.room.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomMasterResponse extends StatusAndMessageAPIResponse {
    private Long roomId;

    public RoomMasterResponse(boolean pstatus, String pMessage, Long roomId) {
        super(pstatus, pMessage);
        this.roomId = roomId;
    }

    public RoomMasterResponse(boolean pStatus, String pMessage) {
        super(pStatus, pMessage);
    }

    public static RoomMasterResponse success(Long roomId) {
        return new RoomMasterResponse(true, "Success", roomId);
    }

    public static RoomMasterResponse error(String pMessage) {
        return new RoomMasterResponse(false, pMessage);
    }

    public static RoomMasterResponse failed(String pMessage) {
        return new RoomMasterResponse(false, pMessage);
    }

}
