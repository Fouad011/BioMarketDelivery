package com.example.biomarketdelivery.models;

public class UserModel {
    public String fullName, doB, gender, mobile, accountType, cni;
    public Address address;

    public UserModel() {
    }

    public UserModel(String fullName, String doB, String gender, String mobile, String accountType, String cni) {
        this.fullName = fullName;
        this.doB = doB;
        this.gender = gender;
        this.mobile = mobile;
        this.accountType = accountType;
        this.cni = cni;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDoB() {
        return doB;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getLocation(){
        return address.getCountry() + ", " + address.getCity() + ", " + address.getStreet() + " " + address.getZipCode();
    }
}
