package Models.MyShop;

public class Product {
    String title;
    double price;
    int quantity;
    double totalProductPrice;

    public Product(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.totalProductPrice = this.price * this.quantity;
    }
}
