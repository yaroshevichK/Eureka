package com.example.addressservice.service;

import com.example.addressservice.model.Address;
import com.example.addressservice.repository.AddressRepository;
import com.example.addressservice.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    public AddressResponse findAddressByEmployeeId(int employeeId) {
        Optional<Address> addressByEmployeeId = addressRepository.findAddressByEmployeeId(employeeId);
        return mapper.map(addressByEmployeeId, AddressResponse.class);
    }
}
