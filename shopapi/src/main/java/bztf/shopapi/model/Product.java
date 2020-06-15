package bztf.shopapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Entity : Product
 */
@Entity // Das sagt Hibernate, dass es einen Tabelle in der Datenbank anlegen soll
public class Product {

    @Id // ID 
    @GeneratedValue(strategy = GenerationType.AUTO) // Autoincrement
    private long id;

    @NotBlank(message = "Name is mandatory") // NOT NULL
    @Column(name = "name") // Name der Column
    private String name;

    @NotBlank(message = "Price is mandatory") // NOT NULL
    @Column(name = "price") // Name der Column
    private double price;

    @Column(name = "stock") // Name der Column
    private int stock;

    @NotBlank(message = "Image Path is mandatory") // NOT NULL
    @Column(name = "img") // Name der Column
    private String img; //Dies ist nur der Pfad zum Bild, bzw der Name des Bildes

    //Constructor -> name, price, stock
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.img = "../../imgs/karrote.jpg";    //img ist immer das gleiche weil prototyp
    }

    //Constuctor -> name, price
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.stock = 0;
        this.img = "../../imgs/karrote.jpg";
    }

    public long getId() {
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return this.price;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getStock(){
        return this.stock;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return img;
    }
}