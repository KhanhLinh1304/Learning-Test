package com.example.learning_dev_2024.payload.response;

import lombok.Data;

@Data
public class ResponseService {
    private Integer code;
    private Object object;

    public ResponseService(Integer code, Object object) {
        this.code = code;
        this.object = object;
    }


}
