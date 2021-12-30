package com.juran.crowd.entity;

/**
 * 作者： Juran on 2021/12/30 19:08
 * 作者博客：iit.la
 */
public class Address {
    private String province;
    private String city;
    private String street;
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public Address(){

    }
    public Address(String province, String city, String street) {
        super();
        this.province = province;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
