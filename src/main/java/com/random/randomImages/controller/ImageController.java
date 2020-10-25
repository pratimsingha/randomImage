package com.random.randomImages.controller;

import com.random.randomImages.model.RandomLinks;
import com.random.randomImages.repository.RandomImageDao;
import com.random.randomImages.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ImageController {
    @Autowired
    private RandomImageDao randomImageDao;

    @Autowired
    private ImageService imageService;

//    @GetMapping
//    public List<ProductModel> getProductList(){
//        return productDao.findAll();
//    }

    @RequestMapping(value="/images", method = RequestMethod.GET)
    public
    List<RandomLinks> getImageLists() throws RandomLinkException {
        try {
            return randomImageDao.getImages();
        }catch(RuntimeException exception){
            throw new RandomLinkException(exception.getLocalizedMessage());
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageLink(@PathVariable("id") Integer id) throws RandomLinkException {
        RandomLinks randomLinks = imageService.getImageURL(id);
        if (randomLinks != null) {
            return imageService.getExistingImageUrl(id);
        }else{
            return imageService.getImage(id);

        }

    }
}
