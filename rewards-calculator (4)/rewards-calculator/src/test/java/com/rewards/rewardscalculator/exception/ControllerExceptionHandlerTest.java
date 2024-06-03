package com.rewards.rewardscalculator.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ControllerExceptionHandlerTest {
    @Mock
    private WebRequest mockRequest;

    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_globalExceptionHandler() {
        // Mock exception and request
        Exception exception = new Exception("Reward calculation failed");
        when(mockRequest.getDescription(false)).thenReturn("Reward calculation failed description");

        // Call the method under test
        ResponseEntity<RewardsCalculatorAppError> responseEntity = controllerExceptionHandler.globalExceptionHandler(exception, mockRequest);

        // Verify that the response entity has the expected status code
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        // Verify that the response entity has the expected error message
        RewardsCalculatorAppError responseBody = responseEntity.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatusCode());
        assertEquals(exception.getMessage(), responseBody.getMessage());
        assertEquals("Reward calculation failed description", responseBody.getDescription());
    }

    @Test
    public void test_handleRewardsValidationGenericError() {
        // Mock exception and request
        RewardsApplicationBadRequestGenericError exception = new RewardsApplicationBadRequestGenericError("Invalid request");
        when(mockRequest.getDescription(false)).thenReturn("No customer found");

        // Call the method under test
        ResponseEntity<RewardsCalculatorAppError> responseEntity = controllerExceptionHandler.handleRewardsValidationGenericError(exception);

        // Verify that the response entity has the expected status code
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Verify that the response entity has the expected error message
        RewardsCalculatorAppError responseBody = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseBody.getStatusCode());
        assertEquals(exception.getMessage(), responseBody.getMessage());
        assertEquals("Invalid request", responseBody.getDescription());
    }
}