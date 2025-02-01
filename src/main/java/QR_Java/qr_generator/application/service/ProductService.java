package QR_Java.qr_generator.application.service;

import QR_Java.qr_generator.application.data.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(Product updatedProduct) {
        int index = products.indexOf(updatedProduct);
        if (index != -1) {
            products.set(index, updatedProduct);
        }
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }
}
