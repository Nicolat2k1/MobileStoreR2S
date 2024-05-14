package com.example.MobileStoreR2S.SERVICE;

import java.util.List;
import java.util.stream.Collectors;

import com.example.MobileStoreR2S.DTO.ImageDTO;
import com.example.MobileStoreR2S.ENTITY.Image;
import com.example.MobileStoreR2S.EXCEPTION.NotFoundException;
import com.example.MobileStoreR2S.MAPPER.ImageMP;
import com.example.MobileStoreR2S.REPOSITORY.ImageRPS;
import org.springframework.stereotype.Service;



@Service
public class ImageServiceImpl implements ImageSV {
    private final ImageMP imageMapper;
    private final ImageRPS imageRepository;

    public ImageServiceImpl(ImageMP imageMapper, ImageRPS imageRepository) {
        this.imageMapper = imageMapper;
        this.imageRepository = imageRepository;
    }

    public List<ImageDTO> findAll() {
        return imageRepository.findAll().stream()
                .map(imageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ImageDTO findById(long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return imageMapper.toDTO(image);
    }
}

