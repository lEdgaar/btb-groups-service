package com.btb.groupsservice.client;

import com.btb.groupsservice.dto.response.InfoTokenDTO;
import com.btb.groupsservice.dto.response.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
public class UserOrganizationServiceClient {

    private static final String API_URL = "http://localhost:9005";
    private static final String GET_USER = API_URL + "/brokers/search";
    private static final String secretKey = "f6cbd33227d1358687092ce9c38b6e07c2f027e5f9616629b964e13a95b56fcbea745376471412bc60c1c76609af4e16c1669e164eb3bade0e2ea94ffb00cd5c";

    public UserDTO getBrokers(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        String email = claims.get("sub", String.class);

        RestTemplate restTemplate = new RestTemplate();
        String urlWithToken =  GET_USER + "?name=" + email;

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserDTO> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    new URI(urlWithToken),
                    HttpMethod.GET,
                    requestEntity,
                    UserDTO.class
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return responseEntity.getBody();
    }
}
