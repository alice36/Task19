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

    public ProductController(ProductRepository productRepository, ShoppingCart shoppingCart) {
        this.productRepository = productRepository;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productRepository.getProducts();

        model.addAttribute("allProducts", products);
        Product product = new Product();
        model.addAttribute("newProduct", product);
        return "bakery";
    }

    @GetMapping("/koszyk/suma")
    public String sumaKoszyka(Model model) {
        List<Product> cart = shoppingCart.getShoppingCart();
        double suma=0;

        for (Product product : cart) {
            suma=suma+product.getPrice()*product.getNumber();
        }
        model.addAttribute("cart", suma);
        return "summary";
    }

    @PostMapping("/added")
    public String addProduct(Product product) {
        Product existingProduct = shoppingCart.findByName(product.getName());

        if(existingProduct != null) {
            existingProduct.setNumber(existingProduct.getNumber()+product.getNumber());
        } else {
            shoppingCart.addProduct(product);
        }
        return "added";
    }

    @GetMapping("/koszyk")
    public String Koszyk(Model model) {
        List<Product> products = shoppingCart.getShoppingCart();
//        String cartDesc="";
//        for (Product product : cart) {
//            cartDesc = cartDesc + product.toString();
//        }
        model.addAttribute("cart", products);
        return "basket";
    }
}
