package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesLogViewModel {
    String item_name;
    String real_name;
    Integer age;
    Date purchase_time;
    Integer purchase_volume;
    Float unit_price;
    Float cost_price;
    Float total_price;

}
