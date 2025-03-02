package com.example.subtrack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 250)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 250)
    private String firstName;

    @Size(max = 250)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 250)
    private String lastName;

    @Size(max = 254)
    @NotNull
    @Column(name = "email", nullable = false, length = 254)
    private String email;

    @Size(max = 254)
    @NotNull
    @Column(name = "password", nullable = false, length = 254)
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserSubscription> userSubscriptions = new HashSet<>();
}