public class Table_order {

    int order_id;
    int product_id;
    int amount;
    double price;

    public Table_order(int order_id,int product_id,int amount,double price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.amount = amount;
        this.price = price;
    }

    public double total_price(){ //function for the first one
        return price*amount;
    }
}





