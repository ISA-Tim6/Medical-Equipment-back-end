package iservice;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Address;

@Service
public interface IAdressService {
	public Address findById(Long id);

	Address saveAddress(Address address);
}
