package dsbd2020.lab.ecomm.api;

import dsbd2020.lab.ecomm.DataModel.Product;
import dsbd2020.lab.ecomm.DataModel.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.Optional;

@Controller
@RequestMapping (path = "/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    //POST http://localhost:8080/product/add
    @PostMapping (path = "/add")
    public @ResponseBody Product  addProduct (@RequestBody Product product) {
        return productRepository.save(product);
    }

    //GET http://localhost:8080/product/id/2
    /* usiamo OPTIONAL perchè potremmo anche non ottenere un valore di ritorno qualora non ci fossero
    prodotti memorizzati nel database */
    @GetMapping (path = "/id/{id}")
    public @ResponseBody Optional<Product> getProduct(@PathVariable Integer id) {
        return productRepository.findById(id);
    }

    //GET http://localhost:8080/product/all
    @GetMapping (path = "/all")
    public @ResponseBody Iterable<Product> getAll(){
        return productRepository.findAll();
    }

    //DELETE http://localhost:8080/product/1
    @DeleteMapping (path = "/{id}")
    public @ResponseBody String delete (@PathVariable Integer id){
        productRepository.deleteById(id);
        return "product with ID = " + id + " has been deleted!";
    }


}
