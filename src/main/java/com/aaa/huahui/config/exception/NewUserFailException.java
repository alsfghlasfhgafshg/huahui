package com.aaa.huahui.config.exception;

import java.util.ArrayList;

public class NewUserFailException extends Exception {
    private ArrayList<String> errors;

    public NewUserFailException(ArrayList<String> errors) {
        this.errors = errors;
    }

    public String getErrors() {
        StringBuilder stringBuilder=new StringBuilder();
        for (String error : errors) {
            stringBuilder.append(error);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
