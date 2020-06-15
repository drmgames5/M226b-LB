package bztf.shopapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Entity : Order
 */
@Entity // Das sagt Hibernate, dass es einen Tabelle in der Datenbank anlegen soll
public class Order {

    @Id // ID 
    @GeneratedValue(strategy = GenerationType.AUTO) // Autoincrement
    private long id;

    @NotBlank(message = "Product is mandatory") // NOT NULL
    @Column(name = "product") // Name der Column
    private Product product;

    @Column(name = "qty") // Name der Column   -> qty = quantity
    private int qty;

    @Column(name = "price") // Name der Column
    private double price;

    public Order(Product product, int qty) {
        this.product = product;
        this.qty = qty;
        this.price = Double.valueOf(this.qty) * product.getPrice();
    }

    public Order(Product product) {
        this.product = product;
        this.qty = 1;
        this.price = Double.valueOf(this.qty) * product.getPrice();
    }

    public long getId() {
        return this.id;
    }

    public Product getProduct(){
        return this.product;
    }

    public void setQty(int qty){
        this.qty = qty;
        this.price = Double.valueOf(this.qty) * product.getPrice();
    }

    public int getQty(){
        return this.qty;
    }

    public double getPrice(){
        return this.price;
    }

}