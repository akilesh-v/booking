package com.booking.room.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private String status;
    private String message;
    private Object object;

    ApiResponse(String status, String message){
        this.status = status;
        this.message = message;
    }

    ApiResponse(Object obj, String status, String message){
        this.status = status;
        this.message = message;
        this.object = obj;
    }


    public static ApiResponse success(Object obj,String message) {
        return new ApiResponse(obj,"true", message);
    }

    public static Object error(String errorMsg) {
        return new ApiResponse("error", errorMsg);
    }

    public static Object failed(String msg) {
        return new ApiResponse("false", msg);
    }
}
