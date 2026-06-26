package com.example.Lufthansa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa-example-pu");

        EntityManager em = emf.createEntityManager();

        System.out.println("Connected successfully!");
        try {
            em.getTransaction().begin();

            Flight flight = new Flight();
            flight.setOrigin("Tirana");
            flight.setDestination("London");
            flight.setAirline("Lufthansa");
            flight.setFlight_number(001);
            em.persist(flight);

            User user = new User();
            user.setUsername("isli_cara");
            user.setPassword("password");
            user.setRole("PASSENGER");

            User_details details = new User_details();
            details.setFirst_name("Isli");
            details.setLast_name("Cara");
            details.setEmail("isli.cara@gmail.com");

            user.setUser_details(details);
            details.setUser(user);

            Booking booking = new Booking();
            booking.setBooking_date("2026-06-26");
            booking.setStatus("CONFIRMED");
            booking.setUser(user);

            Set<Flight> flights = new HashSet<>();
            flights.add(flight);
            booking.setFlights(flights);

            Set<Booking> bookings = new HashSet<>();
            bookings.add(booking);
            user.setBookings(bookings);

            em.persist(user);

            em.getTransaction().commit();
            System.out.println("Entities and cascaded relationships saved successfully!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        em.close();
        emf.close();
    }
}