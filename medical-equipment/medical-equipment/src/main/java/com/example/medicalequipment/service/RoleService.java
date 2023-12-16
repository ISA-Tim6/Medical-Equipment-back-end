package com.example.medicalequipment.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IRoleService;
import com.example.medicalequipment.model.Role;
import com.example.medicalequipment.repository.IRoleRepository;


@Service
public class RoleService implements IRoleService {

  @Autowired
  private IRoleRepository roleRepository;

  @Override
  public Role findById(Long id) {
    Role auth = this.roleRepository.getOne(id);
    return auth;
  }

  @Override
  public List<Role> findByName(String name) {
	List<Role> roles = this.roleRepository.findByName(name);
    return roles;
  }


}
