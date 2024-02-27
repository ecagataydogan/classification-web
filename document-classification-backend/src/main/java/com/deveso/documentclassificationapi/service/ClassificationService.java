package com.deveso.documentclassificationapi.service;

import com.deveso.documentclassificationapi.dto.ClassificationRequest;
import com.deveso.documentclassificationapi.dto.ClassificationResponse;
import com.deveso.documentclassificationapi.exception.TextValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClassificationService {

    private final RestTemplate restTemplate;
    private final String classifyUrl;

    @Autowired
    public ClassificationService(RestTemplate restTemplate, @Value("${classification.service.url}") String classifyUrl) {
        this.restTemplate = restTemplate;
        this.classifyUrl = classifyUrl;
    }

    public ResponseEntity<ClassificationResponse> classifyText(ClassificationRequest request) throws TextValidationException {
        validateText(request.getText());
        ResponseEntity<ClassificationResponse> response = restTemplate.postForEntity(classifyUrl, request, ClassificationResponse.class);
        return ResponseEntity.ok(response.getBody());
    }

    public void validateText(String text) throws TextValidationException {
        String[] words = text.split("\\s+");
        if (words.length < 100) {
            throw new TextValidationException("Text cannot be less than 100 words.");
        }
    }
}
