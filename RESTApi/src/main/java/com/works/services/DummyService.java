package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.JWTUser;
import com.works.props.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DummyService {

    final RestTemplate restTemplate;
    final ObjectMapper objectMapper;

    public ResponseEntity auth() {

        try {
            String url = "https://dummyjson.com/auth/login";
            JWTUser user = new JWTUser();
            user.setUsername("kminchelle");
            user.setPassword("0lelplR");
            String sendData = objectMapper.writeValueAsString(user);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(sendData, httpHeaders);
            ResponseEntity<Jwt> responseEntity = restTemplate.postForEntity(url, httpEntity, Jwt.class );
            Jwt jwt = responseEntity.getBody();
            return new ResponseEntity(jwt, HttpStatus.OK);
        }catch (Exception ex) {
            System.err.println("auth error : " + ex);
        }
        return new ResponseEntity("", HttpStatus.OK);
    }

}
