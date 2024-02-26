package managment.products.Services.Services;

import managment.products.Entities.Product;
import managment.products.Repositories.ProductRepository;
import managment.products.Services.Interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public Product addProduct(Product product) {
        try{
            return productRepository.save(product);
        }catch(Exception e){
            e.printStackTrace();
            return new Product();
        }
    }

    @Override
    public Page<Product> products(int page, int size) {
        Page products = (Page) productRepository.findAll(PageRequest.of(page,size));
        return products;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        product.setId(productId);
        return productRepository.save(product);
    }

    @Override
    public boolean deleteById(Long productId) {
        productRepository.deleteById(productId);
        try{
            productRepository.findById(productId).get().getId();
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return true;
        }
    }
}
