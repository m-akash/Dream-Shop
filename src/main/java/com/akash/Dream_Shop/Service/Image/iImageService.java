package com.akash.Dream_Shop.Service.Image;

import com.akash.Dream_Shop.DTO.ImageDto;
import com.akash.Dream_Shop.Model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface iImageService {
    Image getImageById(Long id);

    List<ImageDto> addImages(Long productId, List<MultipartFile> files);

    void updateImage(MultipartFile file, Long imageId);

    void deleteImageById(Long id);
}
