package com.booking.room.repository;


import com.booking.room.entity.RoomsMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMasterRepo extends JpaRepository<RoomsMasterEntity,Long> {

}
