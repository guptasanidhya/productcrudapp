package productcrudapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

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
}
