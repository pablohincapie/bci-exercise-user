package com.bci.exercise.user.dtoTest;

import com.bci.exercise.user.dto.TokenDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenDTOTest {

    @Test
    public void testTokenDTO() {

        TokenDTO tokenDTO = new TokenDTO();
        String expectedToken = "testToken";

        tokenDTO.setToken(expectedToken);

        assertEquals(expectedToken, tokenDTO.getToken());
    }

    @Test
    public void testTokenDTOEmptyConstructor() {

        TokenDTO tokenDTO = new TokenDTO();
        assertEquals(null, tokenDTO.getToken());
    }
}
