package com.probank.accounts.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.probank.accounts.dtos.AdminDto;
import com.probank.accounts.entities.Admin;
import com.probank.accounts.exceptions.GlobalCustomException;
import com.probank.accounts.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Admin createAdmin(AdminDto adminDto) {
		Optional<Admin> optional = adminRepository.findByAdminEmail(adminDto.getAdminEmail());
		if (optional.isPresent()) {
			throw new GlobalCustomException("Admin Already Registered With The Email : " + adminDto.getAdminEmail(),
					HttpStatus.BAD_REQUEST);
		}
		return adminRepository.save(mapAdminDtoToAdmin(adminDto));
	}

	@Override
	public AdminDto mapAdminToAdminDto(Admin admin) {
		return modelMapper.map(admin, AdminDto.class);
	}

	@Override
	public Admin mapAdminDtoToAdmin(AdminDto adminDto) {
		return modelMapper.map(adminDto, Admin.class);
	}

}
