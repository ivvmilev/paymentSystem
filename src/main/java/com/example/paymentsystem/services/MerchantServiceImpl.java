package com.example.paymentsystem.services;

import com.example.paymentsystem.exeptions.InvalidMerchantException;
import com.example.paymentsystem.models.Merchant;
import com.example.paymentsystem.repositories.MerchantRepository;
import com.example.paymentsystem.services.interfaces.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> getMerchantById(Long id) {
        return merchantRepository.findById(id);
    }

    @Override
    public Merchant createMerchant(Merchant merchant) {
        validateMerchant(merchant);
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant updateMerchant(Merchant merchant) {
        Optional<Merchant> optionalMerchant = merchantRepository.findById(merchant.getId());
        if (optionalMerchant.isPresent()) {
            Merchant existingMerchant = optionalMerchant.get();
            existingMerchant.setName(merchant.getName());
            existingMerchant.setDescription(merchant.getDescription());
            existingMerchant.setEmail(merchant.getEmail());
            existingMerchant.setStatus(merchant.getStatus());
            existingMerchant.setTotalTransactionSum(merchant.getTotalTransactionSum());
            validateMerchant(existingMerchant);
            return merchantRepository.save(existingMerchant);
        }
        return null;
    }

    @Override
    public void deleteMerchant(Long id) {
        merchantRepository.deleteById(id);
    }

    private void validateMerchant(Merchant merchant) {
        if (merchant.getName() == null || merchant.getName().isEmpty()) {
            throw new InvalidMerchantException("Merchant name cannot be empty");
        }
        if (merchant.getEmail() == null || !merchant.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidMerchantException("Invalid email address");
        }
    }
}
