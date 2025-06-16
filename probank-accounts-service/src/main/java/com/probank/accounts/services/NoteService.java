package com.probank.accounts.services;

import java.util.Optional;

import com.probank.accounts.dtos.NoteDto;
import com.probank.accounts.entities.Note;

public interface NoteService {
	
	Note saveOrUpdateNote(Note note);
	
	Note createNote(NoteDto noteDto);
	
	Note findNoteByNoteType(String noteType);
	
	Optional<Note> findNoteByNoteTypeOptional(String noteType);
	
	int getNoteCountByNoteType(String noteType);
	
	Note mapNoteDtoToNote(NoteDto noteDto);
	
	NoteDto mapNoteToNoteDto(Note note);
}
