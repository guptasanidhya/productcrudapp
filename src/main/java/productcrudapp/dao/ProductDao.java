package productcrudapp.dao;

import org.springframework.stereotype.Repository;
import productcrudapp.model.Product;

import java.util.List;
@Repository
public interface ProductDao {

    public int createProduct(Product product);
    public List<Product> getProducts();
    public void deleteProduct(int productId);
    public Product getProduct(int  productId);
}
