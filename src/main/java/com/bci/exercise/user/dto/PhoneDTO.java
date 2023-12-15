package com.bci.exercise.user.dto;

public class PhoneDTO {
    private long number;
    private int citycode;
    private String contrycode;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCitycode() {
        return citycode;
    }

    public void setCitycode(int citycode) {
        this.citycode = citycode;
    }

    public String getContrycode() {
        return contrycode;
    }
    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }

    public PhoneDTO(long number, int citycode, String contrycode) {
        this.number = number;
        this.citycode = citycode;
        this.contrycode = contrycode;
    }
    public PhoneDTO(){}
}
