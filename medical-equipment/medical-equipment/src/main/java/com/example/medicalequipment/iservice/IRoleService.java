package com.example.medicalequipment.iservice;


import java.util.List;

import com.example.medicalequipment.model.Role;


public interface IRoleService {
	Role findById(Long id);
	List<Role> findByName(String name);
}
