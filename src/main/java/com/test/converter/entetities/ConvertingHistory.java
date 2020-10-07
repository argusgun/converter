package com.test.converter.entetities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ConvertingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User user;
    private String log;
    private String time;
}
