package com.deveso.documentclassificationapi.dto;

public class ClassificationRequest {
    private String text;

    public ClassificationRequest() {
    }

    public ClassificationRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

