package com.probank.accounts.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDto {
	private int noteId;
	private String noteType;
	private int noteCount;
}
