package com.example.mediasoftparceltracker.dto;

import com.example.mediasoftparceltracker.model.Parcel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelDto {
    private Parcel.ParcelType parcelType;
    private Integer recipientIndex;
    private Integer senderIndex;
    private String recipientAddress;
    private String recipientName;
    private Parcel.ParcelStatus parcelStatus;
    private PostOfficeDto postOffice;
}
