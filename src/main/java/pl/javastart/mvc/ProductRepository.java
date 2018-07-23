package pl.javastart.mvc;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productsTab;

    public ProductRepository() {
        productsTab = new ArrayList<>();

        productsTab.add(new Product ("Bułka z ziarnem", 1.5, "/images/ziarno.jpg"));
        productsTab.add(new Product ("Bułka pszenna", 1.2, "/images/zwykla.jpg"));
        productsTab.add(new Product ("Chleb zwykły", 3.5,"/images/chleb.jpg"));
        productsTab.add(new Product ("Chleb orkiszowy", 4.35,"/images/orkisz.jpg"));
    }

    public List<Product> getProducts() {
        return productsTab;
    }

    public void addProduct(Product product) {
        productsTab.add(product);
    }

    public Product findByName(String name) {
        for (Product product : productsTab) {
            if(product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}
