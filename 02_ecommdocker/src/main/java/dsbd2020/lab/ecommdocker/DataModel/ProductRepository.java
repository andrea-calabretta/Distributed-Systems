package dsbd2020.lab.ecommdocker.DataModel;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <Product, Integer> {
    public Product findByIdAndQuantityGreaterThanEqual(Integer id, Integer quantity);

}
