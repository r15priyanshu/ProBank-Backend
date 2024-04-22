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
public class FiveHundredRupeeMoneyProcessor implements MoneyProcessor {

	private MoneyProcessor nextProcessor;

	private NoteService noteService;

	@Override
	public MoneyDto processMoney(int amount, MoneyDto moneyDto) {
		int noOfNotesRequired = amount / 500;
		int noOfNoteDeducted = 0;
		// CHECK IF THE ABOVE NUMBER OF NOTES IS EVEN PRESENT IN THE DB OR NOT
		int availableNoteCount = noteService.getNoteCountByNoteType(GlobalConstants.FIVE_HUNDRED_RUPEE_NOTE);
		if (noOfNotesRequired != 0 && availableNoteCount >= noOfNotesRequired) {
			noOfNoteDeducted = noOfNotesRequired;
			moneyDto.setFiveHundredRupeeNoteCount(noOfNoteDeducted);
		} else {
			log.info("Insufficient Rs.500 Note Available To Fulfil the Request : Required={} : Available={}",
					noOfNotesRequired, availableNoteCount);
		}

		int remainingAmount = amount - (500 * noOfNoteDeducted);
		if (remainingAmount == 0) {
			moneyDto.setWithdrawalSuccess(true);
		}
		if (remainingAmount != 0 && nextProcessor != null) {
			moneyDto = this.nextProcessor.processMoney(remainingAmount, moneyDto);
		}
		return moneyDto;
	}

	@Override
	public void nextMoneyProcessor(MoneyProcessor moneyProcessor) {
		this.nextProcessor = moneyProcessor;
	}
}