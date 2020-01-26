package com.example.logincarcare;

public class modelstore {
    private String id;
    private String namestore;

    public modelstore(String id, String namestore, String path, String county, String latitude, String longitude) {
        this.id = id;
        this.namestore = namestore;
        this.path = path;
        this.county = county;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private String path;
    private String county;
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public modelstore(String id, String namestore, String path, String county) {
        this.id = id;
        this.namestore = namestore;
        this.path = path;
        this.county = county;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamestore() {
        return namestore;
    }

    public void setNamestore(String namestore) {
        this.namestore = namestore;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
