package com.bci.exercise.user.modelTest;

import com.bci.exercise.user.model.Phone;
import com.bci.exercise.user.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class PhoneTest {

    @Test
    public void testPhoneCreation() {

        Phone phone = new Phone(123456789L, 123, "US");
        Usuario usuario = mock(Usuario.class);

        phone.setUsuario(usuario);

        assertEquals(123456789L, phone.getNumber());
        assertEquals(123, phone.getCityCode());
        assertEquals("US", phone.getCountryCode());
        assertEquals(usuario, phone.getUsuario());
    }

    @Test
    public void testPhoneDefaultConstructor() {

        Phone phone = new Phone();
        assertNotNull(phone);
    }
}
