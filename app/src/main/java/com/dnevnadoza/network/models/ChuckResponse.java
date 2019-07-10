package com.dnevnadoza.network.models;

import java.util.ArrayList;
import java.util.List;

public class ChuckResponse {

    private String type;
    private List<Chuck> value;

    public ChuckResponse() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Chuck> getValue() {
        return value;
    }

    public void setValue(ArrayList<Chuck> value) {
        this.value = value;
    }
}
