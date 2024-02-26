package managment.products.Controllers;

import managment.products.Entities.Product;
import managment.products.Services.Interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/index")
    public String products(Model model,
                           @RequestParam(name="page", defaultValue ="0") int page,
                           @RequestParam(name="size", defaultValue ="5") int size
    ) {
        Page products = productService.products(page, size);

        model.addAttribute("products", products);
        model.addAttribute("productsList", products.getContent());
        model.addAttribute("pages",new int[products.getTotalPages()]);
        model.addAttribute("currentPage",page);
        return "index";
    }

    @GetMapping("/newProductForm")
    public String newProductForm(Model model){
        model.addAttribute("product", new Product());
        return "newProductForm";
    }

    @PostMapping("add")
    public String addProduct(Model model, @ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("SucessMsg", "Product added successfully");
        return "redirect:/index";
    }

    @GetMapping("/updateProductForm")
    public String updateProductForm(Model model, @RequestParam(name="id") Long productId){
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "updateProductForm";
    }

    @PostMapping("update")
    public String updateProduct(Model model, @RequestParam(name="id") Long productId, @ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        productService.updateProduct(productId, product);
        redirectAttributes.addFlashAttribute("SucessMsg", "Product updated successfully");
        return "redirect:/index";
    }

    @GetMapping("delete")
    public String deleteById(Model model, @RequestParam(name="id") Long productId, RedirectAttributes redirectAttributes) {
        productService.deleteById(productId);
        redirectAttributes.addFlashAttribute("SucessMsg", "Product deleted successfully");
        return "redirect:/index";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
}
