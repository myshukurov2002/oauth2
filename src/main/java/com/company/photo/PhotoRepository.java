package com.company.photo;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PhotoRepository extends CrudRepository<PhotoEntity, UUID> {

}
