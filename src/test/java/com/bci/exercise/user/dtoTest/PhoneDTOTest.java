package com.bci.exercise.user.dtoTest;

import com.bci.exercise.user.dto.PhoneDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneDTOTest {

    @Test
    public void testGettersAndSetters() {

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber(123456789L);
        phoneDTO.setCitycode(123);
        phoneDTO.setContrycode("US");

        assertEquals(123456789L, phoneDTO.getNumber());
        assertEquals(123, phoneDTO.getCitycode());
        assertEquals("US", phoneDTO.getContrycode());
    }

    @Test
    public void testSetContrycode() {

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setContrycode("USA");
        assertEquals("USA", phoneDTO.getContrycode());
    }


}
