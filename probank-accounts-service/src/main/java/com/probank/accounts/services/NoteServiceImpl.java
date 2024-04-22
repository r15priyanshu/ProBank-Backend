package com.probank.accounts.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.probank.accounts.dtos.NoteDto;
import com.probank.accounts.entities.Note;
import com.probank.accounts.exceptions.GlobalCustomException;
import com.probank.accounts.repositories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Override
	public Note saveOrUpdateNote(Note note) {
		return noteRepository.save(note);
	}
	
	@Override
	public Note createNote(NoteDto noteDto) {
		if (noteRepository.findNoteByNoteType(noteDto.getNoteType()).isPresent())
			throw new GlobalCustomException("Note of provided type already exists : " + noteDto.getNoteType(),
					HttpStatus.BAD_REQUEST);

		Note note = new Note();
		note.setNoteCount(noteDto.getNoteCount());
		note.setNoteType(noteDto.getNoteType());
		Note savedNote = noteRepository.save(note);
		return savedNote;
	}

	@Override
	public Note findNoteByNoteType(String noteType) {
		Optional<Note> note = this.noteRepository.findNoteByNoteType(noteType);
		return note.get();
	}

	@Override
	public int getNoteCountByNoteType(String noteType) {
		return this.findNoteByNoteType(noteType).getNoteCount();
	}
	
	@Override
	public Note mapNoteDtoToNote(NoteDto noteDto) {
		return modelMapper.map(noteDto, Note.class);
	}

	@Override
	public NoteDto mapNoteToNoteDto(Note note) {
		return modelMapper.map(note, NoteDto.class);
	}
}
