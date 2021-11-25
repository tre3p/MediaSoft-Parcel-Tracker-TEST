package com.example.mediasoftparceltracker.controller;

import com.example.mediasoftparceltracker.dto.ParcelDto;
import com.example.mediasoftparceltracker.dto.PostOfficeDto;
import com.example.mediasoftparceltracker.model.Parcel;
import com.example.mediasoftparceltracker.service.ParcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0")
public class ParcelController {
    @Autowired
    private ParcelService parcelService;

    @Operation(summary = "Register post shipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parcel has been registered successfully."),
            @ApiResponse(responseCode = "400", description = "JSON is invalid.")
    })
    @PostMapping("/register")
    public String shipmentRegistration(@RequestBody ParcelDto parcelDto) {
        parcelService.createShipment(parcelDto);
        return "Parcel has been successfully created!";
    }

    @Operation(summary = "Handle intermediate post office.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Intermediate post office has been registered successfully."),
            @ApiResponse(responseCode = "400", description = "JSON is invalid")
    })
    @PatchMapping("/{id}/intermediate")
    public String registerIntermediatePostOffice(@Parameter(description = "ID of shipment which gonna be updated")
                                                 @PathVariable(name = "id") Long id,
                                                 @RequestBody PostOfficeDto postOfficeDto) throws NotFoundException
    {
        parcelService.registerIntermediatePostOffice(id, postOfficeDto);
        return "Intermediate post office has been successfully added!";
    }

    @Operation(summary = "Get information about shipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information about shipment"),
            @ApiResponse(responseCode = "404", description = "Parcel not found")
    })
    @GetMapping("/{id}")
    public Parcel getParcelInfo(@Parameter(description = "ID of shipment")
                                    @PathVariable(name = "id") Long id) throws NotFoundException
    {
        return parcelService.findParcelById(id);
    }

    @Operation(summary = "Handle shipment as left from intermediate post office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parcel has been successfully left from intermediate post office"),
            @ApiResponse(responseCode = "404", description = "Parcel not found")
    })
    @PatchMapping("/{id}/leave")
    public String registerLeaveFromIntermediatePostOffice(@Parameter(description = "ID of shipment")
                                                              @PathVariable(name = "id") Long id)
    {
        parcelService.leaveParcelFromPostOffice(id);
        return "Parcel has been successfully left from post office.";
    }

    @Operation(summary = "Handle shipment as received by recipient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parcel has been successfully received by recipient"),
            @ApiResponse(responseCode = "404", description = "Parcel not found")
    })
    @PatchMapping("/{id}/received")
    public String registerParcelReceivedByRecipient(@Parameter(description = "Id of parcel")
                                                        @PathVariable(name = "id") Long id)
    {
        parcelService.registerParcelReceivedByRecipient(id);
        return "Parcel has been successfully received by recipient";
    }
}
