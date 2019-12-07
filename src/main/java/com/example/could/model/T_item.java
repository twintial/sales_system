package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class T_item {
    private int item_id;
    private int store_id;
    private String item_name;
    private int sales;
    private float cost_price;
    private float unit_price;
    private int stock;
}
