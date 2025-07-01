package com.itm.space.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OperatorResponse {

    private UUID id;

    private String name;

    private String specialization;

    private Integer maxTickets;

    private LocalDateTime createdAt;
}
