package com.example.mediasoftparceltracker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ParcelType parcelType;
    private Integer recipientIndex;
    private Integer senderIndex;
    private String recipientAddress;
    private String recipientName;
    private ParcelStatus parcelStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PostOffice> movementHistory;


    public enum ParcelStatus {
        SHIPMENT_REGISTERED,
        ARRIVED_AT_INTERMEDIATE_POST_OFFICE,
        LEAVED_FROM_INTERMEDIATE_POST_OFFICE,
        RECEIVED_BY_RECIPIENT
    }

    public enum ParcelType {
        LETTER,
        PARCEL,
        BOOK_POST,
        CARD
    }
}

