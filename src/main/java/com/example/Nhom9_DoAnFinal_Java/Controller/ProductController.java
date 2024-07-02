package com.example.Nhom9_DoAnFinal_Java.Controller;

import com.example.Nhom9_DoAnFinal_Java.model.Product;
import com.example.Nhom9_DoAnFinal_Java.services.CategoryService;
import com.example.Nhom9_DoAnFinal_Java.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "/products/product-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/products/add-product";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addProduct(@Valid Product product, BindingResult result, @RequestParam("image") MultipartFile imagesFile) {
        if (result.hasErrors()) {
            return "/products/add-product";
        }if(!imagesFile.isEmpty()){
            try{
                String imageName=saveImageStatic(imagesFile);
                product.setImages("/images/" +imageName);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        productService.addProduct(product);
        return "redirect:/products";
    }

    private String saveImageStatic(MultipartFile image) throws IOException {
        File saveFile = new ClassPathResource("static/images").getFile();
        String fileName = UUID.randomUUID()+ "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
        Files.copy(image.getInputStream(), path);
        return fileName;
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/products/update-product";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid Product product, BindingResult result, @RequestParam("image")MultipartFile imagesFile) {
        if (result.hasErrors()) {
            product.setId(id); //
            return "/products/update-product";
        }if(!imagesFile.isEmpty()) {
            try {
                String imageName = saveImageStatic(imagesFile);
                product.setImages("/images/" + imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.updateProduct(product);
        return "redirect:/products";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/detail/{id}")
    public String detailProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/home";
    }
}
