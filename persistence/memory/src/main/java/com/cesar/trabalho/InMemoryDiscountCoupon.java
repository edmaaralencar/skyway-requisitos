package com.cesar.trabalho;

import com.cesar.trabalho.models.DiscountCoupon;
import com.cesar.trabalho.repositories.DiscountCouponRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryDiscountCoupon implements DiscountCouponRepository {
    private final Map<String, DiscountCoupon> discountCoupons = new HashMap<>();

    @Override
    public DiscountCoupon save(DiscountCoupon discountCoupon) {
        this.discountCoupons.put(discountCoupon.getId().toString(), discountCoupon);
        return discountCoupon;
    }

    @Override
    public Optional<DiscountCoupon> findByCode(String code) {
        return this.discountCoupons.values().stream()
                .filter(coupon -> coupon.getCode().equals(code))
                .findFirst();
    }
}
