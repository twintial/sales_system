package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_cart {
    private Integer user_id;
    // 这个不知道用什么类型好
    private Integer item_id;
}
