package com.probank.accounts.services.processors;

import com.probank.accounts.constants.GlobalConstants;
import com.probank.accounts.dtos.MoneyDto;
import com.probank.accounts.services.NoteService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class TenRupeeMoneyProcessor implements MoneyProcessor {
	private MoneyProcessor nextProcessor;

	private NoteService noteService;

	@Override
	public MoneyDto processMoney(int amount, MoneyDto moneyDto) {

		int noOfNotesRequired = amount / 10;
		int noOfNoteDeducted = 0;
		// CHECK IF THE ABOVE NUMBER OF NOTES IS EVEN PRESENT IN THE DB OR NOT
		int availableNoteCount = noteService.getNoteCountByNoteType(GlobalConstants.TEN_RUPEE_NOTE);
		if (noOfNotesRequired != 0 && availableNoteCount >= noOfNotesRequired) {
			noOfNoteDeducted = noOfNotesRequired;
			moneyDto.setTenRupeeNoteCount(noOfNoteDeducted);
		} else {
			log.info("Insufficient Rs.10 Note Available To Fulfil the Request : Required={} : Available={}",
					noOfNotesRequired, availableNoteCount);
		}

		int remainingAmount = amount - (10 * noOfNoteDeducted);
		if (remainingAmount == 0) {
			moneyDto.setWithdrawalSuccess(true);
		}
		return moneyDto;
	}

	@Override
	public void nextMoneyProcessor(MoneyProcessor moneyProcessor) {
		this.nextProcessor = moneyProcessor;
	}
}
