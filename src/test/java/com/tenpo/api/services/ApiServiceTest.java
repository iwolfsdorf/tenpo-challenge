package com.tenpo.api.services;

import com.tenpo.api.entities.Result;
import com.tenpo.api.exceptions.ApiException;
import com.tenpo.api.exceptions.ProviderException;
import com.tenpo.api.repositories.ResultsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "non-async")
public class ApiServiceTest {

    @Mock
    private ExternalService externalService;

    @Mock
    private ResultsRepository resultsRepository;

    @InjectMocks
    private ApiService apiService;

    @Test
    void whenSumNumber_success_expect_SumOfTwoNumberPlusPercentageApplicated() throws ApiException, ProviderException {
        final Double expected = 16.5;
        final Double num1 = 10.0;
        final Double num2 = 5.0;

        when(externalService.getPercentage()).thenReturn(10.0);
        when(resultsRepository.save(any())).thenReturn(null);

        final Double actual = apiService.sumWithPercentage(num1, num2);
        assertEquals(actual, expected);
        verify(resultsRepository, atLeast(1)).save(any());
        verify(externalService, atLeast(1)).getPercentage();
    }

    @Test
    void whenSumNumber_error_expect_ApiException() throws Exception {
        final Double num1 = 10.0;
        final Double num2 = 5.0;

        when(externalService.getPercentage()).thenThrow(new ProviderException());
        assertThrows(ApiException.class, () -> apiService.sumWithPercentage(num1, num2));
        verify(externalService, atLeast(1)).getPercentage();
    }

    @Test
    void whenGetResults_success_expect_PageWithTwoResults() throws Exception {
        final Result result1 = new Result(LocalDateTime.now(), 10.0, 5.0, 10.0, 16.5);
        final Result result2 = new Result(LocalDateTime.now(), 20.0, 5.5, 15.0, 29.325);
        final Page<Result> expected = new PageImpl<>(Arrays.asList(result1, result2));

        when(resultsRepository.findAllByOrderByDateTimeDesc(any())).thenReturn(expected);
        Page<Result> actual = apiService.getResults(0,10);

        assertEquals(actual, expected);
        verify(resultsRepository, atLeast(1)).findAllByOrderByDateTimeDesc(any());
    }

}
