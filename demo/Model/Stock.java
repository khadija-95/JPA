package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Product ID must not be empty")
    @Column(columnDefinition = "not null")
    private Integer productid;

    @NotNull(message = "Merchant ID must not be empty")
    @Column(columnDefinition = "not null")
    private Integer merchantid;

    @Min(value = 10, message = "Stock must be at least 10 at start")
    @Column(columnDefinition = "not null")
    private int stock;
}
