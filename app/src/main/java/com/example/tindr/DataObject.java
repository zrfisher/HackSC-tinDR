package com.example.tindr;

public class DataObject {
    private DoctorProfile[] data;

    public void setData(DoctorProfile[] data) {
        this.data = data;
    }

    public DoctorProfile[] getData(){
        return data;
    }
}
