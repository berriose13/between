package com.between.prices.dto.mapper;

import com.between.prices.domain.Price;
import com.between.prices.dto.PriceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    @Mapping(target = "productoId", source = "productId")
    @Mapping(target = "cadenaId", source = "brandId")
    @Mapping(target = "moneda", source = "curr")
    @Mapping(target = "precioFinal", source = "price")
    @Mapping(target = "fechaInicio", source = "startDate")
    @Mapping(target = "fechaFinal", source = "endDate")
    PriceResponseDto fromPriceEntity(Price price);
}
