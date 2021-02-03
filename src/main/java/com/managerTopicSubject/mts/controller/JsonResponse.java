package com.managerTopicSubject.mts.controller;

import java.util.HashMap;

public class JsonResponse extends HashMap<String, Object> {

    public void putSuccess(boolean success) {
        put("success", success);
    }

    public void putResult(Object object) {
        put("result", object);
    }

}
