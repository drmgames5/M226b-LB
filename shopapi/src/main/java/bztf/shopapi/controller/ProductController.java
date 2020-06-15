package bztf.shopapi.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bztf.shopapi.model.Product;
import bztf.shopapi.repository.ProductRepository;

/**
 * Product Controller
 * - Controller um http://localhost:8080/product zu beantworten
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    
    //Schnittstelle zur DB
    private final ProductRepository productRepository;

    //Automatisch ausführen
    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    //HTTP-GET auf http://localhost:8080/product
    @GetMapping("/")
    public String showIndex(Model model){
        //alle produkte raussuchen
        model.addAttribute("products", productRepository.findAll());
        //Da das index eine liste der produkte ist lädt das product/index.html
        return "product/index.html";
    }

    //HTTP-GET auf http://localhost:8080/product/create
    @GetMapping("create")
    public String showProdForm(Product product){
        return "product/create-product"; //lädt product/create-product.html
    }
    
    //HTTP-POST auf http://localhost:8080/product/add
    @PostMapping("add")
    public String addProduct(@Valid Product product, BindingResult result, Model model){
        //GUI Errors abfangen
        if(result.hasErrors()){
            return "product/create-product.html"; //product/create-product.html laden
        }

        //Product speichern und zum Produkt redirecten
        productRepository.save(product);
        return "redirect:/product/";
    }
    ///HTTP reques auf ttp://localhost:8080/product/edit/{id}
    @RequestMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        //ID wird aus der Url genommen
        Long longID = Long.parseLong(id);

        //Product mit dieser ID raussuchen
        Optional<Product> prod = productRepository.findById(longID);
        if(prod.isPresent()){
            //Model mit Product setzen -> verstehe ich nicht ganz
            model.addAttribute("product", prod.get());
        }

        //Template product/update-product.html aufrufen
        return "product/update-product";
    }

    // HTTP-POST. Die ID des Objekts wird über die URL mit gegeben
    @PostMapping("update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product, BindingResult result,
            Model model)
    {
        //GUI Fehler abfangen
        if(result.hasErrors()){
            return "product/update-product";
        }

        // Produkt updaten
        productRepository.save(product);
        // Alle Produkte auslesen
        model.addAttribute("products", productRepository.findAll());
        // Produkt liste anzeigen
        return "product/index";
    }

    //HTTP-DELETE Die ID des Objekts wird über die URL mit gegeben
    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        //Produkt holen und auf existenz prüfen
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ungültiges Produkt, ID:" + id));
        //Produkt löschen
        productRepository.delete(product);
        //Liste updateaufzeigen
        model.addAttribute("products", productRepository.findAll());
        return "product/index";
    }

    //Datensätze als JSON ausgeben -> für spätere erweiterungen
    //HTTP-GET auf htttp://localhost:8080/product/alljson
    @GetMapping(path = "alljson")
    public @ResponseBody Iterable<Product> getAllProductsAsJSON(){
        //JSON zurückgeben
        return productRepository.findAll();
    }
}