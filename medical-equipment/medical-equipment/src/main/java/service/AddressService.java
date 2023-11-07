package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Address;

import iservice.IAdressService;
import repository.IAddressRepository;

@Service
public class AddressService implements IAdressService{
	private IAddressRepository addressRepository;

	@Autowired
	public AddressService(IAddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public Address findById(Long id) {
		return addressRepository.findById(id).orElse(null);
	}

	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
}
