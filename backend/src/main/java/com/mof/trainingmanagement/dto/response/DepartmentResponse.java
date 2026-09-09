package com.mof.trainingmanagement.dto.response;

public class DepartmentResponse {

    private Long id;
    private String name;

    public DepartmentResponse() {
    }

    public DepartmentResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}