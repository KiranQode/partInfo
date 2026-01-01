package com.example.onshapeapp.partInfo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.onshapeapp.partInfo.model.PhysicalProperties;

@Service
public class OnshapeService {

    private final RestClient restClient;
    private final String accessKey;
    private final String secretKey;

    // Use constructor injection for BOTH the builder and the properties
    public OnshapeService(
            RestClient.Builder restClientBuilder, 
            @Value("${onshape.api.base-url}") String baseUrl,
            @Value("${onshape.api.access-key}") String accessKey,
            @Value("${onshape.api.secret-key}") String secretKey) {
        
        // Debug line: This will print in your terminal when the app starts
        System.out.println("Connecting to Onshape at: " + baseUrl);

        if (baseUrl == null || !baseUrl.startsWith("http")) {
            throw new IllegalArgumentException("Base URL is missing its scheme (https://)");
        }

        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public PhysicalProperties getPartStudioPhysicalProperties(String d, String w, String e) {
        String uri = String.format("/api/partstudios/d/%s/w/%s/e/%s/massproperties", d, w, e);
        
        String authHeader = accessKey + ":" + secretKey;
        String encodedAuth = java.util.Base64.getEncoder().encodeToString(authHeader.getBytes());

        return restClient.get()
                .uri(uri)
                .header("Authorization", "Basic " + encodedAuth)
                .retrieve()
                .body(PhysicalProperties.class);
    }
}
