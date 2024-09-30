package com.cesar.trabalho.services;

import com.cesar.trabalho.models.DiscountCoupon;
import com.cesar.trabalho.repositories.DiscountCouponRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.OptionalInt;

public class DiscountCouponService {
    private final DiscountCouponRepository discountCouponRepository;

    public DiscountCouponService(DiscountCouponRepository discountCouponRepository) {
        this.discountCouponRepository = discountCouponRepository;
    }

    public DiscountCoupon validateCode(String code) throws Exception {
        Optional<DiscountCoupon> discountCoupon = this.discountCouponRepository.findByCode(code);

        if (discountCoupon.isEmpty()) {
            throw new Exception("Código não existe");
        }

        LocalDate now = LocalDate.now();

        if (now.isAfter(discountCoupon.get().getExpiresAt())) {
            throw new Exception("Código expirado");
        }

        return discountCoupon.get();
    }
}
