package pl.javastart.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

        for (Product product : products) {
            if (shoppingCart.findByName(product.getName())!=null){
                
            }else{
                shoppingCart.addProduct(product);
            }
        }
        model.addAttribute("allProducts", products);
        return "bakery";
    }

    @GetMapping("/koszyk/suma")
    public String sumaKoszyka() {
        List<Product> cart = shoppingCart.getShoppingCart();
        double suma=0;

//        model.addAttribute("allProducts", cart);
        for (Product product : cart) {
            suma=suma+product.getPrice();
        }
        return "Do zapłaty " + suma;
    }

    @PostMapping("/added")
    public String addProduct(Product product) {
        Product existingProduct = shoppingCart.findByName(product.getName());

        if(existingProduct != null) {
            existingProduct.setNumber(product.getNumber());
        } else {

        }
        return "added";
    }

    @GetMapping("/koszyk")
    public String Koszyk() {
        List<Product> cart = shoppingCart.getShoppingCart();
        String cartDesc="";
        for (Product product : cart) {
            cartDesc = cartDesc + product.toString();
        }
        return cartDesc;
    }
}
