package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Min(value = 3, message = "Name must be at least 3 characters long")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Min(value = 1, message = "Price must be positive")
    @Column(columnDefinition = "not null")
    private double price;

    @NotNull(message = "Category ID must not be empty")
    @Column(columnDefinition = "not null")
    private Integer categoryID;
}
