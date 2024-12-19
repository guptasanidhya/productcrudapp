package productcrudapp.dao;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import productcrudapp.model.Product;

import java.util.List;

@Component("productDao")
public class ProductDaoImpl implements  ProductDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override


    //create
    @Transactional
    public void createProduct(Product product) {

        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
        session.flush(); //flush the session so that user get save instantly othewise until session is closed it will not be saved
        System.out.println("Product has been added successfully");
    }

    @Transactional
    public List<Product> getProducts()
    {
        Session session = sessionFactory.getCurrentSession();
        List<Product> products = session.createQuery("from Product", Product.class).getResultList();
        return products;
    }

    @Transactional
    public Product getProduct(int  productId){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class,productId);
    }




    @Transactional
    public void deleteProduct(int productId){
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class,productId);
        //delete method deprecated in 6.0
        if (product != null) {
            session.remove(product);
            System.out.println("product deleted with ID: " + productId);
        } else {
            System.out.println("product not found with ID: " + productId);
        }
    }
}
