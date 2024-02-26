package managment.products.Services.Interfaces;

import managment.products.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    Product getProductById(Long productId);
    Product addProduct(Product product);
    Page<Product> products(int page, int size);
    Product updateProduct(Long productId, Product product);
    boolean deleteById(Long productId);

}
