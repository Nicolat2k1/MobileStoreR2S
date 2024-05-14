package com.example.MobileStoreR2S.SERVICE;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MobileStoreR2S.DTO.CategoryDTO;
import com.example.MobileStoreR2S.ENTITY.Category;
import com.example.MobileStoreR2S.EXCEPTION.NotFoundException;
import com.example.MobileStoreR2S.MAPPER.CategoryMP;
import com.example.MobileStoreR2S.REPOSITORY.CategoryRPS;

@Service
public class CategoryServiceImpl implements CategorySV {
    private final CategoryRPS categoryRepository;
    private final CategoryMP categoryMapper;

    public CategoryServiceImpl(CategoryRPS categoryRepository, CategoryMP categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO findById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return categoryMapper.toDTO(category);
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);

        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public CategoryDTO update(long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        category.setName(categoryDTO.getName());

        return categoryMapper.toDTO(categoryRepository.save(category));
    }


    public void delete(long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        categoryRepository.deleteById(id);
    }
}
