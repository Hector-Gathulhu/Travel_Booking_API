package com.cursospring.best_travel.infraestructure.services;

import com.cursospring.best_travel.api.models.request.ReservationRequest;
import com.cursospring.best_travel.api.models.responses.HotelResponse;
import com.cursospring.best_travel.api.models.responses.ReservationResponse;
import com.cursospring.best_travel.domain.entities.ReservationEntity;
import com.cursospring.best_travel.domain.repositories.CustomerRepository;
import com.cursospring.best_travel.domain.repositories.HotelRepository;
import com.cursospring.best_travel.domain.repositories.ReservationRepository;
import com.cursospring.best_travel.infraestructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationResponse create(ReservationRequest request) {

        var customer =customerRepository.findById(request.getIdClient()).orElseThrow();
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charger_price_percentage)))
                .build();


        var reservationPersisted = this.reservationRepository.save(reservationToPersist);

        log.info("Reservation saved with ID: {}",reservationPersisted.getId());
        return this.entityToResponse(reservationToPersist);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromDB = this.reservationRepository.findById(id).orElseThrow();
        return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {

        var reservationToUpdate = this.reservationRepository.findById(id).orElseThrow();
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow();

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charger_price_percentage)));

        var reservationUpdated = this.reservationRepository.save(reservationToUpdate);

        log.info("Reservatión updated with ID {}",reservationUpdated.getId());


        return this.entityToResponse(reservationUpdated);
    }

    @Override
    public void delete(UUID uuid) {

        var reservationToDelete = this.reservationRepository.findById(uuid).orElseThrow();
        this.reservationRepository.delete(reservationToDelete);

    }

    @Override
    public BigDecimal findPrice(Long hotelId){
        var hotel = hotelRepository.findById(hotelId).orElseThrow();
        return hotel.getPrice().add(hotel.getPrice().multiply(charger_price_percentage));
    }


    private ReservationResponse entityToResponse(ReservationEntity entity) {
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }

    private static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.20);

}
