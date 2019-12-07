package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SalesLogViewModel {
    String item_name;
    String real_name;
    int age;
    Date purchase_time;
    int purchase_volume;
    float unit_price;
    float total_price;

}
