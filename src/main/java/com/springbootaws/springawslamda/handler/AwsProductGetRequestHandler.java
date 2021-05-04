package com.springbootaws.springawslamda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyResponseEvent;
import com.google.gson.Gson;
import com.springbootaws.springawslamda.pojo.ProductDB;
import com.springbootaws.springawslamda.pojo.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AwsProductGetRequestHandler extends SpringBootRequestHandler<APIGatewayV2ProxyRequestEvent, APIGatewayV2ProxyResponseEvent> {

    @Override
    public APIGatewayV2ProxyResponseEvent handleRequest(APIGatewayV2ProxyRequestEvent input, Context context) {
        APIGatewayV2ProxyResponseEvent responseEvent = new APIGatewayV2ProxyResponseEvent();
        Gson gson = new Gson();
        try {
            String productId = input.getPathParameters().get("productId");
            log.info("Product find api is received for Id  " + productId);
            ProductDB productDB = new ProductDB();
            productDB.save(new ProductInfo(productId, "sample", "data"));
            ProductInfo productInfo = productDB.find(productId);
            log.info("Starting OBJECT TO JSON" + productInfo.toString());
            String valueAsString = gson.toJson(productInfo);
            log.info("Response for find API  " + valueAsString);
            ProductInfo productInfo1 = gson.fromJson(valueAsString, ProductInfo.class);
            log.info("Starting JSON TO Object" + productInfo1.toString());
            responseEvent.setStatusCode(HttpStatus.OK.value());
            responseEvent.setBody(valueAsString);
            return responseEvent;
        } catch (Exception e) {
            log.error("Exception thrown AwsProductGetRequestHandler " + e);
            responseEvent.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseEvent.setBody("Error thrown from AwsProductGetRequestHandler");
            return responseEvent;
        }
    }
}
