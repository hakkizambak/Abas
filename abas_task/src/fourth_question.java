import java.util.ArrayList;
import java.util.List;

class OrderData {
    int orderId;
    int amount;

    public OrderData(int orderId, int amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
}

class ProductData {
    int productId;
    List<OrderData> orders;

    public ProductData(int productId) {
        this.productId = productId;
        this.orders = new ArrayList<>();
    }
}
