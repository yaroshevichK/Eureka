package com.example.employeeservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {

    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private AddressResponse addressResponse;
}
