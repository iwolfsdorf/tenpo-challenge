package com.tenpo.api.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExternalServiceTest {

    final ExternalService externalService = new ExternalService();

    @Test
    void whenGetPercentage_success_expected_percentage() throws Exception {
        final Double expected = 10.0;

        final Double actual = externalService.getPercentage();

        assertEquals(actual, expected);
    }

}
