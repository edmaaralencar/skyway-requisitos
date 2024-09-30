package com.cesar.trabalho.repositories;

import com.cesar.trabalho.models.DiscountCoupon;

import java.util.Optional;

public interface DiscountCouponRepository {
    DiscountCoupon save(DiscountCoupon discountCoupon);
    Optional<DiscountCoupon> findByCode(String code);
}
