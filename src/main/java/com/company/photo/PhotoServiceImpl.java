package com.company.photo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Override
    public PhotoResp upload(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();

            PhotoEntity photo = PhotoEntity
                    .builder()
                    .fileName(file.getOriginalFilename())
                    .content(bytes)
                    .build();

            PhotoEntity saved = photoRepository.save(photo);

            return new PhotoResp(saved.getId(), saved.getFileName());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getById(UUID id) {

        PhotoEntity photo = photoRepository
                .findById(id)
                .orElseThrow();

        return photo.getContent();

    }
}
