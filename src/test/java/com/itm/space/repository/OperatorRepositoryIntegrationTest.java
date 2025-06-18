package com.itm.space.repository;

import com.itm.space.BaseIntegrationTest;
import com.itm.space.domain.entity.Operator;
import com.itm.space.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@DisplayName("Интеграционный тест OperatorRepository")
public class OperatorRepositoryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private Operator testOperator;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .name("John")
                .email("john@example.com")
                .build();
        userRepository.save(testUser);

        testOperator = Operator.builder()
                .user(testUser)
                .specialization("Technical Support")
                .maxTickets(15)
                .build();
    }

    @Test
    @Transactional
    @DisplayName("Создание сущности Operator в БД")
    void shouldSaveAndRetrieveOperatorById() {
        Operator savedOperator = operatorRepository.save(testOperator);

        Operator retrievedOperator = operatorRepository.findById(savedOperator.getId())
                .orElseThrow();

        assertEquals(retrievedOperator.getId(), savedOperator.getId());
        assertEquals(retrievedOperator.getUser(), savedOperator.getUser());
        assertEquals(retrievedOperator.getSpecialization(), savedOperator.getSpecialization());
        assertEquals(retrievedOperator.getMaxTickets(), savedOperator.getMaxTickets());
    }

    @Test
    @Transactional
    @DisplayName("Обновление сущности Operator в БД")
    void shouldUpdateOperator() {
        Operator savedOperator = operatorRepository.save(testOperator);

        savedOperator.setSpecialization("Customer Support");
        savedOperator.setMaxTickets(20);
        operatorRepository.save(savedOperator);

        Operator updatedOperator = operatorRepository.findById(savedOperator.getId())
                .orElseThrow();

        assertEquals(updatedOperator.getSpecialization(), "Customer Support");
        assertThat(updatedOperator.getMaxTickets()).isEqualTo(20);
        assertEquals(updatedOperator.getCreatedAt(), savedOperator.getCreatedAt());
    }
}
