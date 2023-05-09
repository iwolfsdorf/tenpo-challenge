package com.tenpo.api.controllers;

import com.tenpo.api.controllers.errorhandling.ApiException;
import com.tenpo.api.entities.Result;
import com.tenpo.api.services.ApiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/sum")
    public ResponseEntity<Double> sumNumbers(@RequestParam Double num1, @RequestParam Double num2) {
        try {
            Double valueResult = apiService.sumWithPercentage(num1, num2);
            return ResponseEntity.ok(valueResult);
        } catch (ApiException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(null);
        }
    }

    @GetMapping("/results")
    public ResponseEntity<Page<Result>> getResults(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        try {
            Page<Result> apiCalls = apiService.getApiCalls(page, size);
            return ResponseEntity.ok(apiCalls);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

/*    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    @ExceptionHandler(TooManyRequestsException.class)
    public void handleTooManyRequests() {}*/

}