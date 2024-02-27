package com.deveso.documentclassificationapi.dto;

import java.util.List;

public class ClassificationResponse {

    private List<String> labels;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}

