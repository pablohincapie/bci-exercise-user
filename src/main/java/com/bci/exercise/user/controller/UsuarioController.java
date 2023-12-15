package com.bci.exercise.user.controller;

import com.bci.exercise.user.config.ErrorResponse;
import com.bci.exercise.user.dto.TokenDTO;
import com.bci.exercise.user.dto.UsuarioDTO;
import com.bci.exercise.user.model.Usuario;
import com.bci.exercise.user.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {

        if (usuarioDTO.getEmail().isBlank() || usuarioDTO.getPassword().isBlank()) {
            ErrorResponse errorResponse = new ErrorResponse(new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST, "Los campos email y password no pueden estar vacíos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        if (usuarioService.existUsuario(usuarioDTO.getEmail())) {
            ErrorResponse errorResponse = new ErrorResponse(new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST, "El usuario  ya existe en base de datos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        if (!usuarioService.isValidData(usuarioDTO)) {
            ErrorResponse errorResponse = new ErrorResponse(new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST, "El campo email y contraseña deben tener un formato correcto.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        Usuario nuevoUsuario = usuarioService.crearUsuario(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);

    }
    @GetMapping("/login")
    public ResponseEntity<?> buscarUsuarioPorToken(@Valid @RequestBody TokenDTO tokenDTO) {

        if (tokenDTO.getToken() != null && !tokenDTO.getToken().isBlank()) {
            ErrorResponse errorResponse = new ErrorResponse(new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST, "Debe ingresar un token para continuar.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        Optional<Usuario> usuario = usuarioService.getUsuariobyToken(tokenDTO.getToken());
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        ErrorResponse errorResponse = new ErrorResponse(new Timestamp(System.currentTimeMillis()), HttpStatus.NOT_FOUND, "El token ingresado no corresponde a un usuario.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
