package com.example.berkayyalcincase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Earthquake {
    private String country;
    private String place;
    private double magnitude;
    private String date;
    private String time;
}