package com.probank.accounts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.probank.accounts.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
	Optional<Note> findNoteByNoteType(String noteType);
}
