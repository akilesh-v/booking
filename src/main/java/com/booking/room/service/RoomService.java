package com.booking.room.service;

import com.booking.room.common.constants.CommonConstants;
import com.booking.room.common.utils.DateUtils;
import com.booking.room.entity.RoomBookingDtlEntity;
import com.booking.room.entity.RoomBookingEntity;
import com.booking.room.entity.RoomPriceEntity;
import com.booking.room.entity.RoomsMasterEntity;
import com.booking.room.model.request.RoomBookingRequest;
import com.booking.room.model.request.RoomMasterRequest;
import com.booking.room.model.request.RoomPriceRequest;
import com.booking.room.model.response.RoomBookingResponse;
import com.booking.room.model.response.RoomMasterResponse;
import com.booking.room.model.response.RoomPriceResponse;
import com.booking.room.repository.RoomBookingRepo;
import com.booking.room.repository.RoomMasterRepo;
import com.booking.room.repository.RoomPriceRepo;
import com.booking.room.validator.RoomServiceValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomService {

    private static final Log logger = LogFactory.getLog(RoomService.class);

    @Autowired
    private RoomMasterRepo roomMasterRepo;

    @Autowired
    private RoomPriceRepo roomPriceRepo;

    @Autowired
    private RoomBookingRepo roomBookingRepo;

    @Autowired
    private RoomServiceValidator roomServiceValidator;

    public RoomMasterResponse addRooms(RoomMasterRequest roomData, String operationType) {
        try {
            String validateMsg = roomServiceValidator.validateRoomMaster(roomData, operationType);
            if (CommonConstants.SUCCESS.equals(validateMsg)) {
                RoomsMasterEntity roomSaveData = roomMasterRepo.save(new RoomsMasterEntity(roomData));
                return RoomMasterResponse.success(roomSaveData.getId());
            }
            return RoomMasterResponse.failed(validateMsg);
        } catch (Exception ex) {
            ex.printStackTrace();
            return RoomMasterResponse.error("Exception Occurred while Processing Room Master Data");
        }

    }

    public RoomPriceResponse addRoomPrice(RoomPriceRequest roomPriceData, String operationType) {
        try {
            String validateMsg = roomServiceValidator.validateRoomPrice(roomPriceData, operationType);
            if (CommonConstants.SUCCESS.equals(validateMsg)) {
                RoomPriceEntity priceSaveData = roomPriceRepo.save(new RoomPriceEntity(roomPriceData));
                return RoomPriceResponse.success(priceSaveData.getRoomId());
            }
            return RoomPriceResponse.failed(validateMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return RoomPriceResponse.error("Exception Occurred while Processing Room Price Data");
        }
    }

    /*    public InputRoomModel inputConverter(String input){
        InputRoomModel inputModel= new InputRoomModel();
        ArrayList<Integer> childAge = new ArrayList<>();
        String[] strArr = input.split(" ");
        inputModel.setInputRoomId(strArr[0]);
        inputModel.setCheckIn(strArr[1]);
        inputModel.setCheckOut(strArr[2]);
        inputModel.setAdultCount(Integer.parseInt(strArr[3]));

        if(strArr.length>4){
            for(String str : Arrays.copyOfRange(strArr,4,strArr.length)){
                childAge.add(Integer.parseInt(str));
            }
        }
        inputModel.setChildAge(childAge);
        return inputModel;
    }*/

    public RoomBookingResponse bookRoom(RoomBookingRequest bookingRequestData, String operationType) {
        try {
            String validateMsg = roomServiceValidator.validateBooking(bookingRequestData, operationType);
            if (CommonConstants.SUCCESS.equals(validateMsg)) {
                calculateRoomCost(bookingRequestData);
                RoomBookingEntity saveData = roomBookingRepo.save(new RoomBookingEntity(bookingRequestData));
                return new RoomBookingResponse(true, "Room Booked Successfully", saveData.getBookingId(), saveData.getTotalAmount());
            } else {
                return new RoomBookingResponse(false, "Room Booked Failed " + validateMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new RoomBookingResponse(false, "Exception Occurred while Booking room");
        }
    }


    public void calculateRoomCost(RoomBookingRequest roomBookingRequest) throws Exception {
        double roomCost = 0.00;
        roomBookingRequest.setTotalRoomCost(roomCost);
        List<RoomPriceEntity> priceValue = roomPriceRepo.findPriceRangeOfRoom(roomBookingRequest.getRoomId(), new SimpleDateFormat("dd-MM-yyyy").parse(roomBookingRequest.getBookingFrom()), new SimpleDateFormat("dd-MM-yyyy").parse(roomBookingRequest.getBookingTo()));
        List<RoomBookingDtlEntity> roomBookingDtl = new ArrayList<>();
        Date priceFromDate = roomBookingRequest.getBookingFromDate();
        Double periodCost = 0.00;
        for (RoomPriceEntity price : priceValue) {
            RoomBookingDtlEntity roomPerDayCostDtl = new RoomBookingDtlEntity();
            roomPerDayCostDtl.setRoomId(roomBookingRequest.getRoomId());
            roomPerDayCostDtl.setBasePrice(price.getBasePrice());
            roomPerDayCostDtl.setExtraAdultPrice(price.getExtraAdultPrice());
            roomPerDayCostDtl.setExtraChildPrice(price.getExtraChildPrice());
            roomPerDayCostDtl.setPriceFromDate(priceFromDate);
            roomPerDayCostDtl.setPriceToDate(roomBookingRequest.getBookingToDate());
            long totalDays = DateUtils.getDateDiffCount(priceFromDate, roomBookingRequest.getBookingToDate().after(price.getPriceRangeTo()) ? price.getPriceRangeTo() : roomBookingRequest.getBookingToDate());
            int adultCount = roomBookingRequest.getProcessAdultCount();
            if (adultCount > price.getBaseAdultCount()) {
                roomCost = price.getExtraAdultPrice() * (adultCount - price.getBaseAdultCount());
                adultCount = adultCount - price.getBaseAdultCount();
            }
            priceFromDate = price.getPriceRangeTo();
            periodCost=(roomCost + (price.getBasePrice() * adultCount) + (roomBookingRequest.getProcessChildCount() * price.getExtraChildPrice())) * totalDays;
            roomPerDayCostDtl.setTotalPeriodCost(periodCost);
            roomBookingDtl.add(roomPerDayCostDtl);
            roomBookingRequest.setRoomBookingDtlEntities(roomBookingDtl);
            roomBookingRequest.setTotalRoomCost(roomBookingRequest.getTotalRoomCost() + periodCost);
        }
    }


}
