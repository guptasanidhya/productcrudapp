package productcrudapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Transactional
    public int newProduct(Product product)
    {
        System.out.println("I am inside service");
        return this.productDao.createProduct(product);
    }


    public List<Product> displayAllProduct(){
        System.out.println("displaying All product");
        return this.productDao.getProducts();
    }

    public void deleteProduct(int productId){
        this.productDao.deleteProduct(productId);
    }

    public Product getProduct(int productId){
        System.out.println("Get product");
        return this.productDao.getProduct(productId);
    }
}
