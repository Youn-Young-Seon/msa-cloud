package com.example.catalogsService.service;

import com.example.catalogsService.entity.CatalogEntity;
import com.example.catalogsService.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogServiceImpl {
    private final CatalogRepository catalogRepository;
    private final Environment env;

    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
