package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_user {
    private Integer user_id;
    private String real_name;
    private Integer age;
    private String profession;
    private String user_account;
    private String user_password;
}
