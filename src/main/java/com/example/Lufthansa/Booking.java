package com.example.Lufthansa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String booking_date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

}
