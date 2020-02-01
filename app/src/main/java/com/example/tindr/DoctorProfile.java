package com.example.tindr;

public class DoctorProfile {
    private Profile profile;
    private Practice[] practices;
    private Specialty[] specialties;

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setPractices(Practice[] practices) {
        this.practices = practices;
    }

    public void setSpecialties(Specialty[] specialties) {
        this.specialties = specialties;
    }

    public Profile getProfile() {
        return profile;
    }

    public Practice[] getPractices() {
        return practices;
    }

    public Specialty[] getSpecialties() {
        return specialties;
    }
}
