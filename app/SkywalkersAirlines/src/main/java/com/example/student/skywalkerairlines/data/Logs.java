package com.example.student.skywalkerairlines.data;

public class Logs {
    private String id;
    private String operation;

    public Logs(String id, String operation) {
        this.id = id;
        this.operation = operation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
