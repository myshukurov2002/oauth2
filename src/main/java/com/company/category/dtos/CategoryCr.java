package com.company.category.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryCr(
        @NotBlank(message = "Name Must Not Blank!!!")
        String name
) {
}
