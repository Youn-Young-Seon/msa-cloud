package com.example.catalogsService.messagebroker;

import com.example.catalogsService.entity.CatalogEntity;
import com.example.catalogsService.repository.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CatalogRepository catalogRepository;

    @KafkaListener(topics = "example-order-topic")
    public void processMessage(String kafkaMessage) throws JsonProcessingException {
        log.info("Kafka Message: =====> " + kafkaMessage);

        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(kafkaMessage, new TypeReference<Map<String, Object>>() {});

        CatalogEntity entity = catalogRepository.findByProductId((String) map.get("productId"));
        entity.setStock(entity.getStock() - (Integer) map.get("qty"));

        catalogRepository.save(entity);
    }
}
