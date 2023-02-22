package com.works.restcontrollers;

import com.works.services.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DummyRestController {

    final DummyService service;

    @GetMapping("/auth")
    public ResponseEntity auth() {
        return service.auth();
    }

}
