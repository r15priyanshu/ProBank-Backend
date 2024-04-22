package com.probank.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.probank.accounts.constants.GlobalConstants;
import com.probank.accounts.dtos.MoneyDto;
import com.probank.accounts.entities.Note;
import com.probank.accounts.services.processors.MoneyProcessor;

@Service
public class TransactionService {

	@Autowired
	private MoneyProcessor moneyProcessor;

	@Autowired
	private NoteService noteService;

	public MoneyDto withdrawAmount(int amount, MoneyDto moneyDto) {
		MoneyDto moneyDtoResponse = moneyProcessor.processMoney(amount, moneyDto);

		if (moneyDtoResponse.isWithdrawalSuccess()) {
			Note note1 = noteService.findNoteByNoteType(GlobalConstants.FIVE_HUNDRED_RUPEE_NOTE);
			note1.setNoteCount(note1.getNoteCount() - moneyDto.getFiveHundredRupeeNoteCount());
			noteService.saveOrUpdateNote(note1);

			Note note2 = noteService.findNoteByNoteType(GlobalConstants.ONE_HUNDRED_RUPEE_NOTE);
			note2.setNoteCount(note2.getNoteCount() - moneyDto.getOneHundredRupeeNoteCount());
			noteService.saveOrUpdateNote(note2);

			Note note3 = noteService.findNoteByNoteType(GlobalConstants.TEN_RUPEE_NOTE);
			note3.setNoteCount(note3.getNoteCount() - moneyDto.getTenRupeeNoteCount());
			noteService.saveOrUpdateNote(note3);
		} else {
			moneyDto.setFiveHundredRupeeNoteCount(0);
			moneyDto.setOneHundredRupeeNoteCount(0);
			moneyDto.setTenRupeeNoteCount(0);
			moneyDto.setWithdrawalSuccess(false);
		}
		return moneyDtoResponse;
	}
}
