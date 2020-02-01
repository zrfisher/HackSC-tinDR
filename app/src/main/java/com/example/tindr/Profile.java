package com.example.tindr;

public class Profile {
    private String first_name, last_name, image_url, gender, bio;

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLanguages(Language[] languages) {
        this.languages = languages;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }

    public Language[] getLanguages() {
        return languages;
    }

    private Language[] languages;
}
