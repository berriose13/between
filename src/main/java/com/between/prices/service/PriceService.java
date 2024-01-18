package com.between.prices.service;

import com.between.prices.dto.PriceResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    List<PriceResponseDto> findPricesDto(Integer productId, Integer brandId, LocalDateTime date);

}
