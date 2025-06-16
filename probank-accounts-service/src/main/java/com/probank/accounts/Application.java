package com.probank.accounts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.probank.accounts.constants.GlobalConstants;
import com.probank.accounts.entities.Note;
import com.probank.accounts.services.NoteService;

@SpringBootApplication
@EnableFeignClients
// We are telling JPA to do auditing for createdBy and modifiedBy using this bean
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Application implements ApplicationRunner {

	@Lazy
	@Autowired
	private NoteService noteService;

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String noteTypeOne = GlobalConstants.TEN_RUPEE_NOTE;
		noteService.findNoteByNoteTypeOptional(noteTypeOne).orElseGet(() -> {
			Note note = new Note(GlobalConstants.TEN_RUPEE_NOTE, GlobalConstants.TEN_RUPEE_NOTE_INITIAL_COUNT);
			return noteService.saveOrUpdateNote(note);
		});

		String noteTypeTwo = GlobalConstants.ONE_HUNDRED_RUPEE_NOTE;
		noteService.findNoteByNoteTypeOptional(noteTypeTwo).orElseGet(() -> {
			Note note = new Note(GlobalConstants.ONE_HUNDRED_RUPEE_NOTE,
					GlobalConstants.ONE_HUNDRED_RUPEE_NOTE_INITIAL_COUNT);
			return noteService.saveOrUpdateNote(note);
		});

		String noteTypeThree = GlobalConstants.FIVE_HUNDRED_RUPEE_NOTE;
		noteService.findNoteByNoteTypeOptional(noteTypeThree).orElseGet(() -> {
			Note note = new Note(GlobalConstants.FIVE_HUNDRED_RUPEE_NOTE,
					GlobalConstants.FIVE_HUNDRED_RUPEE_NOTE_INITIAL_COUNT);
			return noteService.saveOrUpdateNote(note);
		});
	}
}
