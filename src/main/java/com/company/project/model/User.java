package com.company.project.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_user")
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "pass_word")
    private String password;

    @Column(name = "nick_name")
    private String nickname;

    private Integer sex;

    @Column(name = "register_date")
    private Timestamp registerDate;

}
