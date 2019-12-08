package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_store {
    private Integer store_id;
    private String store_name;
    private Date opening_date;
    private String store_account;
    private String store_password;
}
