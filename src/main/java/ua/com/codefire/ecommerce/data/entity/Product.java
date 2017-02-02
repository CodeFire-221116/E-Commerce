package ua.com.codefire.ecommerce.data.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by human on 1/31/17.
 */
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
})
public class Product implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_price_id")
    private Price price;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private Brand brand;

    private String model;

    public Product() {
    }

    public Product(Price price, ProductType productType, Brand brand, String model) {
        this.price = price;
        this.productType = productType;
        this.brand = brand;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return getId() == product.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }
}
