package com.between.prices.service.impl;

import com.between.prices.dto.PriceResponseDto;
import com.between.prices.dto.mapper.PriceMapper;
import com.between.prices.service.PriceService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.between.prices.domain.Price;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public List<PriceResponseDto> findPricesDto(Integer productId, Integer brandId, LocalDateTime date) {
        return this.findPrices(productId, brandId, date).stream().map(price -> priceMapper.fromPriceEntity(price)).collect(Collectors.toList());
    }

    private List<Price> findPrices(Integer productId, Integer brandId, LocalDateTime date) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Price> cq = cb.createQuery(Price.class);
        Root<Price> price = cq.from(Price.class);

        List<Predicate> predicates = new ArrayList<>();

        if (productId != null) {
            predicates.add(cb.equal(price.get("productId"), productId));
        }
        if (brandId != null) {
            predicates.add(cb.equal(price.get("brandId"), brandId));
        }
        if (date != null) {
            predicates.add(cb.lessThanOrEqualTo(price.<LocalDateTime>get("startDate"), date));
            predicates.add(cb.greaterThanOrEqualTo(price.<LocalDateTime>get("endDate"), date));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Price> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

}
