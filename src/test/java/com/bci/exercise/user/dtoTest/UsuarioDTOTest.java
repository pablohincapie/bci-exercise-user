package com.bci.exercise.user.dtoTest;

import com.bci.exercise.user.dto.UsuarioDTO;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioDTOTest {

    @Test
    public void testUsuarioDTO() {

        String expectedName = "Pablo Hincapie";
        String expectedEmail = "pablohincapie@hotmail.com";
        String expectedPassword = "A12dfgra";
        UsuarioDTO usuarioDTO = new UsuarioDTO(expectedName, expectedEmail, expectedPassword, Collections.emptyList());

        assertEquals(expectedName, usuarioDTO.getName());
        assertEquals(expectedEmail, usuarioDTO.getEmail());
        assertEquals(expectedPassword, usuarioDTO.getPassword());
        assertNotNull(usuarioDTO.getPhones());
        assertEquals(Collections.emptyList(), usuarioDTO.getPhones());
    }

    @Test
    public void testUsuarioDTOEmptyConstructor() {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        assertNotNull(usuarioDTO);

    }
}
