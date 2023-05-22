package com.etoullali.services;

import com.etoullali.dtos.*;

import java.util.List;

public interface ReservationService {
    ReservationDTO getReservationById(Long id);
    List<ReservationDTO> getAllReservations();
    void saveReservation(ReservationDTO reservationDTO);

}
