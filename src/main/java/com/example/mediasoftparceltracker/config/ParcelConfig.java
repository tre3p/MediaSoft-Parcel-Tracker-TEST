package com.example.mediasoftparceltracker.config;

import com.example.mediasoftparceltracker.dao.ParcelRepository;
import com.example.mediasoftparceltracker.service.ParcelService;
import com.example.mediasoftparceltracker.service.ParcelServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParcelConfig {
    @Bean
    public ParcelService parcelService(ParcelRepository parcelRepository) {
        return new ParcelServiceImpl(parcelRepository);
    }
}
