package com.example.mediasoftparceltracker.controller;

import com.example.mediasoftparceltracker.dto.ParcelDto;
import com.example.mediasoftparceltracker.dto.PostOfficeDto;
import com.example.mediasoftparceltracker.model.Parcel;
import com.example.mediasoftparceltracker.service.ParcelService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0")
public class ParcelController {
    @Autowired
    private ParcelService parcelService;

    @PostMapping("/register")
    public String shipmentRegistration(@RequestBody ParcelDto parcelDto) {
        parcelService.createShipment(parcelDto);
        return "Parcel has been successfully created!";
    }

    @PatchMapping("/{id}/intermediate")
    public String registerIntermediatePostOffice(@PathVariable(name = "id") Integer id, @RequestBody PostOfficeDto postOfficeDto) throws NotFoundException {
        parcelService.registerIntermediatePostOffice(id, postOfficeDto);
        return "Intermediate post office has been successfully added!";
    }

    @GetMapping("/{id}")
    public Parcel getParcelInfo(@PathVariable(name = "id") Integer id) throws NotFoundException {
        return parcelService.findParcelById(id);
    }

    @PatchMapping("/{id}/leave")
    public String registerLeaveFromIntermediatePostOffice(@PathVariable(name = "id") Integer id) {
        parcelService.leaveParcelFromPostOffice(id);
        return "Parcel has been successfully leaved from post office.";
    }

    @PatchMapping("/{id}/received")
    public String registerParcelReceivedByRecipient(@PathVariable(name = "id") Integer id) {
        parcelService.registerParcelReceivedByRecipient(id);
        return "Parcel has been successfully received by recipient";
    }
}
