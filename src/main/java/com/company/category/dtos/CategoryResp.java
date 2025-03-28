package com.company.category.dtos;

import java.util.UUID;

public record CategoryResp(
        UUID id,
        String name
) {
}
