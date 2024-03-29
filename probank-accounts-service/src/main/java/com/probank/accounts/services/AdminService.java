package com.probank.accounts.services;

import com.probank.accounts.dtos.AdminDto;
import com.probank.accounts.entities.Admin;

public interface AdminService {
	Admin createAdmin(AdminDto adminDto);
	
	AdminDto mapAdminToAdminDto(Admin admin);
	
	Admin mapAdminDtoToAdmin(AdminDto adminDto);
}
