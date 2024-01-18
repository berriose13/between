package com.between.prices.controller;

import com.between.prices.dto.PriceResponseDto;
import com.between.prices.service.impl.PriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceServiceImpl priceServiceImpl;

    @GetMapping("/obtener")
    public List<PriceResponseDto> getPrice(
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(value = "productId", required = false) Integer productId,
            @RequestParam(value = "brandId", required = false) Integer brandId) {
        return priceServiceImpl.findPricesDto(productId, brandId, date);
    }

}
