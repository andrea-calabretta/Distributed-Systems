package dsbd2020.lab.ecommdocker.DataModel;

import javax.persistence.*;
import java.util.List;

@Entity
public class FinalOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    @OneToMany(cascade= CascadeType.ALL)
    private List<OrderProduct> products;


    @Transient
    public Double getPrice(){
        double total=0.0;
        for (OrderProduct product:products){
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


/*
Cascade.ALL propagates all operations — including Hibernate-specific ones —
from a parent to a child entity.

Entity relationships often depend on the existence of another entity —
in this example, the FinalOrder–OrderRequest relationship.
Without the FinalOrder entity, the OrderRequest entity doesn't have any meaning of its own.
When we delete the FinalOrder entity, the OrderRequest entity should also get deleted.

Cascading is the way to achieve this.
When we perform some action on the target entity, the same action will be applied to the associated entity.


*/
/*
Transient entity fields are fields that do not participate in persistence and
their values are never stored in the database
@Transient on getters has the same meaning as @Transient on fields -
if default access type for your entity is property access,
you need to annotate all getters that
don't correspond to persistent properties with @Transient.
 */