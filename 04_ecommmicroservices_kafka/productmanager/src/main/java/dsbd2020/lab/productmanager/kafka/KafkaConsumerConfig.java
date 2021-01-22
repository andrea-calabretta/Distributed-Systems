package dsbd2020.lab.productmanager.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafkaTopic}")
    private String topicName;

    @Value("${kafkaGroup}")
    private String consumerGroup;

    // Bean di creazione di una mappa con i parametri di configurazione per il client kafka
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers); // Host e porta sulla quale kafka è in ascolto
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                consumerGroup);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class); // Classe di serializzazione da utilizzare per serializzare la chiave
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class); // Classe di serializzazione da utilizzare per serializzare i valori
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> producerFactory() {
        // implementation to produce a new Consumer instance
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
}

