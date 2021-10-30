package com.booking.room.repository;

import com.booking.room.entity.RoomPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPriceRepo extends JpaRepository<RoomPriceEntity,Long> {
}
