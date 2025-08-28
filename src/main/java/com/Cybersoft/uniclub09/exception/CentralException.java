package com.Cybersoft.uniclub09.exception;

import com.Cybersoft.uniclub09.payload.respone.BaseRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(Exception e) {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(500);
        baseRespone.setMessage(e.getMessage());
        baseRespone.setData(null);

        return ResponseEntity.ok(baseRespone);
    }

    @ExceptionHandler(DataNotFoundExceptions.class)
    public ResponseEntity<?> handleDataNotFoundExceptions(Exception e) {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(404);
        baseRespone.setMessage(e.getMessage());
        baseRespone.setData(null);

        return ResponseEntity.ok(baseRespone);
    }

    @ExceptionHandler(MethodNotAllowExceptions.class)
    public ResponseEntity<?> handleMethodNotAllowExceptions(Exception e) {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(405);
        baseRespone.setMessage(e.getMessage());
        baseRespone.setData(null);

        return ResponseEntity.ok(baseRespone);
    }
}
