package com.Cybersoft.uniclub09.payload.respone;

import lombok.Data;

@Data
public class BaseRespone {
    private int code;
    private String message;
    private Object data;
}
