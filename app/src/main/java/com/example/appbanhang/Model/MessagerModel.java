package com.example.appbanhang.Model;

public class MessagerModel {
    boolean success;
    String message;
    String name;
    String iddonhang;

    public MessagerModel(boolean success, String message, String name, String iddonhang) {
        this.success = success;
        this.message = message;
        this.name = name;
        this.iddonhang = iddonhang;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIddonhang() {
        return iddonhang;
    }

    public void setIddonhang(String iddonhang) {
        this.iddonhang = iddonhang;
    }
}

