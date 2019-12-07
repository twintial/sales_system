package com.example.could.model;

import lombok.Data;

import java.util.Date;

@Data
public class T_store {
    private int store_id;
    private String store_name;
    private Date opening_date;
    private String store_account;
    private String store_password;
}
