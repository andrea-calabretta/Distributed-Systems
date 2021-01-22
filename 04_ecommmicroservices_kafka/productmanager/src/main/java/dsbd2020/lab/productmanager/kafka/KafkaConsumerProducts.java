package dsbd2020.lab.productmanager.kafka;

import com.google.gson.Gson;
import dsbd2020.lab.productmanager.data.ProductRepository;
import dsbd2020.lab.productmanager.data.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import product.Product;

import java.util.Optional;

@Component
public class KafkaConsumerProducts {

    @Autowired
    ProductRepository repository;

    @KafkaListener(topics = "${kafkaTopic}", groupId = "${kafkaGroup}")
    public void listenProductTopic(String message) {
        if (message != null) {
            ProductUpdateRequest updateRequest = new Gson().fromJson(message, ProductUpdateRequest.class);
            Optional<Product> product = repository.findById(updateRequest.getProductId());
            if (product.isPresent()) {
                Product p = product.get();
                p.setQuantity(p.getQuantity()-updateRequest.getProductQuantity());
                repository.save(p);
            }
        }
    }
}
