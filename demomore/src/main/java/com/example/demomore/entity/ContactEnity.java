package com.example.demomore.entity;

/**
 * Created by benchengzhou on 2017/4/23.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：联系人实体类
 */

public class ContactEnity {
    String id = "name_zbc_" ;

    public ContactEnity(String id, String name, String phone, String email, String street, String place) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.place = place;
    }

    String name = "name_zbc_" ;
    String phone = "phone_" ;
    String email = "email_";
    String street = "street_" ;
    String place = "place_北京_" ;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "ContactEnity{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
