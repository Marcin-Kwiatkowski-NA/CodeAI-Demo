package com.bestpractice.api.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        // GIVEN: Initialize a new DomainBean before each test
        domainBean = new DomainBean();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN: A specific ID value
        Long expectedId = 100L;
        // WHEN: Setting the ID on the domain bean
        domainBean.setId(expectedId);
        // THEN: The retrieved ID should match the expected value
        assertEquals(expectedId, domainBean.getId());
    }

    @Test
    void testSetAndGetName() {
        // GIVEN: A specific name value
        String expectedName = "Test Domain";
        // WHEN: Setting the name on the domain bean
        domainBean.setName(expectedName);
        // THEN: The retrieved name should match the expected value
        assertEquals(expectedName, domainBean.getName());
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN: A specific description value
        String expectedDescription = "This is a test domain bean.";
        // WHEN: Setting the description on the domain bean
        domainBean.setDescription(expectedDescription);
        // THEN: The retrieved description should match the expected value
        assertEquals(expectedDescription, domainBean.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        // GIVEN: Two domain beans with the same data
        DomainBean bean1 = new DomainBean();
        bean1.setId(1L);
        bean1.setName("Bean");
        bean1.setDescription("Description");

        DomainBean bean2 = new DomainBean();
        bean2.setId(1L);
        bean2.setName("Bean");
        bean2.setDescription("Description");

        // WHEN: Comparing the two beans
        boolean areEqual = bean1.equals(bean2);

        // THEN: They should be equal and have the same hash code
        assertThat(areEqual).isTrue();
        assertEquals(bean1.hashCode(), bean2.hashCode());
    }

    @Test
    void testNotEqualsDifferentId() {
        // GIVEN: Two domain beans with different IDs
        DomainBean bean1 = new DomainBean();
        bean1.setId(1L);
        DomainBean bean2 = new DomainBean();
        bean2.setId(2L);

        // WHEN: Comparing the two beans
        boolean areEqual = bean1.equals(bean2);

        // THEN: They should not be equal
        assertThat(areEqual).isFalse();
    }

    @Test
    void testToStringContainsFields() {
        // GIVEN: A domain bean with specific values
        domainBean.setId(10L);
        domainBean.setName("Domain");
        domainBean.setDescription("Sample description");

        // WHEN: Converting to string
        String result = domainBean.toString();

        // THEN: The string should contain all field values
        assertThat(result).contains("10");
        assertThat(result).contains("Domain");
        assertThat(result).contains("Sample description");
    }
}
