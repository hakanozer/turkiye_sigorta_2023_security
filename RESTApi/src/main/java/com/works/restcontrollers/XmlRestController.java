package com.works.restcontrollers;

import com.works.services.XmlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class XmlRestController {

    final XmlService xmlService;

    @GetMapping("/xml")
    public void xml() {
        xmlService.xml();
    }

}
