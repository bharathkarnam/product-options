package com.xero.product.it;

import com.xero.product.ProductOptionsApplication;
import com.xero.product.models.Product;
import com.xero.product.models.ProductOptions;
import java.net.URI;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductOptionsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductOptionIntegrationTest {

    private static final String PRODUCT_URL = "https://localhost:8090/api/products";
    private static final String PRODUCT_OPTIONS_URL = "https://localhost:8090/api/products/options";


    @Value("${server.ssl.key-store}")
    private Resource trustStore;

    @Value("${server.ssl.key-store-password}")
    private String trustStorePassword;

    String plainCreds = "admin:password";
    byte[] plainCredsBytes = plainCreds.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    String base64Creds = new String(base64CredsBytes);



    @Test
    public void when_POST_PRODUCT_then_CorrectResponse() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + base64Creds);

        Product.builder()
                .productId("1")
                .description("hello")
                .name("test")
                .deliveryPrice(2)
                .price(9).build();

        HttpEntity<Product> requestEntity = new HttpEntity<>( Product.builder()
                .productId("1")
                .description("hello")
                .name("test")
                .deliveryPrice(0)
                .price(9).build(), headers);

        ResponseEntity<Product> response = restTemplate().postForEntity(new URI(PRODUCT_URL), requestEntity, Product.class);

        assertEquals("1", response.getBody().getProductId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void when_POST_PRODUCT_OPTIONS_then_CorrectResponse() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + base64Creds);

        Product.builder()
                .productId("1")
                .description("hello")
                .name("test")
                .deliveryPrice(2)
                .price(9).build();

        HttpEntity<ProductOptions> requestEntity = new HttpEntity<>( ProductOptions.builder()
                .productId("1")
                .description("hello")
                .name("test")
                .optionsId("1")
                .build(), headers);

        ResponseEntity<ProductOptions> response = restTemplate().postForEntity(new URI(PRODUCT_OPTIONS_URL),
                requestEntity, ProductOptions.class);

        assertEquals("1", response.getBody().getProductId());
        assertEquals("1", response.getBody().getOptionsId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    RestTemplate restTemplate() throws Exception {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
