package com.example.mediasoftparceltracker.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Parcel(ParcelType parcelType,
                  Integer recipientIndex,
                  Integer senderIndex,
                  String recipientAddress,
                  String recipientName,
                  ParcelStatus parcelStatus,
                  List<PostOffice> movementHistory) {
        this.parcelType = parcelType;
        this.recipientIndex = recipientIndex;
        this.senderIndex = senderIndex;
        this.recipientAddress = recipientAddress;
        this.recipientName = recipientName;
        this.parcelStatus = parcelStatus;
        this.movementHistory = movementHistory;
    }

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

