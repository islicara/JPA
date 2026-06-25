package com.example.Lufthansa;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.List;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private User_details user_details;

}
