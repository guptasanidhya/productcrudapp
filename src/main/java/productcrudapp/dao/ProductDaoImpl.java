package productcrudapp.dao;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import productcrudapp.model.Product;

import java.util.List;

@Repository
public class ProductDaoImpl implements  ProductDao{

    @Autowired
    private SessionFactory sessionFactory;

    public ProductDaoImpl() {
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //create
    @Override
    @Transactional
    public int createProduct(Product product) {

        Session session = sessionFactory.getCurrentSession();
        if (session != null && session.isOpen()) {
            System.out.println("Session is open: " + session);
            if (product.getId() == 0) {
                session.persist(product);
                System.out.println("Product added successfully: " + product.getId());
                session.flush();
            } else {
                System.out.println("using deprecated method");
                session.saveOrUpdate(product);
                System.out.println("Product updated or added successfully: " + product.getId());
            }
        } else {
            System.out.println("Session is not open");
        }
        return product.getId();
    }



    @Transactional
    @Override
    public List<Product> getProducts()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product", Product.class).getResultList();
    }

    @Transactional
    @Override
    public Product getProduct(int  productId){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class,productId);
    }




    @Transactional
    @Override
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
