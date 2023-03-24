package com.learning.swd.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHelper {

    public static ResponseEntity<Object> getResponse(Object obj, Integer totalPage, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("totalPage",totalPage);
        map.put("Status", 0 );
        map.put("Errors", "{ code:  " + 0 +", message: SUCCESS }");
        map.put("isOk", true);
        map.put("isError", false);
        map.put("Object", obj);
        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> getResponseSearchMess(HttpStatus status, ResponseMess responseMess) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Status", 0);
        map.put("Errors", responseMess);
        map.put("isOk", true);
        map.put("isError", false);
        map.put("Object", null);
        return new ResponseEntity<Object>(map, status);
    }
}
