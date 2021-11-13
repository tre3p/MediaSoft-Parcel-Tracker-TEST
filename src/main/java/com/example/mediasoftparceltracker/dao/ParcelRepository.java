package com.example.mediasoftparceltracker.dao;

import com.example.mediasoftparceltracker.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    Parcel findParcelById(Integer id);
}
