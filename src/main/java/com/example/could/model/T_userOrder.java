package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_userOrder {
    private Date purchase_time;
    private Integer purchase_volume;
    private String item_name;
    private Float unit_price;
}
