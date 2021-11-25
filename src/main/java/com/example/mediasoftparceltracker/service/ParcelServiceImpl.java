package com.example.mediasoftparceltracker.service;

import com.example.mediasoftparceltracker.dao.ParcelRepository;
import com.example.mediasoftparceltracker.dto.ParcelDto;
import com.example.mediasoftparceltracker.dto.PostOfficeDto;
import com.example.mediasoftparceltracker.errors.ParcelNotFoundException;
import com.example.mediasoftparceltracker.model.Parcel;
import com.example.mediasoftparceltracker.model.PostOffice;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelServiceImpl implements ParcelService {
    private ParcelRepository parcelRepository;

    @Override
    public void createShipment(ParcelDto parcelDto) {
        PostOffice postOffice = new PostOffice(
                parcelDto.getPostOffice().getIndex(),
                parcelDto.getPostOffice().getName(),
                parcelDto.getPostOffice().getAddress()
                );

        Parcel parcel = new Parcel(
                parcelDto.getParcelType(),
                parcelDto.getRecipientIndex(),
                parcelDto.getSenderIndex(),
                parcelDto.getRecipientAddress(),
                parcelDto.getRecipientName(),
                Parcel.ParcelStatus.SHIPMENT_REGISTERED,
                List.of(postOffice)
                );

        this.parcelRepository.save(parcel);
    }

    @Override
    public void registerIntermediatePostOffice(Long id, PostOfficeDto postOfficeDto) {
        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFoundException(id));
        parcel.setParcelStatus(Parcel.ParcelStatus.ARRIVED_AT_INTERMEDIATE_POST_OFFICE);
        PostOffice postOffice = new PostOffice(
                postOfficeDto.getIndex(),
                postOfficeDto.getName(),
                postOfficeDto.getAddress()
        );
        parcel.getMovementHistory().add(postOffice);
        parcelRepository.save(parcel);
    }

    @Override
    public Parcel findParcelById(Long id) {
        return parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFoundException(id));
    }

    @Override
    public void leaveParcelFromPostOffice(Long id) {
        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFoundException(id));
        parcel.setParcelStatus(Parcel.ParcelStatus.LEAVED_FROM_INTERMEDIATE_POST_OFFICE);
        this.parcelRepository.save(parcel);
    }

    @Override
    public void registerParcelReceivedByRecipient(Long id) {
        Parcel parcel = this.parcelRepository.findById(id)
                        .orElseThrow(() -> new ParcelNotFoundException(id));
        parcel.setParcelStatus(Parcel.ParcelStatus.RECEIVED_BY_RECIPIENT);
        this.parcelRepository.save(parcel);
    }
}
