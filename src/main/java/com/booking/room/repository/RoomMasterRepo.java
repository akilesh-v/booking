package com.booking.room.repository;


import com.booking.room.entity.RoomsMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomMasterRepo extends JpaRepository<RoomsMasterEntity,Long> {
    Optional<RoomsMasterEntity> findByRoomId(String roomId);
}
