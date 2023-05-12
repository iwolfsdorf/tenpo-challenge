package com.tenpo.api.controllers;

import com.tenpo.api.config.RetryConfig;
import com.tenpo.api.entities.Result;
import com.tenpo.api.services.ApiService;
import com.tenpo.api.services.ExternalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = ApiController.class,
        excludeAutoConfiguration = {
                RetryConfig.class
        })
@ActiveProfiles(profiles = "non-async")
class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApiService apiService;

    @MockBean
    ExternalService externalService;

    @Test
    void whenSumNumber_success_expect_SumOfTwoNumberPlusPercentageApplicated() throws Exception {
        final Double expected = 16.5;
        final Double num1 = 10.0;
        final Double num2 = 5.0;

        when(apiService.sumWithPercentage(any(Double.class), any(Double.class))).thenReturn(expected);

        mockMvc.perform(post("/sum?num1=" + num1 + "&num2=" + num2))
            .andExpect(status().is2xxSuccessful())
            .andDo(print());

        verify(apiService, atLeast(1)).sumWithPercentage(any(Double.class), any(Double.class));
    }

    @Test
    void whenSumNumber_error_expect_MethodNotAllowed() throws Exception {
        final Double num1 = 10.0;
        final Double num2 = 5.0;

        when(apiService.sumWithPercentage(any(Double.class), any(Double.class))).thenThrow(new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED));

        mockMvc.perform(post("/sum?num1=" + num1 + "&num2=" + num2))
                .andExpect(status().is4xxClientError())
                .andExpect(status().is(405))
                .andDo(print());

        verify(apiService, atLeast(1)).sumWithPercentage(any(Double.class), any(Double.class));
    }

    @Test
    void whenGetResults_success_expect_PageWithTwoResults() throws Exception {
        final Result result1 = new Result(LocalDateTime.now(), 10.0, 5.0, 10.0, 16.5);
        final Result result2 = new Result(LocalDateTime.now(), 20.0, 5.5, 15.0, 29.325);
        final Page<Result> expected = new PageImpl<>(Arrays.asList(result1, result2));

        when(apiService.getResults(any(Integer.class), any(Integer.class))).thenReturn(expected);

        mockMvc.perform(get("/results?page=0&size=10"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andDo(print());

        verify(apiService, atLeast(1)).getResults(any(Integer.class), any(Integer.class));
    }

}
