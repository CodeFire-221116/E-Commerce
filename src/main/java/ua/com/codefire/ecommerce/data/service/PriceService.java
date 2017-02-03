package ua.com.codefire.ecommerce.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.entity.Price;
import ua.com.codefire.ecommerce.data.repo.CurrencyRepo;
import ua.com.codefire.ecommerce.data.repo.PriceRepo;

import java.util.List;

/**
 * Created by Katya on 03.02.2017.
 */
@Service
public class PriceService {

    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private CurrencyRepo currencyRepo;

    public List<Price> getAllBrands() {
        return priceRepo.findAll();
    }

    public List<Currency> getAllProducts() {
        return currencyRepo.findAll();
    }

}
