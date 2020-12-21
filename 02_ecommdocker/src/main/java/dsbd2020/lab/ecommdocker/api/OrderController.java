package dsbd2020.lab.ecommdocker.api;

import dsbd2020.lab.ecommdocker.DataModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path="/order")
public class OrderController {

    @Autowired
    FinalOrderRepository orderrepository;

    @Autowired
    UserRepository userrepository;

    @Autowired
    ProductRepository productrepository;

    @PostMapping(path="/add")
    public @ResponseBody String add(@RequestBody OrderRequest orderrequest) {
        Optional<User> user = userrepository.findById(orderrequest.getUserId());
        if (!user.isPresent()) return "the user of the order is not present";
        List<OrderProduct> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : orderrequest.getProducts().entrySet()) {
            // mi serve cercare nel repository il prodotto con un dato id e verificare
            // che ci sia una scorta adeguata a soddisfare la richiesta

               // verifica che il productrepository contenga quel prodotto (in base a Id)  in quantità ≥ richiesta
            Product product = productrepository.findByIdAndQuantityGreaterThanEqual(entry.getKey(), entry.getValue());
            list.add(new OrderProduct().setProduct(product).setQuantity(entry.getValue()));
                //aggiorna la quantità di prodotto che rimane in stock dopo aver soddisfatto la richiesta
            product.setQuantity(product.getQuantity()-entry.getValue());
        }
        FinalOrder finalorder = new FinalOrder().setUser(user.get()).setProducts(list);
        orderrepository.save(finalorder);
        return "order created! \n" + finalorder.toString();
    }

    @GetMapping(path="/id/{id}")
    public @ResponseBody Optional<FinalOrder> getId(@PathVariable Integer id){
        return orderrepository.findById(id);
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<FinalOrder> getAll(){
        return orderrepository.findAll();
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody String delete(@PathVariable Integer id){
        orderrepository.deleteById(id);
        return "the order " + id + " has been deleted";
    }

}
