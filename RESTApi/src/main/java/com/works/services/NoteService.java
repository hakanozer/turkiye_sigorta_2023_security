package com.works.services;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.repositories.NoteRepository;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository nRepo;

    public ResponseEntity save(Note note) {
        Map<String, Object> hm = new LinkedHashMap<>();
        nRepo.save(note);
        hm.put("status", true);
        hm.put("result", note);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", nRepo.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }




}
