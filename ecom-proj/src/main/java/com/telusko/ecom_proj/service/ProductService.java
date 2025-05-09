package com.telusko.ecom_proj.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.telusko.ecom_proj.model.Product;
import com.telusko.ecom_proj.repo.ProductRepo;
@Service
public class ProductService {


    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProduct(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public Product updateproduct(int id, Product product, MultipartFile imageFile) throws IOException {
        Product existingProduct = repo.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }

        // Update all fields, including desc
        existingProduct.setName(product.getName());
        existingProduct.setDesc(product.getDesc());  // ✅ This line was missing before
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setAvailable(product.isAvailable());
        existingProduct.setReleaseDate(product.getReleaseDate());

        if (imageFile != null && !imageFile.isEmpty()) {
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
            existingProduct.setImageData(imageFile.getBytes());
        }

        return repo.save(existingProduct);
    }

	
    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

	



	public List<Product> searchproducts(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchproducts(keyword);
	}

	}
	

