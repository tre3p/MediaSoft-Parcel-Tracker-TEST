package com.example.mediasoftparceltracker.service;

import com.example.mediasoftparceltracker.dao.ParcelRepository;
import com.example.mediasoftparceltracker.dto.ParcelDto;
import com.example.mediasoftparceltracker.dto.PostOfficeDto;
import com.example.mediasoftparceltracker.model.Parcel;
import com.example.mediasoftparceltracker.model.PostOffice;
import javassist.NotFoundException;

import java.util.List;

public class ParcelServiceImpl implements ParcelService {
    private ParcelRepository parcelRepository;

    public ParcelServiceImpl(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public void createShipment(ParcelDto parcelDto) {
        Parcel parcel = new Parcel();

        parcel.setParcelType(parcelDto.getParcelType());
        parcel.setParcelStatus(Parcel.ParcelStatus.SHIPMENT_REGISTERED);
        parcel.setRecipientAddress(parcelDto.getRecipientAddress());
        parcel.setRecipientIndex(parcelDto.getRecipientIndex());
        parcel.setSenderIndex(parcelDto.getSenderIndex());
        parcel.setRecipientName(parcelDto.getRecipientName());

        PostOffice postOffice = new PostOffice();
        postOffice.setName(parcelDto.getPostOffice().getName());
        postOffice.setIndex(parcelDto.getPostOffice().getIndex());
        postOffice.setAddress(parcelDto.getPostOffice().getAddress());

        parcel.setMovementHistory(List.of(postOffice));

        this.parcelRepository.save(parcel);
    }

    @Override
    public void registerIntermediatePostOffice(Integer id, PostOfficeDto postOfficeDto) throws NotFoundException {
        Parcel parcel = parcelRepository.findParcelById(id);
        if (!(parcel == null)) {
            parcel.setParcelStatus(Parcel.ParcelStatus.ARRIVED_AT_INTERMEDIATE_POST_OFFICE);
            PostOffice postOffice = new PostOffice();
            postOffice.setIndex(postOfficeDto.getIndex());
            postOffice.setName(postOfficeDto.getName());
            postOffice.setAddress(postOfficeDto.getAddress());

            parcel.getMovementHistory().add(postOffice);
            parcelRepository.save(parcel);
            return;
        }
        throw new NotFoundException("Parcel with such ID is not exist");
    }

    @Override
    public Parcel findParcelById(Integer id) throws NotFoundException {
        Parcel parcel = parcelRepository.findParcelById(id);
        if (!(parcel == null)) {
            return parcel;
        }
        return null;
    }

    @Override
    public void leaveParcelFromPostOffice(Integer id) {
        Parcel parcel = this.parcelRepository.findParcelById(id);
        parcel.setParcelStatus(Parcel.ParcelStatus.LEAVED_FROM_INTERMEDIATE_POST_OFFICE);
        this.parcelRepository.save(parcel);
    }

    @Override
    public void registerParcelReceivedByRecipient(Integer id) {
        Parcel parcel = this.parcelRepository.findParcelById(id);
        parcel.setParcelStatus(Parcel.ParcelStatus.RECEIVED_BY_RECIPIENT);
        this.parcelRepository.save(parcel);
    }
}