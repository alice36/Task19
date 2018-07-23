package pl.javastart.mvc;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingCart {
    private List<Product> shoppingCart;

    public ShoppingCart() {
       shoppingCart = new ArrayList<>();
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void addProduct(Product product) {
        shoppingCart.add(product);
    }

    public Product findByName(String name) {
        for (Product product : shoppingCart) {
            if(product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}
