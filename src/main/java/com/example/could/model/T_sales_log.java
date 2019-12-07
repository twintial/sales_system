package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class T_sales_log {
    private int item_id;
    private int user_id;
    private Date purchase_time;
    private int purchase_volume;
}
