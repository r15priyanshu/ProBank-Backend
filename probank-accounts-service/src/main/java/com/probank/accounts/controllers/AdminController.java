package com.probank.accounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.probank.accounts.dtos.AdminDto;
import com.probank.accounts.entities.Admin;
import com.probank.accounts.services.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/admins")
	public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
		Admin admin = adminService.createAdmin(adminDto);
		AdminDto response = adminService.mapAdminToAdminDto(admin);
		return new ResponseEntity<AdminDto>(response, HttpStatus.CREATED);
	}
}
