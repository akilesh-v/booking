package com.booking.room.model.response;

import com.booking.room.model.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusAndMessageAPIResponse {
    private boolean status;
    private String message;

    public StatusAndMessageAPIResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Object success(String message) {
        return new ApiResponse("true", message);
    }

    public static Object error(String errorMsg) {
        return new ApiResponse("error", errorMsg);
    }

    public static Object failed(String message) {
        return new ApiResponse("false", message);
    }
}
