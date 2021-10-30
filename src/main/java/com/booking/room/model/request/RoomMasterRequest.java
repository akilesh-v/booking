package com.booking.room.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomMasterRequest {
    private String roomId;
    private Integer maxAdult;
    private Integer maxChild;
    private Integer maxChildAgeLimit;
}
