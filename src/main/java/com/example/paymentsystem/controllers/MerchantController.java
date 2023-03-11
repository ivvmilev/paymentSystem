package com.example.paymentsystem.controllers;

import com.example.paymentsystem.models.Merchant;
import com.example.paymentsystem.services.interfaces.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("")
    public List<Merchant> getAllMerchants() {
        return merchantService.getAllMerchants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable(value = "id") Long id) {
        Optional<Merchant> merchant = merchantService.getMerchantById(id);
        return merchant.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        Merchant createdMerchant = merchantService.createMerchant(merchant);
        return ResponseEntity.created(URI.create("/api/merchants/" + createdMerchant.getId())).body(createdMerchant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable(value = "id") Long id, @RequestBody Merchant merchant) {
        Merchant updatedMerchant = merchantService.updateMerchant(merchant);
        if (updatedMerchant != null) {
            return ResponseEntity.ok().body(updatedMerchant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable(value = "id") Long id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }
}