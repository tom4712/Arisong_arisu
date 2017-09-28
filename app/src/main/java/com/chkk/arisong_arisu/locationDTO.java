package com.chkk.arisong_arisu;

/**
 * Created by CX62 on 2017-09-26.
 */

public class locationDTO {

    private long wedo;
    private long gyoundo;
    private String LocationName;
    private String LcatesmallName;

    public  locationDTO(){}

    public locationDTO(String LocationName, String LcatesmallName, long gyoundo, long wedo){
        this.LocationName = LocationName;
        this.LcatesmallName = LcatesmallName;
        this.gyoundo = gyoundo;
        this.wedo = wedo;
    }

    public long getWedo() {
        return wedo;
    }

    public void setWedo(long wedo) {
        this.wedo = wedo;
    }

    public long getGyoundo() {
        return gyoundo;
    }

    public void setGyoundo(long gyoundo) {
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
