package com.itm.space.controller.impl;

import com.itm.space.controller.OperatorController;
import com.itm.space.model.request.UpdateOperatorRequest;
import com.itm.space.model.response.OperatorResponse;
import com.itm.space.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OperatorControllerImpl implements OperatorController {

    private final OperatorService operatorService;

    @Override
    public ResponseEntity<OperatorResponse> updateOperator(UUID id, UpdateOperatorRequest updateOperatorRequest) {
        OperatorResponse operatorResponse = operatorService.updateOperator(id, updateOperatorRequest);
        return ResponseEntity.ok(operatorResponse);
    }
}
