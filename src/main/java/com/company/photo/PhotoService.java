package com.company.photo;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PhotoService {
    PhotoResp upload(MultipartFile file);

    byte[] getById(UUID id);
}
