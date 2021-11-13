package com.example.mediasoftparceltracker.service;

import com.example.mediasoftparceltracker.dto.ParcelDto;
import com.example.mediasoftparceltracker.dto.PostOfficeDto;
import com.example.mediasoftparceltracker.model.Parcel;
import javassist.NotFoundException;

public interface ParcelService {
    void createShipment(ParcelDto parcelDto);
    void registerIntermediatePostOffice(Integer id, PostOfficeDto postOfficeDto) throws NotFoundException;
    Parcel findParcelById(Integer id) throws NotFoundException;
    void leaveParcelFromPostOffice(Integer id);
    void registerParcelReceivedByRecipient(Integer id);
}
