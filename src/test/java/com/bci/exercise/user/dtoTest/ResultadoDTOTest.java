package com.bci.exercise.user.dtoTest;

import com.bci.exercise.user.dto.PhoneDTO;
import com.bci.exercise.user.dto.ResultadoDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ResultadoDTOTest {

    @Test
    public void testSetterAndGetters() {

        ResultadoDTO resultadoDTO = new ResultadoDTO();
        UUID id = UUID.randomUUID();
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime lastLogin = LocalDateTime.now().minusHours(1);
        String token = "Valor esperado";
        boolean isActive = true;
        String name = "Pablo Hincapie";
        String email = "pablohincapie@hotmail.com";
        String password = "A12dfgra";
        List<PhoneDTO> phones = Collections.singletonList(new PhoneDTO());

        resultadoDTO.setId(id);
        resultadoDTO.setCreated(created);
        resultadoDTO.setLastLogin(lastLogin);
        resultadoDTO.setToken(token);
        resultadoDTO.setActive(isActive);
        resultadoDTO.setName(name);
        resultadoDTO.setEmail(email);
        resultadoDTO.setPassword(password);
        resultadoDTO.setPhones(phones);

        assertEquals(token, resultadoDTO.getToken(), "Valor esperado");
        assertEquals(name, resultadoDTO.getName(), "Pablo Hincapie");
        assertEquals(email, resultadoDTO.getEmail(), "pablohincapie@hotmail.com");
        assertEquals(password, resultadoDTO.getPassword(), "A12dfgra");
    }
}