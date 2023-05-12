package com.tenpo.api.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalServiceTest {

    final ExternalService externalService = new ExternalService();

    @Test
    void whenGetPercentage_success_expected_percentage() throws Exception {
        final Double expected = 10.0;

        final Double actual = externalService.getPercentage();

        assertEquals(actual, expected);
    }

}
