package com.bestpractice.api.domain.model;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ArgumentRules;
import org.junit.rules.OrderRules;
import org.junit.rules.StateRule;
import org.junit.rules.TimeoutRule;

import io.cucumber.java.en.Then;

public class AuthByEmailRequestGeneratedAiTests {

    @StateRule
    private StateRule<Void> stateRule = new StateRule<>(Void.class);

    @ExtendWith(OrderRules.class)
    public static class OrderRules {}

    @ExtendWith(TimeoutRule.class)
    public static class TimeoutRules {}

    @ArgumentRules(value = {ArgumentRules.order(OrderRules.Order.FIRST)})
    public static class OrderRules {}

    @Test
    void setEmail() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        assert request.getEmail().equals("test@example.com");
    }

    @Test
    void getPassword() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setPassword("secretPassword");
        assert request.getPassword().equals("secretPassword");
    }

    @Test
    void setAndGetEmail() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        assert request.getEmail().equals("test@example.com");
    }
}