package com.itm.space.service.impl;

import com.itm.space.domain.entity.Operator;
import com.itm.space.mapper.OperatorMapper;
import com.itm.space.model.request.UpdateOperatorRequest;
import com.itm.space.model.response.OperatorResponse;
import com.itm.space.repository.OperatorRepository;
import com.itm.space.service.OperatorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.itm.space.constant.ErrorMessagesConstant.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository operatorRepository;
    private final OperatorMapper operatorMapper;

    @Override
    @Transactional
    public OperatorResponse updateOperator(UUID id, UpdateOperatorRequest updateOperatorRequest) {
        Operator operator = operatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_MESSAGE));

        operator.setSpecialization(updateOperatorRequest.getSpecialization());
        operator.setMaxTickets(updateOperatorRequest.getMaxTickets());

        operatorRepository.save(operator);

        return operatorMapper.toOperatorResponse(operator);
    }
}
