package ua.com.codefire.ecommerce.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by human on 1/31/17.
 */
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.getCount", query = "SELECT COUNT(p.id) FROM Product p")
})
public class Product implements Serializable {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "product")
    private List<Price> prices;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private Brand brand;

    private String model;

    public Price getTopicalPrice(){
        Price topicalPrice = null;
        for(Price p : prices){
            if(p.isTopical()){
                topicalPrice = p;
                break;
            }
        }
        return topicalPrice;
    }

    public Product() {
    }

    public Product(ProductType productType, Brand brand, String model) {
        this.productType = productType;
        this.brand = brand;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (productType != null ? !productType.equals(product.productType) : product.productType != null) return false;
        if (brand != null ? !brand.equals(product.brand) : product.brand != null) return false;
        return model != null ? model.equals(product.model) : product.model == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }
}
