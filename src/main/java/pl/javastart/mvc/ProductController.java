package pl.javastart.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;
    private ShoppingCart shoppingCart;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String home(Model model) {
        List<Product> products = productRepository.getProducts();

//        for (Product product : products) {
//            if (shoppingCart.findByName(product.getName())!=null){
//
//            }else{
//                shoppingCart.addProduct(product);
//            }
//        }
        model.addAttribute("allProducts", products);
        Product product = products.get(1);
        model.addAttribute("newProduct", product);
        return "bakery";
    }

    @GetMapping("/koszyk/suma")
    public String sumaKoszyka(Model model) {
        List<Product> cart = productRepository.getProducts();
        double suma=0;

        for (Product product : cart) {
            suma=suma+product.getPrice();
        }
        model.addAttribute("cart", suma);
        return "summary";
    }

    @PostMapping("/added")
    public String addProduct(Product product) {
        Product existingProduct = productRepository.findByName(product.getName());

        if(existingProduct != null) {
            existingProduct.setNumber(product.getNumber());
        } else {

        }
        return "added";
    }

    @GetMapping("/koszyk")
    public String Koszyk(Model model) {
        List<Product> products = productRepository.getProducts();
//        String cartDesc="";
//        for (Product product : cart) {
//            cartDesc = cartDesc + product.toString();
//        }
        model.addAttribute("cart", products);
        return "basket";
    }
}
