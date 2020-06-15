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

import bztf.shopapi.model.Order;
import bztf.shopapi.repository.OrderRepository;

/**
 * Order Controller
 * - Controller um http://localhost:8080/order zu beantworten
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    //Schnittstelle zur DB
    private final OrderRepository orderRepository;

    //Automatisch ausführen
    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    //HTTP-GET auf http://localhost:8080/order
    @GetMapping("/")
    public String showIndex(Model model){
        //alle produkte raussuchen
        model.addAttribute("orders", orderRepository.findAll());
        //Da das index eine liste der produkte ist lädt das product/index.html
        return "order/index.html";
    }

    //HTTP-POST auf http://localhost:8080/order/add
    //Orders werden nur in product/index.html erstellt, nicht eigenständig
    @PostMapping("add")
    public String addOrder(@Valid Order order, BindingResult result, Model model) {
        //GUI Fehler abfangen
        if(result.hasErrors()){
            return "product/index.html"; // zurück zur produktliste
        }

        //Order speichern und redirect
        orderRepository.save(order);
        return "redirect:/order";
    }

    ///HTTP reques auf ttp://localhost:8080/order/edit/{id}
    @RequestMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        //ID aus der URL nehmen
        Long longID = Long.parseLong(id);

        //Order raussuchen
        Optional<Order> ord = orderRepository.findById(longID);
        if(ord.isPresent()){
            //Model mit Order setzen
            model.addAttribute("order", ord.get());
        }

        //Template ordder/update-order.html zurückgeben
        return "order/update-order";
    }

    //HTTP-POST. ID über URL mitgegeben
    @PostMapping("update/{id}")
    public String updateOrder(@PathVariable("id") long id, @Valid Order order, BindingResult result,
        Model model)
    {
        //GUI Fehler abfangen
        if(result.hasErrors()){
            return "order/update-order";
        }

        //oder updaten
        orderRepository.save(order);
        //Alle orders auslesen
        model.addAttribute("orders", orderRepository.findAll());
        // Order Liste anzeigen
        return "order/index";
    }

    //HTTP-DELETE Die ID wird über URL mitgegeben
    @GetMapping("delete/{id}")
    public String deleteOrder(@PathVariable("id") long id, Model model){
        //order auf existenz prüfen
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ungültige Bestellung, ID:" + id));
        //oder löschen
        orderRepository.delete(order);
        //Liste der orders anzeigen
        model.addAttribute("orders", orderRepository.findAll());
        return "order/index";
    }

    //Datensätze als JSON abrufen -> für spätere erweiterungen
    //HTTP-GET auf http://localhost:8080/product/alljson
    @GetMapping(path = "alljson")
    public @ResponseBody Iterable<Order> getAllOrdersAsJSON(){
        //JSON zurückgeben
        return orderRepository.findAll();
    }

}