package com.itm.space.service;

import com.itm.space.model.request.UpdateOperatorRequest;
import com.itm.space.model.response.OperatorResponse;

import java.util.UUID;

public interface OperatorService {

    OperatorResponse updateOperator(UUID uuid, UpdateOperatorRequest updateOperatorRequest);
}
