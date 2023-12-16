package com.bci.exercise.user.serviceTest;

import com.bci.exercise.user.dto.PhoneDTO;
import com.bci.exercise.user.dto.ResultadoDTO;
import com.bci.exercise.user.dto.UsuarioDTO;
import com.bci.exercise.user.model.Phone;
import com.bci.exercise.user.model.Usuario;
import com.bci.exercise.user.repository.PhoneRepository;
import com.bci.exercise.user.repository.UsuarioRepository;
import com.bci.exercise.user.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertirPhoneToEntity() {
        Usuario usuario = new Usuario();
        List<PhoneDTO> listPhoneDTO = new ArrayList<>();
        listPhoneDTO.add(new PhoneDTO(1L, 1, "10"));
        PhoneRepository phoneRepositoryMock = mock(PhoneRepository.class);
        UsuarioService usuarioService = new UsuarioService(phoneRepositoryMock);
        Phone resultPhone = usuarioService.convertirPhoneToEntity(listPhoneDTO, usuario);
        verify(phoneRepositoryMock, times(0)).save(any(Phone.class));
        verify(phoneRepositoryMock, times(0)).save(argThat(phone -> {
                assertEquals(usuario, phone.getUsuario());
            return true;
        }));
        assertEquals(listPhoneDTO.get(listPhoneDTO.size() - 1).getNumber(), resultPhone.getNumber());
        assertEquals(listPhoneDTO.get(listPhoneDTO.size() - 1).getCitycode(), resultPhone.getCityCode());
        assertEquals(listPhoneDTO.get(listPhoneDTO.size() - 1).getContrycode(), resultPhone.getCountryCode());

    }

    @Test
    public void testIsValidData() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Pablo Hincapie", "pablohincapie@hotmail.com", "A45erfghj", new ArrayList<>());
        boolean result = usuarioService.isValidData(usuarioDTO);
        assertTrue(result);
    }

    @Test
    public void testExistUsuario() {
        String email = "pablohincapie@hotmail.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(new Usuario()));
        boolean result = usuarioService.existUsuario(email);
        assertTrue(result);
    }
    @Test
    public void testGetUsuarioByTokenTokenNotPresent() {
        String token = "nonExistentToken";
        when(usuarioRepository.findByToken(token)).thenReturn(Optional.empty());
        Optional<Usuario> result = usuarioService.getUsuarioByToken(token);
        assertTrue(result.isEmpty(), "No debería haber un usuario presente para el token inexistente");
    }
    @Test
    public void testValoresResultado() {

        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setToken("exampleToken");
        usuario.setActive(true);
        usuario.setName("John Doe");
        usuario.setEmail("john.doe@example.com");
        usuario.setPassword("password");

        Phone phone = new Phone();
        phone.setNumber(1L);
        phone.setCityCode(1);
        phone.setCountryCode("US");

        PhoneRepository phoneRepositoryMock = mock(PhoneRepository.class);
        when(phoneRepositoryMock.findByUsuario(usuario)).thenReturn(phone);

        UsuarioService usuarioService = new UsuarioService(phoneRepositoryMock);

        ResultadoDTO resultadoDTO = usuarioService.valoresResultado(usuario);

        assertEquals(usuario.getId(), resultadoDTO.getId());
        assertEquals(usuario.getFechaCreacion(), resultadoDTO.getCreated());
        assertEquals(usuario.getLastLogin(), resultadoDTO.getLastLogin());
        assertEquals(usuario.getToken(), resultadoDTO.getToken());
        assertEquals(usuario.isActive(), resultadoDTO.isActive());
        assertEquals(usuario.getName(), resultadoDTO.getName());
        assertEquals(usuario.getEmail(), resultadoDTO.getEmail());
        assertEquals(usuario.getPassword(), resultadoDTO.getPassword());

        assertNotNull(resultadoDTO.getPhones());
        assertEquals(1, resultadoDTO.getPhones().size());
        PhoneDTO phoneDTO = resultadoDTO.getPhones().get(0);
        assertEquals(phone.getNumber(), phoneDTO.getNumber());
        assertEquals(phone.getCityCode(), phoneDTO.getCitycode());
        assertEquals(phone.getCountryCode(), phoneDTO.getContrycode());
    }

}

