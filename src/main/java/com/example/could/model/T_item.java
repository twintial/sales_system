package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_item {
    private Integer item_id;
    private Integer store_id;
    private String item_name;
    private Integer sales;
    private Float cost_price;
    private Float unit_price;
    private Integer stock;
}
