package com.aaa.huahui.config.exception;

import java.util.ArrayList;

public class NewUserFailException extends Exception {
    private ArrayList<String> errors;

    public NewUserFailException(ArrayList<String> errors) {
        this.errors = errors;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }
}
