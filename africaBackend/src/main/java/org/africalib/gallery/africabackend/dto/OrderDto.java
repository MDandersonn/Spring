package org.africalib.gallery.africabackend.dto;

import lombok.Getter;

@Getter
public class OrderDto {
//post에서 넘어온것 request객체
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private String items;
}