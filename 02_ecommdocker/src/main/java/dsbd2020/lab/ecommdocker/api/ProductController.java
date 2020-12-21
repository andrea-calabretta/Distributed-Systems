package dsbd2020.lab.ecommdocker.api;

import dsbd2020.lab.ecommdocker.DataModel.Product;
import dsbd2020.lab.ecommdocker.DataModel.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    //POST http://localhost:8080/product/add
    @PostMapping(path = "/add")
    public @ResponseBody Product addProduct(@RequestBody Product product){
        return repository.save(product);
    }

    //GET http://localhost:8080/product/id/1
    @GetMapping(path="/id/{id}")
    public @ResponseBody Optional<Product> getProduct(@PathVariable Integer id){
        return repository.findById(id);
    }

    //GET http://localhost:8080/product/all
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> getAll(){
        return repository.findAll();
    }

    //DELETE http://localhost:8080/1
    @DeleteMapping(path="/{id}")
    public @ResponseBody String delete(@PathVariable Integer id){
        repository.deleteById(id);
        return "product with id=" + id + "has been deleted!";
    }

}
