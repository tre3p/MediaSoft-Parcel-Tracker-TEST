package com.example.mediasoftparceltracker.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer index;
    private String name;
    private String address;

    public PostOffice(Integer index, String name, String address) {
        this.index = index;
        this.name = name;
        this.address = address;
    }
}
