package com.etoullali.services;

import com.etoullali.dtos.ReservationDTO;
import com.etoullali.dtos.VolDTO;
import com.etoullali.entities.Reservation;
import com.etoullali.entities.Vol;
import com.etoullali.mappers.Mappers;
import com.etoullali.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private Mappers mapper;
    private ReservationRepository reservationRepository;

    @Override
    public ReservationDTO getReservationById(Long id) {
        return mapper.fromReservation(reservationRepository.findById(id).orElse(null));

    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOS = reservations
                .stream()
                .map(reservation -> mapper.fromReservation(reservation))
                .collect(Collectors.toList());
        return reservationDTOS;
    }

    @Override
    public void saveReservation(ReservationDTO reservationDTO) {
        Reservation reservation=mapper.fromReservationDTO(reservationDTO);
        reservationRepository.save(reservation);
    }
}
