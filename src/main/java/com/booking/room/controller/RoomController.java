package com.booking.room.controller;

import com.booking.room.model.response.ApiResponse;
import com.booking.room.model.request.RoomMasterRequest;
import com.booking.room.model.request.RoomPriceRequest;
import com.booking.room.model.response.RoomMasterResponse;
import com.booking.room.model.response.RoomPriceResponse;
import com.booking.room.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    RoomService iRoomService;

    @PostMapping("/add_room")
    public ResponseEntity<RoomMasterResponse> addRoom(RoomMasterRequest roomData){
        RoomMasterResponse roomMaster = iRoomService.addRooms(roomData);
        return roomMaster.isStatus() ? ResponseEntity.ok(roomMaster) : ResponseEntity.badRequest().body(roomMaster);
    }

    @PostMapping("/add_room_price")
    public ResponseEntity<RoomPriceResponse> addRoomPrice(RoomPriceRequest roomPriceData){
        RoomPriceResponse roomPriceResponse=iRoomService.addRoomPrice(roomPriceData);
        return roomPriceResponse.isStatus()? ResponseEntity.ok(roomPriceResponse): ResponseEntity.badRequest().body(roomPriceResponse);
    }



}
