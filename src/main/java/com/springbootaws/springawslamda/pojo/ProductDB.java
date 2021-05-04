package com.springbootaws.springawslamda.pojo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProductDB {

    private static HashMap<String, ProductInfo> map = new HashMap<>();

    public void save(ProductInfo productInfo) {
        map.put(productInfo.getProductId(), productInfo);
        log.info("Product added to DB for Id "+ productInfo.getProductId());
    }

    public ProductInfo find(String productId) {
        ProductInfo productInfo = map.get(productId);
        log.info("Product fetched to DB for Id "+ productInfo);
        return productInfo;
    }

    public  List<ProductInfo> findAllProduct() {
        List<ProductInfo> collect = map.values().stream().collect(Collectors.toList());
        log.info("Product fetched to DB for Id "+ collect);
        return collect;
    }

}
