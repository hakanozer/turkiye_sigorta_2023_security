package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository pRepo;
    final CacheManager cacheManager;

    public ResponseEntity save(Product product) {
        Map<String, Object> hm = new LinkedHashMap<>();
        pRepo.save(product);
        hm.put("status", true);
        hm.put("result", product);
        cacheManager.getCache("product").clear();
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    @Cacheable("product")
    public ResponseEntity list() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", pRepo.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void call() {
        System.out.println("this line call");
        cacheManager.getCache("product").clear();
    }


}
