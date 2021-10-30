package com.booking.room.entity;

import com.booking.room.model.request.RoomMasterRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="rooms_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomsMasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="room_id")
    private String roomId;

    @Column(name="max_adult")
    private Integer maxAdult;

    @Column(name="max_child")
    private Integer maxChild;

    @Column(name="max_child_age_limit")
    private Integer maxChildAgeLimit;

    public RoomsMasterEntity(RoomMasterRequest roomMasterRequest) {
        this.roomId = roomMasterRequest.getRoomId();
        this.maxAdult = roomMasterRequest.getMaxAdult();
        this.maxChild = roomMasterRequest.getMaxChild();
        this.maxChildAgeLimit = roomMasterRequest.getMaxChildAgeLimit();
    }
}
