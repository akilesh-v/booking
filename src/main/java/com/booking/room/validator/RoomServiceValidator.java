package com.booking.room.validator;

import com.booking.room.common.constants.CommonConstants;
import com.booking.room.constants.RoomResponseConstants;
import com.booking.room.entity.RoomBookingEntity;
import com.booking.room.entity.RoomPriceEntity;
import com.booking.room.entity.RoomsMasterEntity;
import com.booking.room.model.request.RoomBookingRequest;
import com.booking.room.model.request.RoomMasterRequest;
import com.booking.room.model.request.RoomPriceRequest;
import com.booking.room.repository.RoomBookingRepo;
import com.booking.room.repository.RoomMasterRepo;
import com.booking.room.repository.RoomPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RoomServiceValidator {

    @Autowired
    private RoomMasterRepo roomMasterRepo;

    @Autowired
    private RoomBookingRepo roomBookingRepo;

    @Autowired
    private RoomPriceRepo roomPriceRepo;

    public String validateRoomMaster(RoomMasterRequest roomMasterData, String operationType) throws Exception {
        if (checkRoomNumberAlreadyExists(roomMasterData.getRoomId()) && CommonConstants.ADD.equals(operationType))
            return RoomResponseConstants.DUPLICATE_ROOM_ID_PROVIDED;
        else if (0 >= roomMasterData.getMaxAdult())
            return RoomResponseConstants.INVALID_MAX_ADULT_AGE;
        else if (0 < roomMasterData.getMaxChild() && 18 < roomMasterData.getMaxChildAgeLimit())
            return RoomResponseConstants.INVALID_CHILD_AGE_SPECIFIED;
        return CommonConstants.SUCCESS;
    }

    public boolean checkRoomNumberAlreadyExists(String roomId) throws Exception {
        return roomMasterRepo.findByRoomId(roomId).isPresent();
    }

    public boolean checkRoomIdExists(Long roomId) throws Exception {
        return roomMasterRepo.findById(roomId).isPresent();
    }

    public String validateRoomPrice(RoomPriceRequest roomPriceRequest, String operationType) throws Exception {
        if (!checkRoomIdExists(roomPriceRequest.getRoomId()))
            return RoomResponseConstants.INVALID_ROOM_ID;
        if (0 >= roomPriceRequest.getBasePrice())
            return RoomResponseConstants.INVALID_ROOM_PRICE;
        return CommonConstants.SUCCESS;
    }

    public String validateBooking(RoomBookingRequest roomBookingData, String operationType) throws Exception {
        if (checkRoomAvailability(roomBookingData))
            return RoomResponseConstants.ROOM_UNAVAILABLE;
        else if (!checkRoomAlreadyBooked(roomBookingData))
            return RoomResponseConstants.ROOM_ALREADY_BOOKED;
        else if (checkAdultCountValidation(roomBookingData))
            return RoomResponseConstants.MAX_ADULTS_EXCEEDED;
        else if (checkChildCountValidation(roomBookingData))
            return RoomResponseConstants.MAX_CHILDREN_EXCEEDED;
        return CommonConstants.SUCCESS;
    }

    public boolean checkRoomAvailability(RoomBookingRequest roomBookingRequest) {
        return roomPriceRepo.findPriceRangeOfRoom(roomBookingRequest.getRoomId(), roomBookingRequest.getBookingFromDate(), roomBookingRequest.getBookingToDate()).isEmpty();
    }

    public boolean checkRoomAlreadyBooked(RoomBookingRequest roomBookingRequest) {
        return roomBookingRepo.findByRoomIdAndBookingFromDateGreaterThanEqualAndBookingToDateGreaterThanEqual(roomBookingRequest.getRoomId(), roomBookingRequest.getBookingFromDate(), roomBookingRequest.getBookingToDate()).isEmpty();
    }

    public boolean checkAdultCountValidation(RoomBookingRequest roomBookingRequest) {
        roomBookingRequest.setProcessAdultCount(roomBookingRequest.getAdultCount());
        roomBookingRequest.setProcessChildCount(roomBookingRequest.getChildsAge().size());
        RoomsMasterEntity roomsMaster = roomMasterRepo.findById(roomBookingRequest.getRoomId()).get();
        roomBookingRequest.setMaxAdultCount(roomsMaster.getMaxAdult());
        roomBookingRequest.setMaxChildCount(roomsMaster.getMaxChild());
        roomBookingRequest.setMaxChildAgeLimit(roomsMaster.getMaxChildAgeLimit());
        for (Integer childAge : roomBookingRequest.getChildsAge()) {
            if (childAge > roomsMaster.getMaxChildAgeLimit()) {
                roomBookingRequest.setProcessAdultCount(roomBookingRequest.getProcessAdultCount() + 1);
                roomBookingRequest.setProcessChildCount(roomBookingRequest.getProcessChildCount() - 1);
            }
        }
        return roomBookingRequest.getProcessAdultCount() > roomBookingRequest.getMaxAdultCount();
    }

    public boolean checkChildCountValidation(RoomBookingRequest roomBookingRequest) {
        return roomBookingRequest.getProcessChildCount() > roomBookingRequest.getMaxChildCount();
    }


}
