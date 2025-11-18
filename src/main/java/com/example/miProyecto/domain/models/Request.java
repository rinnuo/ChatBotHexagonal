package com.example.miProyecto.domain.models;

import com.example.miProyecto.domain.valueObjects.TimeValue;
import com.example.miProyecto.domain.valueObjects.HttpMethodValue;

public class Request {

    private long id;
    private String endpoint;
    private String httpMethod;
    private String timestamp;

    public Request() {
        
    }

    public Request(long id, String endpoint, String httpMethod, String timestamp) {
        this.id = id;
        this.endpoint = endpoint;
        this.httpMethod = new HttpMethodValue(httpMethod).getValue();
        this.timestamp = new TimeValue(timestamp).getValue();
    }

    public long getId() {
        return id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
