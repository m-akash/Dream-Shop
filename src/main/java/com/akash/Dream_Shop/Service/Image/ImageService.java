package com.akash.Dream_Shop.Service.Image;

import com.akash.Dream_Shop.DTO.ImageDto;
import com.akash.Dream_Shop.Exceptions.ImageNotFoundException;
import com.akash.Dream_Shop.Repository.ImageRepository;
import com.akash.Dream_Shop.Repository.ProductRepository;
import com.akash.Dream_Shop.Service.Product.ProductService;
import com.akash.Dream_Shop.Model.Image;
import com.akash.Dream_Shop.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements iImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Image not found!"));
    }

    @Override
    public List<ImageDto> addImages(Long productId, List<MultipartFile> files) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDTOS = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download/";
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                image.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDTO = new ImageDto();
                imageDTO.setId(savedImage.getId());
                imageDTO.setFileName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDTOS.add(imageDTO);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return savedImageDTOS;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ImageNotFoundException("Image not found!");
        });
    }
}
