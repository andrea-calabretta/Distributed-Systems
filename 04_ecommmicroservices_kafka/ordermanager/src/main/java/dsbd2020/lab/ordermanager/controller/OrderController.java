package dsbd2020.lab.ordermanager.controller;

import com.google.gson.Gson;
import com.netflix.discovery.EurekaClient;
import dsbd2020.lab.ordermanager.data.FinalOrderRepository;
import dsbd2020.lab.ordermanager.data.ProductUpdateRequest;
import order.FinalOrder;
import order.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import product.Product;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path="/order")
public class OrderController {

    @Autowired
    private FinalOrderRepository orderrepository;

    @Autowired
    EurekaClient discoveryClient;

    @Value("${kafkaTopic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }

    @PostMapping(path="/add")
    public @ResponseBody
    String add(@RequestBody OrderRequest orderrequest) {

        String USER_MANAGER_URL = discoveryClient.getNextServerFromEureka("usermanager",false).getHomePageUrl();
        User user = new RestTemplate().getForObject(USER_MANAGER_URL + "/user/id/{id}", User.class, orderrequest.getUserId());  //Optional<User> user = userrepository.findById(orderrequest.getUserId());
        if (user==null) return "the user of the order is not present";
        System.out.println("user manager ha trovato user " + user);
        List<OrderProduct> list = new ArrayList<>();

        String PRODUCT_MANAGER_URL = discoveryClient.getNextServerFromEureka("productmanager",false).getHomePageUrl();

        for (Map.Entry<Integer, Integer> entry : orderrequest.getProducts().entrySet()) {
            // mi serve cercare nel repository il prodotto con un dato id e verificare
            // che ci sia una scorta adeguata a soddisfare la richiesta

               // verifica che il productrepository contenga quel prodotto (in base a Id)  in quantità ≥ richiesta
            Product product = new RestTemplate().getForObject(PRODUCT_MANAGER_URL+"/product/id/{id}" , Product.class, entry.getKey());//productrepository.findByIdAndQuantityGreaterThanEqual(entry.getKey(), entry.getValue());
            if (product!=null && product.getQuantity() >= entry.getValue()) {
                list.add(new OrderProduct().setProduct(product).setQuantity(entry.getValue()));
                //aggiorna la quantità di prodotto che rimane in stock dopo aver soddisfatto la richiesta
                //product.setQuantity(product.getQuantity()-entry.getValue());
                // meglio aggiornare tutto in un ciclo for finale di interazione col sevizio productmanager
                System.out.println("product manager ha trovato product " + product);
            }
        }
        FinalOrder finalorder = new FinalOrder().setUser(user).setProducts(list);
        orderrepository.save(finalorder);

        //aggiorna la quantità di prodotto che rimane in stock dopo aver soddisfatto la richiesta
        for (final OrderProduct orderProduct : list) {

            Product p = orderProduct.getProduct();
            ProductUpdateRequest productUpdateRequest=new ProductUpdateRequest().setProductId(p.getId()).setProductQuantity(orderProduct.getQuantity());
            sendMessage(new Gson().toJson(productUpdateRequest));

            //new RestTemplate().postForObject(PRODUCT_MANAGER_URL + "/product/add", orderProduct.getProduct().setQuantity(  // postForEntity
              //      orderProduct.getProduct().getQuantity() - orderProduct.getQuantity()), Product.class);

        }
        return "order created! \n" + finalorder.toString();

    }

    @GetMapping(path="/id/{id}")
    public @ResponseBody
    Optional<FinalOrder> getId(@PathVariable Integer id){
        return orderrepository.findById(id);
    }


    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<FinalOrder> getAll(){
        return orderrepository.findAll();
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody
    String delete(@PathVariable Integer id){
        orderrepository.deleteById(id);
        return "the order " + id + " has been deleted";
    }


}