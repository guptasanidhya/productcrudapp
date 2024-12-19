package productcrudapp.dao;

import productcrudapp.model.Product;

import java.util.List;

public interface ProductDao {

    public void createProduct(Product product);
    public List<Product> getProducts();
    public void deleteProduct(int productId);
    public Product getProduct(int  productId);
}
