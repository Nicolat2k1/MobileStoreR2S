package com.example.MobileStoreR2S.SERVICE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.MobileStoreR2S.DTO.ImageDTO;
import com.example.MobileStoreR2S.DTO.ProductDTO;
import com.example.MobileStoreR2S.ENTITY.Category;
import com.example.MobileStoreR2S.ENTITY.Image;
import com.example.MobileStoreR2S.ENTITY.Product;
import com.example.MobileStoreR2S.EXCEPTION.NotFoundException;
import com.example.MobileStoreR2S.MAPPER.ProductMP;
import com.example.MobileStoreR2S.REPOSITORY.CategoryRPS;
import com.example.MobileStoreR2S.REPOSITORY.ImageRPS;
import com.example.MobileStoreR2S.REPOSITORY.ProductRPS;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImpl implements ProductSV{
    private final ProductMP productMapper;
    private final ProductRPS productRepository;
    private final ImageRPS imageRepository;
    private final CategoryRPS categoryRepository;

    public ProductServiceImpl(ProductMP productMapper, ProductRPS productRepository,
                              ImageRPS imageRepository, CategoryRPS categoryRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO findById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return productMapper.toDTO(product);
    }

    public ProductDTO create(ProductDTO productDTO) {

        Category category = categoryRepository.findById(productDTO.getCategory())
                .orElseThrow(() -> new NotFoundException(productDTO.getCategory()));

        Product product = productMapper.toEntity(productDTO);
        product.setCategory(category);
        product = productRepository.save(product);


        List<Image> listImages = new ArrayList<>();
        for (Image image : product.getImages()) {
            image.setProduct(product);
            listImages.add(imageRepository.save(image));
        }
        product.setImages(listImages);

        return productMapper.toDTO(product);
    }

    public ProductDTO update(long id, ProductDTO productDTO) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        Category category = categoryRepository.findById(productDTO.getCategory())
                .orElseThrow(() -> new NotFoundException(productDTO.getCategory()));

        product = productMapper.toEntity(productDTO);
        product.setId(id);
        product.setName(productDTO.getName());
        product.setUnitPrice(productDTO.getUnitprice());
        product.setUnitStock(productDTO.getUnitStock());
        product.setDescription(productDTO.getDescription());
        product.setManufacturer(productDTO.getManufacturer());
        product.setCategory(category);
        product.setCondition(product.getCondition());

        List<ImageDTO> imageDTOs = productDTO.getImageDTOs();
        List<Image> updateImages = new ArrayList<>();

        for (ImageDTO imageDTO : imageDTOs) {
            Long imageId = imageDTO.getId();
            Image image;

            if (imageId != null && imageRepository.existsById(imageId)) {
                // Nếu hình ảnh đã tồn tại trong cơ sở dữ liệu, cập nhật thông tin của nó
                image = imageRepository.findById(imageId).get();
                image.setName(imageDTO.getName());
                image.setUrl(imageDTO.getUrl());
            } else {
                // Nếu hình ảnh chưa tồn tại trong cơ sở dữ liệu, thêm nó vào cơ sở dữ liệu
                image = new Image();
                image.setName(imageDTO.getName());
                image.setUrl(imageDTO.getUrl());
                image.setProduct(product);
            }
            updateImages.add(image);
        }
        product.setImages(updateImages);

        return productMapper.toDTO(productRepository.save(product));
    }

    public void delete(long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        // Tìm tất cả các hình ảnh có id sản phẩm tương ứng
        List<Image> images = imageRepository.findByProductId(id);

        // Xóa tất cả các hình ảnh
        if (images != null && !images.isEmpty()) {
            imageRepository.deleteAll(images);
        }

        productRepository.deleteById(id);
    }
}
