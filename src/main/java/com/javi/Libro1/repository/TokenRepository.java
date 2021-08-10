package com.javi.Libro1.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javi.Libro1.domain.User;
import com.javi.Libro1.utils.ConfirmationToken;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Optional;

@Repository
public class TokenRepository {
    Jedis jedis = new Jedis(new HostAndPort("redis-18349.c238.us-central1-2.gce.cloud.redislabs.com", 18349));
    ObjectMapper mapper = new ObjectMapper();


    public Optional<ConfirmationToken> findByToken(String token) {
        jedis.auth("wu807NljXeMKYl1bOIdVrtMqmYZmNRXa");
        String userFetched = jedis.get(token);
        System.out.println(userFetched);
        try {
            User userMapped = mapper.readValue(userFetched, User.class);
            return Optional.of(new ConfirmationToken(token, userMapped));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    public void save(ConfirmationToken token) {
        try {
            jedis.auth("wu807NljXeMKYl1bOIdVrtMqmYZmNRXa");
            String userJson = mapper.writeValueAsString(token.getUser());
            long SECONDS_TO_EXPIRE = 180;
            jedis.set(token.getToken(), userJson, new SetParams().ex(SECONDS_TO_EXPIRE));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
