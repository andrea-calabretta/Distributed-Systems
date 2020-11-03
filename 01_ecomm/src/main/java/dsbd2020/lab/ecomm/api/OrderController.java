package dsbd2020.lab.ecomm.api;

import dsbd2020.lab.ecomm.DataModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping (path = "/order")
public class OrderController {
    @Autowired
    FinalOrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String add(@RequestBody OrderRequest orderrequest)
    {
        Optional<User> user = userRepository.findById(orderrequest.getUserId());
        System.out.println(user.toString());
        if (!user.isPresent()) return "the user of the order is not present";
        List<OrderProduct> list = new ArrayList<>();


        for (Map.Entry<Integer, Integer> entry : orderrequest.getProducts().entrySet())
        {
            //mi serve cercare nel repository il prodotto con un dato ID e verificare che
            //ci sia una scorta adeguata a soddisfare la richiesta

            //verifica che il productRepository contenga quel prodotto (in base a ID) in quantità >= richiesta
            Product product = productRepository.findByIdAndQuantityGreaterThanEqual(entry.getKey(), entry.getValue());
            
                            list.add(new OrderProduct().setProduct(product).setQuantity(entry.getValue()));
                            // aggiorna la quantità di prodotto che rimane in stock dopo aver soddisfatto la richiesta

            product.setQuantity(product.getQuantity()-entry.getValue());


        }
        FinalOrder finalOrder = new FinalOrder().setUser(user.get()).setProducts(list);
        orderRepository.save(finalOrder);
        return "order created! \n" + finalOrder.toString();
    }
    @GetMapping(path = "/id/{id}")
    public @ResponseBody Optional <FinalOrder> getId(@PathVariable Integer id){
        return orderRepository.findById(id);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable <FinalOrder> getAll(){ return orderRepository.findAll();}

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String delete(@PathVariable Integer id)
    {
        orderRepository.deleteById(id);
        return "the order " + id + "has deleted";
    }



}
