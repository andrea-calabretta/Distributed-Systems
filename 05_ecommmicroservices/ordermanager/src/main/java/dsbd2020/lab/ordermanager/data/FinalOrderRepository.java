package dsbd2020.lab.ordermanager.data;

import order.FinalOrder;
import org.springframework.data.repository.CrudRepository;

public interface FinalOrderRepository extends CrudRepository<FinalOrder, Integer> {
    // questo repository contiene gli ordini "in via di evasione"
    // dopo che sono stati prelevati i prodotti richiesti da quell'user
}
