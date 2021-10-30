package com.booking.room.service;

import com.booking.room.entity.RoomPriceEntity;
import com.booking.room.entity.RoomsMasterEntity;
import com.booking.room.model.response.ApiResponse;
import com.booking.room.model.request.RoomMasterRequest;
import com.booking.room.model.request.RoomPriceRequest;
import com.booking.room.model.response.RoomMasterResponse;
import com.booking.room.model.response.RoomPriceResponse;
import com.booking.room.repository.RoomBookingRepo;
import com.booking.room.repository.RoomMasterRepo;
import com.booking.room.repository.RoomPriceRepo;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomMasterRepo roomMasterRepo;

    private final RoomPriceRepo roomPriceRepo;

    private final RoomBookingRepo roomBookingRepo;

    public RoomService(RoomMasterRepo roomMasterRepo, RoomPriceRepo roomPriceRepo, RoomBookingRepo roomBookingRepo) {
        this.roomMasterRepo = roomMasterRepo;
        this.roomPriceRepo = roomPriceRepo;
        this.roomBookingRepo = roomBookingRepo;
    }


    public RoomMasterResponse addRooms(RoomMasterRequest roomData){
        try {
            //ValidateRoom Master Data

            //Save RoomMaster Data
            RoomsMasterEntity roomSaveData = roomMasterRepo.save(new RoomsMasterEntity(roomData));
            return new RoomMasterResponse(true,"Room Created Successfully", roomSaveData.getId());
        }catch (Exception ex){
            ex.printStackTrace();
            return new RoomMasterResponse(false,"Exception Occurred while Processing Room Master Data");
        }

    }

    public RoomPriceResponse addRoomPrice(RoomPriceRequest roomPriceData){
        try{
            RoomPriceEntity priceSaveData =roomPriceRepo.save(new RoomPriceEntity(roomPriceData));
            return new RoomPriceResponse(true,"Room Price Created Successfully",roomPriceData.getRoomId());
        }catch (Exception e){
            e.printStackTrace();
            return new RoomPriceResponse(false,"Exception Occurred while Processing Room Price Data");
        }
    }

}
