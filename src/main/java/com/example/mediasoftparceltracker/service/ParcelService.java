package com.example.mediasoftparceltracker.service;

import com.example.mediasoftparceltracker.dto.ParcelDto;
import com.example.mediasoftparceltracker.dto.PostOfficeDto;
import com.example.mediasoftparceltracker.model.Parcel;
import javassist.NotFoundException;

public interface ParcelService {
    void createShipment(ParcelDto parcelDto);
    void registerIntermediatePostOffice(Long id, PostOfficeDto postOfficeDto) throws NotFoundException;
    Parcel findParcelById(Long id) throws NotFoundException;
    void leaveParcelFromPostOffice(Long id);
    void registerParcelReceivedByRecipient(Long id);
}
