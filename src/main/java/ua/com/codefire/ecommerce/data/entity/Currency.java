package ua.com.codefire.ecommerce.data.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Entity
@Table(name = "price_currencies")
@NamedQueries({
        @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c")
})
public class Currency {
    @Id
    @Column(name = "price_currency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "currency")
    private List<Price> prices;

    public Currency() {
    }

    public Currency(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Currency currency = (Currency) o;

        if (id != currency.id) return false;
        if (!name.equals(currency.name)) return false;
        return prices != null ? prices.equals(currency.prices) : currency.prices == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        return result;
    }
}
