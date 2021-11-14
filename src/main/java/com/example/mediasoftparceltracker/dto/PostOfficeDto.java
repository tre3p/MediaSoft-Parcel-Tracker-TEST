package com.example.mediasoftparceltracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostOfficeDto {
    private Integer index;
    private String name;
    private String address;
}
