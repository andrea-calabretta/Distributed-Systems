package dsbd2020.lab.productmanager.data;

import org.springframework.data.repository.CrudRepository;
import product.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Product findByIdAndQuantityGreaterThanEqual(Integer id, Integer quantity);

}
