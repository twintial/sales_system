package com.example.could.model;

import lombok.Data;

import java.util.Date;

@Data
public class T_sales_log {
    private int item_id;
    private int user_id;
    private Date purchase_time;
    private int purchase_volume;
}
