package com.cursospring.best_travel;

import com.cursospring.best_travel.domain.entities.ReservationEntity;
import com.cursospring.best_travel.domain.entities.TicketEntity;
import com.cursospring.best_travel.domain.entities.TourEntity;
import com.cursospring.best_travel.domain.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class BestTravelApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BestTravelApplication.class);

    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final TourRepository tourRepository;

    public BestTravelApplication(FlyRepository flyRepository, HotelRepository hotelRepository,
                                 TicketRepository ticketRepository, ReservationRepository reservationRepository,
                                 CustomerRepository customerRepository, TourRepository tourRepository) {
        this.flyRepository = flyRepository;
        this.hotelRepository = hotelRepository;
        this.ticketRepository = ticketRepository;
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.tourRepository = tourRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(BestTravelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        var fly = flyRepository.findById(15L).get();
//        var hotel = hotelRepository.findById(7L).get();
//        var ticket = ticketRepository.findById(UUID.fromString("12345678-1234-5678-2236-567812345678")).get();
//        var reservation = reservationRepository.findById(UUID.fromString("22345678-1234-5678-1234-567812345678")).get();
//        var customer = customerRepository.findById("BBMB771012HMCRR022").get();
//        log.info(String.valueOf(fly));
//        log.info(String.valueOf(hotel));
//        log.info(String.valueOf(ticket));
//        log.info(String.valueOf(ticket));
//        log.info(String.valueOf(reservation));
//        log.info(String.valueOf(customer));

//        System.out.println("----------------------LESS THAN---------------------- ");
//          this.flyRepository.selectLessPrice(BigDecimal.valueOf(20)).forEach(f -> System.out.println(f));
//        System.out.println("----------------------BETWEEN-------------------------");
//          this.flyRepository.selectBetweenPrice(BigDecimal.valueOf(10),BigDecimal.valueOf(20)).forEach(f -> System.out.println(f));
//        System.out.println("----------------------ORIGIN AND DESTINY-------------------------");
//        this.flyRepository.selectOriginDestiny("Grecia","Mexico").forEach(f -> System.out.println(f));

        var customer = customerRepository.findById("GOTW771012HMRGR087").orElseThrow();
        log.info("client name:" + customer.getFullName());

        var fly = flyRepository.findById(11L).orElseThrow();
        log.info("Fly:" + fly.getOriginName() + "-" + fly.getDestinyName());

        var hotel = hotelRepository.findById(3L).orElseThrow();
        log.info("Hotel:" + hotel.getName());

        var tour = TourEntity.builder()
                .customer(customer)
                .build();

        var ticket = TicketEntity.builder()
                .id(UUID.randomUUID())
                .price(fly.getPrice().multiply(BigDecimal.TEN))
                .arrivalDate(LocalDate.now())
                .departureDate(LocalDate.now())
                .purchaseDate(LocalDate.now())
                .customer(customer)
                .tour(tour)
                .fly(fly)
                .build();

        var reservation = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .dateTimeReservation(LocalDateTime.now())
                .dateEnd(LocalDate.now().plusDays(2))
                .dateStart(LocalDate.now().plusDays(1))
                .hotel(hotel)
                .customer(customer)
                .tour(tour)
                .totalDays(1)
                .price(hotel.getPrice().multiply(BigDecimal.TEN))
                .build();

        System.out.println("-----SAVING-----");
        var tourSaved = this.tourRepository.save(tour);
        tour.addReservation(reservation);
        tour.updateReservation();

        tour.addTicket(ticket);
        tour.updateTickets();



        Thread.sleep(10000);
        this.tourRepository.deleteById(tourSaved.getId());
    }
}


