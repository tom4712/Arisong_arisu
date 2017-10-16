package com.chkk.arisong_arisu;

/**
 * Created by CX62 on 2017-09-26.
 */

public class locationDTO {

    private double wedo;
    private double gyoundo;
    private String LocationName;
    private String LcatesmallName;

    public  locationDTO(){}

    public locationDTO(String LocationName, String LcatesmallName, double gyoundo, double wedo){
        this.gyoundo = gyoundo;
        this.wedo = wedo;
        this.LocationName = LocationName;
        this.LcatesmallName = LcatesmallName;
    }



    public double getWedo() {
        return wedo;
    }

    public void setWedo(double wedo) {
        this.wedo = wedo;
    }

    public double getGyoundo() {
        return gyoundo;
    }

    public void setGyoundo(double gyoundo) {
        this.gyoundo = gyoundo;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getLcatesmallName() {
        return LcatesmallName;
    }

    public void setLcatesmallName(String lcatesmallName) {
        LcatesmallName = lcatesmallName;
    }
}
