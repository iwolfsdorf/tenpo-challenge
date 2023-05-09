package com.tenpo.api.services;

import com.tenpo.api.controllers.errorhandling.ApiException;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    private static final Double PERCENTAGE = 10.0;

    public Double getPercentage() throws ApiException {
        // Mock de servicio externo que devuelve el mismo valor durante 30 minutos
        return PERCENTAGE;
    }
}