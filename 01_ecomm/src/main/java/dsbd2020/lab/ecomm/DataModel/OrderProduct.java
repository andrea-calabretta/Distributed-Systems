package dsbd2020.lab.ecomm.DataModel;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class OrderProduct {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne    //si possono richiedere più ordini per lo stesso prodotto
    private Product product;

    private Integer quantity;

    @Transient
    public Double getPrice(){
        return quantity * product.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public OrderProduct setId(Integer id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderProduct setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
