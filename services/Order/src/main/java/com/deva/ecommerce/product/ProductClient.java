package com.deva.ecommerce.product;

import com.deva.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import java.util.List;

@Service
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    @Autowired
    private  RestTemplate restTemplate;
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody){
        HttpHeaders header = new HttpHeaders();
        header.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseRequest>> requestHttpEntity = new HttpEntity<>(requestBody,header);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<List<PurchaseResponse>>() {};
        ResponseEntity<List<PurchaseResponse>> responseEntity =
                restTemplate.exchange(productUrl+"/purchase",POST,requestHttpEntity,responseType);
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("An error occurred while processing the product purchase:"+responseEntity.getStatusCode());
        }
        return responseEntity.getBody();

    }
}
