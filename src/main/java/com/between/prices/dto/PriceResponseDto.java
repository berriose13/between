package com.between.prices.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceResponseDto {
    private Integer productoId;
    private Integer cadenaId;
    private String moneda;
    private Integer precioFinal;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
}
