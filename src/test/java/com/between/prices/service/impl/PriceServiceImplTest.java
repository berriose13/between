package com.between.prices.service.impl;

import com.between.prices.domain.Price;
import com.between.prices.dto.PriceResponseDto;
import com.between.prices.dto.mapper.PriceMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceService;
    @Mock
    private EntityManager entityManager;
    @Mock
    private PriceMapper priceMapper;

    @Test
    void findPricesSuccess() {
        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime date = LocalDateTime.now();

        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Price> cq = mock(CriteriaQuery.class);
        Root<Price> priceRoot = mock(Root.class);
        TypedQuery<Price> query = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Price.class)).thenReturn(cq);
        when(cq.from(Price.class)).thenReturn(priceRoot);
        when(entityManager.createQuery(cq)).thenReturn(query);

        Price priceEntity = new Price();
        List<Price> prices = new ArrayList<>();
        prices.add(priceEntity);
        when(query.getResultList()).thenReturn(prices);

        PriceResponseDto priceResponseDto = new PriceResponseDto();
        when(priceMapper.fromPriceEntity(priceEntity)).thenReturn(priceResponseDto);

        List<PriceResponseDto> result = priceService.findPricesDto(productId, brandId, date);

        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(1, result.size(), "La lista debería contener un elemento");
        assertEquals(priceResponseDto, result.get(0), "El elemento de la lista debería ser el DTO esperado");

        verify(entityManager, times(1)).getCriteriaBuilder();
        verify(entityManager, times(1)).createQuery(cq);
        verify(query, times(1)).getResultList();
        verify(priceMapper, times(1)).fromPriceEntity(priceEntity);
    }

}