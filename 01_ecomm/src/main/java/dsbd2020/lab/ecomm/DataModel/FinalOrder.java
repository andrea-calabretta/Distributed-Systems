package dsbd2020.lab.ecomm.DataModel;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class FinalOrder {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderProduct> products;

    @Transient
    public Double getPrice() {
        double total = 0.0;
        for (OrderProduct product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public Integer getId() {
        return id;
    }

    public FinalOrder setId(Integer id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public FinalOrder setUser(User user) {
        this.user = user;
        return this;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public FinalOrder setProducts(List<OrderProduct> products) {
        this.products = products;
        return this;
    }

    @Override
    public String toString() {
        return "FinalOrder{" +
                "id=" + id +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
