package productcrudapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;
import productcrudapp.service.ProductService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductService  productService;

    @RequestMapping("/")
    public String home(Model m){

        List<Product> products = productService.displayAllProduct();

        m.addAttribute("products",products);
        return "index";
    }


    //show add product form
    @RequestMapping("/add-product")
    public String addProduct(Model m ){
        m.addAttribute("title","Add Product");
        return "add_product_form";
    }

    //handle add product form

    @RequestMapping(value="/handle-product", method = RequestMethod.POST)
    public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest httpServletRequest)
    {
        System.out.println(product);
        RedirectView redirectView = new RedirectView();

        int r = this.productService.newProduct(product);
        redirectView.setUrl(httpServletRequest.getContextPath()+"/");

        return redirectView;
    }

    @RequestMapping("/delete/{productId}")
    public RedirectView deleteProduct(@PathVariable("productId")int productId , HttpServletRequest httpServletRequest){
        RedirectView redirectView = new RedirectView();
        this.productService.deleteProduct(productId);

        redirectView.setUrl(httpServletRequest.getContextPath()+"/");
        return redirectView;
    }


}
