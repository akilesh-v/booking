package com.booking.room.repository;

import com.booking.room.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomBookingRepo extends JpaRepository<RoomBookingEntity, Long> {
    List<RoomBookingEntity> findByRoomIdAndBookingFromDateGreaterThanEqualAndBookingToDateGreaterThanEqual(Long roomId, Date bookingFrom, Date bookingTo);
}
