package ua.com.codefire.ecommerce.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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
    private Integer id;

    private Double priceValue;

    private Timestamp lastUpdated;

    @ManyToOne
    @JoinColumn(name = "product_currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private Brand brand;

    private String model;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (getId() != null ? !getId().equals(product.getId()) : product.getId() != null) return false;
        if (getPriceValue() != null ? !getPriceValue().equals(product.getPriceValue()) : product.getPriceValue() != null)
            return false;
        if (getLastUpdated() != null ? !getLastUpdated().equals(product.getLastUpdated()) : product.getLastUpdated() != null)
            return false;
        if (getCurrency() != null ? !getCurrency().equals(product.getCurrency()) : product.getCurrency() != null)
            return false;
        if (getProductType() != null ? !getProductType().equals(product.getProductType()) : product.getProductType() != null)
            return false;
        if (getBrand() != null ? !getBrand().equals(product.getBrand()) : product.getBrand() != null) return false;
        return getModel() != null ? getModel().equals(product.getModel()) : product.getModel() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPriceValue() != null ? getPriceValue().hashCode() : 0);
        result = 31 * result + (getLastUpdated() != null ? getLastUpdated().hashCode() : 0);
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        result = 31 * result + (getProductType() != null ? getProductType().hashCode() : 0);
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        return result;
    }
}
