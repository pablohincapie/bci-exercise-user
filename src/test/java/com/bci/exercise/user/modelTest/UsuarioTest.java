package com.bci.exercise.user.modelTest;

import com.bci.exercise.user.model.Phone;
import com.bci.exercise.user.model.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class UsuarioTest {

    @Test
    public void testGettersAndSetters() {

        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setName("Pablo Hincapie");
        usuario.setEmail("pablohincapie@hotmail.com");
        usuario.setPassword("A12dfgra");
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setActive(true);
        usuario.setToken("testToken");

        assertEquals(usuario.getId(), usuario.getId());
        assertEquals("Pablo Hincapie", usuario.getName());
        assertEquals("pablohincapie@hotmail.com", usuario.getEmail());
        assertEquals("A12dfgra", usuario.getPassword());
        assertEquals(usuario.getFechaCreacion(), usuario.getFechaCreacion());
        assertEquals(usuario.getLastLogin(), usuario.getLastLogin());
        assertTrue(usuario.isActive());
        assertEquals("testToken", usuario.getToken());
    }

    @Test
    public void testSetPhones() {

        Usuario usuario = new Usuario();
        Phone phone1 = mock(Phone.class);
        Phone phone2 = mock(Phone.class);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);
        usuario.setPhones(phones);

        assertEquals(2, usuario.getPhones().size());
        assertTrue(usuario.getPhones().contains(phone1));
        assertTrue(usuario.getPhones().contains(phone2));
    }

    @Test
    public void testPrePersist() {

        Usuario usuario = new Usuario();
        usuario.onCreate();

        assertTrue(usuario.isActive());
        assertTrue(usuario.getFechaCreacion() != null);
    }

    @Test
    public void testPreUpdate() {

        Usuario usuario = new Usuario();

        usuario.onUpdate();
        assertTrue(usuario.isActive());
        assertTrue(usuario.getLastLogin() != null);
    }
}
