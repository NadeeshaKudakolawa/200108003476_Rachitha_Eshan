package com.mof.trainingmanagement.dto.response;

public class OfficerResponse {

    private Long id;
    private String employeeNumber;
    private String name;
    private String email;
    private Long departmentId;
    private String departmentName;

    public OfficerResponse() {
    }

    public OfficerResponse(Long id, String employeeNumber, String name, String email,
                            Long departmentId, String departmentName) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}