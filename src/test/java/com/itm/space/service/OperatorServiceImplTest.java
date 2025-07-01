package com.itm.space.service;

import com.itm.space.BaseUnitTest;
import com.itm.space.domain.entity.Operator;
import com.itm.space.mapper.OperatorMapper;
import com.itm.space.model.request.UpdateOperatorRequest;
import com.itm.space.model.response.OperatorResponse;
import com.itm.space.repository.OperatorRepository;
import com.itm.space.service.impl.OperatorServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OperatorServiceImplTest extends BaseUnitTest {

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private OperatorMapper operatorMapper;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    private UpdateOperatorRequest request;

    private UUID operatorId;

    @BeforeEach
    void setUp() {
        operatorId = UUID.randomUUID();

        request = UpdateOperatorRequest
                .builder()
                .specialization("Техническая поддержка")
                .maxTickets(15)
                .build();
    }

    @Test
    @DisplayName("Оператор обновлен успешно")
    void updateOperator_shouldUpdateExistingOperator() {
        Operator existingOperator = Operator.builder()
                .id(operatorId)
                .specialization("Тест")
                .maxTickets(10)
                .build();

        Operator savedOperator = Operator
                .builder()
                .id(operatorId)
                .specialization("Техническая поддержка")
                .maxTickets(15)
                .build();

        OperatorResponse expectedResponse = OperatorResponse
                .builder()
                .id(operatorId)
                .specialization("Техническая поддержка")
                .maxTickets(15)
                .build();

        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(existingOperator));
        when(operatorRepository.save(existingOperator)).thenReturn(savedOperator);
        when(operatorMapper.toOperatorResponse(any(Operator.class))).thenReturn(expectedResponse);

        OperatorResponse result = operatorService.updateOperator(operatorId, request);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
        assertEquals("Техническая поддержка", existingOperator.getSpecialization());
        assertEquals(15, existingOperator.getMaxTickets());

        verify(operatorRepository).findById(operatorId);
        verify(operatorRepository).save(existingOperator);
        verify(operatorMapper).toOperatorResponse(any(Operator.class));
    }

    @Test
    @DisplayName("Оператор не найден")
    void updateOperator_shouldThrowEntityNotFoundExceptionWhenOperatorNotFound() {
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                operatorService.updateOperator(operatorId, request)
        );

        verify(operatorRepository).findById(operatorId);
        verify(operatorRepository, never()).save(any());
        verify(operatorMapper, never()).toOperatorResponse(any());
    }
}
