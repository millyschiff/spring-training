package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jt on 11/6/15.
 */
@Controller
public class ProductController {

    private ProductServices productServices;

    @Autowired
    public void setProductService(ProductServices productServices) {
        this.productServices = productServices;
    }

    @RequestMapping("/products/list")
    public String listProducts(Model model){

        model.addAttribute("products", productServices.listAllProducts());

        return "products/list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable Integer id, Model model){

        model.addAttribute("product", productServices.getProductById(id));

        return "product/show";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productServices.getProductById(id));
        return "/product/productform";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(Product product){
        Product savedProduct = productServices.saveOrUpdateProduct(product);
        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable Integer id){

        productServices.deleteProduct(id);
        return "redirect:/products/list";
    }
}
