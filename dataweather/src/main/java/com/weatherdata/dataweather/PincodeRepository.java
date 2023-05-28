package com.weatherdata.dataweather;



import org.springframework.data.jpa.repository.JpaRepository;

public interface PincodeRepository extends JpaRepository<Pincode, Long> {
    Pincode findByPincode(String pincode);
}
