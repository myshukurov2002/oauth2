package com.company.component;

public interface BaseMapper<ENTITY, CR, RESP> {
    ENTITY toEntity(CR cr);
    RESP toResponse(ENTITY entity);
} 