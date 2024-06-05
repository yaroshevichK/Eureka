package com.example.employeeservice.service;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.response.AddressResponse;
import com.example.employeeservice.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;
    private final DiscoveryClient discoveryClient;


    public EmployeeResponse getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

        List<ServiceInstance> instances = discoveryClient.getInstances("ADDRESS-SERVICE");

        AddressResponse addressResponse = null;

        if (instances != null && !instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            String url = serviceInstance.getUri().toString();
            url = url + "/address/{id}";
            log.info(url);
            RestTemplate restTemplate = new RestTemplate();
            addressResponse = restTemplate.getForObject(url, AddressResponse.class, id);
        }

        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }
}
