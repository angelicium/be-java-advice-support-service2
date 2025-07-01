package com.itm.space.mapper;

import com.itm.space.domain.entity.Operator;
import com.itm.space.model.response.OperatorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperatorMapper {

    @Mapping(target = "name", source = "operator.user.name")
    OperatorResponse toOperatorResponse(Operator operator);
}
