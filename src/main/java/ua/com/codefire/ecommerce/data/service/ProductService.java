package ua.com.codefire.ecommerce.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.codefire.ecommerce.data.entity.Brand;
import ua.com.codefire.ecommerce.data.entity.Product;
import ua.com.codefire.ecommerce.data.entity.ProductType;
import ua.com.codefire.ecommerce.data.repo.BrandRepo;
import ua.com.codefire.ecommerce.data.repo.ProductRepo;
import ua.com.codefire.ecommerce.data.repo.ProductTypeRepo;

import java.util.List;

/**
 * Created by human on 2/2/17.
 */
@Service
public class ProductService {

    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductTypeRepo productTypeRepo;

    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeRepo.findAll();
    }

}
