package com.br.reserva.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

    @Getter
    @Setter
    @Entity
    public class Vehicle {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String model;

        @Column(nullable = false)
        private String brand;

        @Column(nullable = false)
        private String licensePlate;

        @Column(nullable = false)
        private Integer year;

        @Column(nullable = false)
        private BigDecimal pricePerDay;

        @Column(nullable = false)
        private Boolean available;

    }

