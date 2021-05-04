package com.springbootaws.springawslamda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyResponseEvent;
import com.google.gson.Gson;
import com.springbootaws.springawslamda.pojo.ProductDB;
import com.springbootaws.springawslamda.pojo.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AwsProductPostRequestHandler extends SpringBootRequestHandler<APIGatewayV2ProxyResponseEvent, APIGatewayV2ProxyResponseEvent> {


    public Object handleRequest(E requestEvent, Context context) {
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        Gson gson = new Gson();
        ProductDB productDB = new ProductDB();
        try {
            String productInfo = requestEvent.getBody();
            log.info("Product Received as " + productInfo + " & getHttpMethod = " + requestEvent.getHttpMethod() + " & getRequestContext = " + requestEvent.getRequestContext().toString());
            ProductInfo productInfo1 = gson.fromJson(productInfo, ProductInfo.class);
            log.info("Storing data in-memory DB " + productInfo1.toString());
            productDB.save(productInfo1);
            List<ProductInfo> allProduct = productDB.findAllProduct();
            log.info("Number of products present " + allProduct.size());
            String toJson = gson.toJson(allProduct);
            log.info("Retrieving records back to client " + toJson);
            responseEvent.setBody(toJson);
            responseEvent.setStatusCode(HttpStatus.CREATED.value());
            return responseEvent;
        } catch (Exception ex) {
            log.error("Unexpected exception received " + ex);
            responseEvent.setBody("Unexpected exception received " + ex.getMessage());
            responseEvent.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return responseEvent;
        }
    }
}
