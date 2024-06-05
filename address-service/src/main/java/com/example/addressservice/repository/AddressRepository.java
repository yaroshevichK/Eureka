package com.example.addressservice.repository;

import com.example.addressservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(
            nativeQuery = true,
            value
                    = "SELECT address.id, address.city, address.state FROM gfg.address address " +
                    "join " +
                    "gfg.employee employee on employee.id = address.employee_id " +
                    "where address.employee_id=:employeeId")
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);

}
