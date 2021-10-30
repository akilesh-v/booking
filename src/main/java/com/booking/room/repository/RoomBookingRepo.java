package com.booking.room.repository;

import com.booking.room.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomBookingRepo extends JpaRepository<RoomBookingEntity,Long> {

}
