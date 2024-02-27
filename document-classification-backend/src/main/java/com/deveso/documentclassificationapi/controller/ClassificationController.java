package com.deveso.documentclassificationapi.controller;

import com.deveso.documentclassificationapi.dto.ClassificationRequest;
import com.deveso.documentclassificationapi.dto.ClassificationResponse;
import com.deveso.documentclassificationapi.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificationController {

    private final ClassificationService classificationService;

    @Autowired
    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @PostMapping("/classify")
    public ResponseEntity<ClassificationResponse> classifyText(@RequestBody ClassificationRequest request) {
        return classificationService.classifyText(request);
    }

}



