package org.springframework.samples.petclinic.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final String VIEWS_USERS_CREATE_UPDATE_FORM = "products/createOrUpdateProductForm";
    
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/create")
    public String initCreationForm(ModelMap modelMap) {
        String view = VIEWS_USERS_CREATE_UPDATE_FORM;
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("productTypeList", productService.getAllProductTypes());
        return view;
    }

    @PostMapping(path = "/create")
    public String processCreationForm(@Valid Product product, BindingResult result, ModelMap modelMap) {
        String view = "welcome";
        if(result.hasErrors()) {
            modelMap.addAttribute("product", product);
            modelMap.addAttribute("productTypeList", productService.getAllProductTypes());
            return VIEWS_USERS_CREATE_UPDATE_FORM;
        } else {
            productService.save(product);
            modelMap.addAttribute("message", "Product successfully created");
        }
        return view;
    }


}
