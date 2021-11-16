package com.booking.room.controller;

import com.booking.room.common.constants.CommonConstants;
import com.booking.room.common.controller.BaseController;
import com.booking.room.common.deserializer.DateDeserializer;
import com.booking.room.model.request.RoomBookingRequest;
import com.booking.room.model.request.RoomMasterRequest;
import com.booking.room.model.request.RoomPriceRequest;
import com.booking.room.model.response.RoomBookingResponse;
import com.booking.room.model.response.RoomMasterResponse;
import com.booking.room.model.response.RoomPriceResponse;
import com.booking.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import static com.booking.room.common.utils.DateUtils.parseDate;
import static com.booking.room.common.utils.DateUtils.getSupportedLocale;

@RestController
public class RoomController extends BaseController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add_room")
    public ResponseEntity<RoomMasterResponse> addRoom(RoomMasterRequest roomData){
        RoomMasterResponse roomMaster = roomService.addRooms(roomData, CommonConstants.ADD);
        return roomMaster.isStatus() ? ResponseEntity.ok(roomMaster) : ResponseEntity.badRequest().body(roomMaster);
    }

    @PostMapping("/add_room_price")
    public ResponseEntity<RoomPriceResponse> addRoomPrice(RoomPriceRequest roomPriceData){
        roomPriceData.setPriceRangeFrom(parseDate(roomPriceData.getPriceFrom(), DateDeserializer.dateFormat));
        roomPriceData.setPriceRangeTo(parseDate(roomPriceData.getPriceTo(), DateDeserializer.dateFormat));
        RoomPriceResponse roomPriceResponse= roomService.addRoomPrice(roomPriceData,CommonConstants.ADD);
        return roomPriceResponse.isStatus()? ResponseEntity.ok(roomPriceResponse): ResponseEntity.badRequest().body(roomPriceResponse);
    }

    @PostMapping("/book_room")
    public ResponseEntity<RoomBookingResponse> bookRoom(RoomBookingRequest roomBookingData){
        roomBookingData.setBookingFromDate(parseDate(roomBookingData.getBookingFrom(), DateDeserializer.dateFormat));
        roomBookingData.setBookingToDate(parseDate(roomBookingData.getBookingTo(), DateDeserializer.dateFormat));
        RoomBookingResponse roomBookingResponse = roomService.bookRoom(roomBookingData,CommonConstants.ADD);
        return roomBookingResponse.isStatus()? ResponseEntity.ok(roomBookingResponse): ResponseEntity.badRequest().body(roomBookingResponse);
    }



}
