package com.example.paymentsystem.services.interfaces;


import com.example.paymentsystem.models.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantService {

    List<Merchant> getAllMerchants();

    Optional<Merchant> getMerchantById(Long id);

    Merchant createMerchant(Merchant merchant);

    Merchant updateMerchant(Merchant merchant);

    void deleteMerchant(Long id);
}