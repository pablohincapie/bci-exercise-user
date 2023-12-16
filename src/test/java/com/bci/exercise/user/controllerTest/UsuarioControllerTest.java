package com.bci.exercise.user.controllerTest;

import com.bci.exercise.user.config.ErrorResponse;
import com.bci.exercise.user.controller.UsuarioController;
import com.bci.exercise.user.dto.TokenDTO;
import com.bci.exercise.user.dto.UsuarioDTO;
import com.bci.exercise.user.model.Usuario;
import com.bci.exercise.user.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    public void testCrearUsuario() {

        UsuarioDTO usuarioDTO = new UsuarioDTO("Pablo Hincapie", "pablohincapie@hotmail.com", "A12dfgrs", new ArrayList<>());
        Usuario nuevoUsuario = new Usuario();

        when(usuarioService.existUsuario(usuarioDTO.getEmail())).thenReturn(false);
        when(usuarioService.isValidData(usuarioDTO)).thenReturn(true);
        when(usuarioService.crearUsuario(usuarioDTO)).thenReturn(nuevoUsuario);

        ResponseEntity<?> responseEntity = usuarioController.crearUsuario(usuarioDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(nuevoUsuario, responseEntity.getBody());
    }

    @Test
    public void testCrearUsuarioCamposVacios() {

        UsuarioDTO usuarioDTO = new UsuarioDTO("", "", "", new ArrayList<>());
        ResponseEntity<?> responseEntity = usuarioController.crearUsuario(usuarioDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorResponse);
    }

    @Test
    public void testCrearUsuarioExistente() {

        UsuarioDTO usuarioDTO = new UsuarioDTO("Pablo Hincapie", "pablohincapie@hotmail.com", "A12dfgra", new ArrayList<>());

        when(usuarioService.existUsuario(usuarioDTO.getEmail())).thenReturn(true);
        ResponseEntity<?> responseEntity = usuarioController.crearUsuario(usuarioDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorResponse);
    }

    @Test
    public void testCrearUsuarioDatosInvalidos() {

        UsuarioDTO usuarioDTO = new UsuarioDTO("Pablo Hincapie", "pablohincapie@hotmail.com", "A12dfgra", new ArrayList<>());

        when(usuarioService.isValidData(usuarioDTO)).thenReturn(false);

        ResponseEntity<?> responseEntity = usuarioController.crearUsuario(usuarioDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorResponse);
    }

    @Test
    public void testBuscarUsuarioPorTokenTokenVacio() {

        TokenDTO tokenDTO = new TokenDTO();

        ResponseEntity<?> responseEntity = usuarioController.buscarUsuarioPorToken(tokenDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorResponse);
    }

    @Test
    public void testBuscarUsuarioPorTokenNoEncontrado() {

        TokenDTO tokenDTO = new TokenDTO();

        when(usuarioService.getUsuarioByToken(tokenDTO.getToken())).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = usuarioController.buscarUsuarioPorToken(tokenDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorResponse);
    }
}