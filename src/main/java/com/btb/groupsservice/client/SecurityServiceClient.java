package com.btb.groupsservice.client;

import com.btb.groupsservice.dto.response.InfoTokenDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


@Component
public class SecurityServiceClient {

    private static final String API_URL = "http://localhost:9000/auth";
    private static final String TOKEN_ENDPOINT = API_URL + "/token";
    private static final String VERIFY_TOKEN = API_URL + "/verify";
    private static final String API_KEY = "A7243431-AD97-4392-A244-A16702429927";

    public Authentication getInfoToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("BTB-API-KEY", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        String urlWithToken = VERIFY_TOKEN + "?token=" + token;

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<InfoTokenDTO> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    new URI(urlWithToken),
                    HttpMethod.GET,
                    requestEntity,
                    InfoTokenDTO.class
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            UserDetails userDetails = User.withUsername(responseEntity.getBody().getEmail())
                    .password("")
                    .roles(responseEntity.getBody().getRole())
                    .build();

            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    "",
                    userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(newAuthentication);

            return authentication;
        } else {
            throw new RuntimeException("Error en la solicitud. Código de estado: " + responseEntity.getStatusCodeValue());
        }

    }

    private static String convertObjectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir el objeto a JSON", e);
        }
    }


}
