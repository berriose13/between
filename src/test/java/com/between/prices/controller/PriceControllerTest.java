package com.between.prices.controller;

import com.between.prices.dto.PriceResponseDto;
import com.between.prices.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PriceControllerTest {
    @InjectMocks
    private PriceController priceController;
    @Mock
    private PriceServiceImpl priceService;

    @Test
    void getPriceSuccess() {
        PriceResponseDto priceResponseDto = new PriceResponseDto();
        priceResponseDto.setCadenaId(1);
        priceResponseDto.setMoneda("USD");
        priceResponseDto.setProductoId(1);
        priceResponseDto.setPrecioFinal(34);

        when(priceService.findPricesDto(any(), any(), any(LocalDateTime.class)))
                .thenReturn(List.of(priceResponseDto));

        List<PriceResponseDto> result = priceController.getPrice(LocalDateTime.now(), 1, 1);
        assertNotNull(result, "El resultado no debería ser nulo");

    }


}