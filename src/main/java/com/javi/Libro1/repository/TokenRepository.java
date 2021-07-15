package com.javi.Libro1.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javi.Libro1.domain.User;
import com.javi.Libro1.utils.ConfirmationToken;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Optional;

public class TokenRepository {
    Jedis jedis = new Jedis(new HostAndPort("localhost", 6379));
    ObjectMapper mapper = new ObjectMapper();

    Optional<ConfirmationToken> findByToken(String token) {
        String userFetched = jedis.get(token);

        try {
            User userMapped = mapper.readValue(userFetched, User.class);
            return Optional.of(new ConfirmationToken(token, userMapped));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    void save(ConfirmationToken token) {
        try {
            String userJson = mapper.writeValueAsString(token.getUser());
            long SECONDS_TO_EXPIRE = 180;
            jedis.set(token.getToken(),userJson, new SetParams().ex(SECONDS_TO_EXPIRE));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
