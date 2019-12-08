package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_sales_log {
    private Integer item_id;
    private Integer user_id;
    private Date purchase_time;
    private Integer purchase_volume;
}
