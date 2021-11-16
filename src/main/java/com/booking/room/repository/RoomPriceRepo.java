package com.booking.room.repository;

import com.booking.room.entity.RoomPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomPriceRepo extends JpaRepository<RoomPriceEntity,Long> {

    @Query(value = "select * from room_price where room_id = :roomId and (price_range_from <= :priceRangeFrom or price_range_to >= :priceRangeTo) order by price_range_from asc",nativeQuery = true)
    List<RoomPriceEntity> findPriceRangeOfRoom(@Param(value = "roomId") Long roomId,@Param(value = "priceRangeFrom") Date priceRangeFrom,@Param(value = "priceRangeTo") Date priceRangeTo);

}
