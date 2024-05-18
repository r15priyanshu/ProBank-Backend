package com.probank.accounts.controllers;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.probank.accounts.constants.GlobalConstants;
import com.probank.accounts.dtos.MoneyDto;
import com.probank.accounts.dtos.NoteDto;
import com.probank.accounts.entities.Note;
import com.probank.accounts.exceptions.GlobalCustomException;
import com.probank.accounts.services.NoteService;
import com.probank.accounts.services.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MoneyController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private RedissonClient redissonClient;

	@GetMapping("/money/withdraw/{amount}")
	public ResponseEntity<MoneyDto> withdrawAmount(@PathVariable double amount) {
		boolean isAmountDecimal = amount % 1 != 0;
		if (isAmountDecimal)
			throw new GlobalCustomException(
					"Sorry , We can't process your request , Kindly enter a non decimal amount !!",
					HttpStatus.BAD_REQUEST);

		if (amount <= 0 || amount < 10 || amount % 10 != 0) {
			throw new GlobalCustomException(
					"Sorry , We can't process your request ,  Kindly enter a valid non-zero whole number in multiple of 10 !!",
					HttpStatus.BAD_REQUEST);
		}

		MoneyDto moneyDto = new MoneyDto();
		moneyDto.setTotalAmount((int) amount);
		
		//Critical Section
		RLock lock = redissonClient.getLock(GlobalConstants.NOTE_DISPENSER_LOCK_NAME);
        try {
        	log.info("Is Lock Already Aquired : {}",lock.isLocked());
        	lock.lock();
        	log.info("Lock Successfully Aquired !!");
            log.info("Trying to withdraw the amount : {}",(int) amount);     
            moneyDto = transactionService.withdrawAmount((int) amount, moneyDto);
        } finally {
        	log.info("Lock Successfully Released !!");
            lock.unlock();
        }
		
		return new ResponseEntity<MoneyDto>(moneyDto, HttpStatus.OK);
	}

	@PostMapping("/money/addnotes")
	public ResponseEntity<NoteDto> addNotes(@RequestBody NoteDto noteDto) {
		Note note = noteService.createNote(noteDto);
		NoteDto savedNoteDto = noteService.mapNoteToNoteDto(note);
		return new ResponseEntity<NoteDto>(savedNoteDto, HttpStatus.OK);
	}
}
