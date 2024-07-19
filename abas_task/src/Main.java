//Sipariş     Mal Numarası        Miktar              Birim Fiyat (TL)
//1000        2000                12                  100,51
//1000        2001                31                  200
//1000        2002                22                  150,86
//1000        2003                41                  250
//1000        2004                55                  244

//1001        2001                88                  44,531
//1001        2002                121                 88,11
//1001        2004                74                  211
//1001        2002                14                  88,11

//1002        2003                2                   12,1
//1002        2004                3                   22,3
//1002        2003                8                   12,1
//1002        2002                16                  94
//1002        2005                9                   44,1
//1002        2006                19                  90

import java.util.ArrayList;

public class Main {
    public static ArrayList<Table_order> mergeOrders(ArrayList<Table_order> orders) {  //Created to merge orders
        ArrayList<Table_order> mergedOrders = new ArrayList<>();

        for (Table_order order : orders) {
            boolean found = false;
            for (Table_order mergedOrder : mergedOrders) {

                //If product_id appears more than once, sums the amount and puts them together
                if (mergedOrder.order_id == order.order_id && mergedOrder.product_id == order.product_id) {
                    mergedOrder.amount += order.amount;
                    found = true;
                    break;
                }
            }
            if (!found) {
                mergedOrders.add(new Table_order(order.order_id, order.product_id, order.amount, order.price));
            }
        }

        return mergedOrders;
    }
    public static void main(String[] args) {

        //Created an arraylist to insert the table values
        ArrayList<Table_order> tableOrderArrayList = new ArrayList<>();

        tableOrderArrayList.add(new Table_order(1000, 2000, 12, 100.51));
        tableOrderArrayList.add(new Table_order(1000, 2001, 31, 200));
        tableOrderArrayList.add(new Table_order(1000, 2002, 22, 150.86));
        tableOrderArrayList.add(new Table_order(1000, 2003, 41, 250));
        tableOrderArrayList.add(new Table_order(1000, 2004, 55, 244));

        tableOrderArrayList.add(new Table_order(1001, 2001, 88, 44.531));
        tableOrderArrayList.add(new Table_order(1001, 2002, 121, 88.11));
        tableOrderArrayList.add(new Table_order(1001, 2004, 74, 211));
        tableOrderArrayList.add(new Table_order(1001, 2002, 14, 88.11));

        tableOrderArrayList.add(new Table_order(1002, 2003, 2, 12.1));
        tableOrderArrayList.add(new Table_order(1002, 2004, 3, 22.3));
        tableOrderArrayList.add(new Table_order(1002, 2003, 8, 12.1));
        tableOrderArrayList.add(new Table_order(1002, 2002, 16, 94));
        tableOrderArrayList.add(new Table_order(1002, 2005, 9, 44.1));
        tableOrderArrayList.add(new Table_order(1002, 2006, 19, 90));

        //-------------------------------------------------------------------------------------------
        System.out.println("Üç siparişteki malların toplam tutarının çıktısını veren java kodu.");
        //a. Üç siparişteki malların toplam tutarının çıktısını veren java kodu.

        ArrayList<Table_order> mergedOrders = mergeOrders(tableOrderArrayList);

        double total_price = 0;
        for (Table_order total : mergedOrders) {
            total_price += total.total_price();
        }
        System.out.println("Toplam Fiyat: " + total_price); //toplam fiyatı verir

        double total_for_1000 = 0;
        for (Table_order total : mergedOrders) {
            if (total.order_id == 1000){
                total_for_1000 += total.total_price();
            }
        }
        System.out.println("Toplam Fiyat 1000: " + total_for_1000); //1000 nolu order_id için toplam fiyat verir

        double total_for_1001 = 0;
        for (Table_order total : mergedOrders) {
            if (total.order_id == 1001){
                total_for_1001 += total.total_price();
            }
        }
        System.out.println("Toplam Fiyat 1001: " + total_for_1001); //1001 nolu order_id için toplam fiyat verir

        double total_for_1002 = 0;
        for (Table_order total : mergedOrders) {
            if (total.order_id == 1002){
                total_for_1002 += total.total_price();
            }
        }
        System.out.println("Toplam Fiyat 1002: " + total_for_1002); //1002 nolu order_id için toplam fiyat verir

        //------------------------------------------------------------------------------------------------------
        System.out.println();
        System.out.println("Üç siparişteki bütün malların ortalama fiyatını bulan java kodu.");
        //b. Üç siparişteki bütün malların ortalama fiyatını bulan java kodu.

        double total_price_all = 0;
        int total_amount_all = 0;

        for (Table_order order : mergedOrders) {
            total_price_all += order.total_price(); //Ürünlerin amount*price fonksiyonu ile toplam fiyatını saklar
            total_amount_all += order.amount;       //Her sipariş için toplam miktar tutulur ve eklenir
        }

        double average_price_all = total_price_all / total_amount_all; //2 saklanan değer birbirine bölündüğü taktirde bütün malların ortalama fiyatı karşımıza çıkmaktadır
        System.out.println("Üç siparişteki bütün malların ortalama fiyatı: " + average_price_all);

        //-----------------------------------------------------------------------------------------------------

        System.out.println();
        System.out.println("Üç siparişteki bütün malların tek tek mal bazlı ortalama fiyatını bulan java kodu.");

        ArrayList<third_question> average_price = new ArrayList<>();
        //Birbirleri ile karşılaştırmalı gidileceği için yeni bir class oluşturuldu -->third_question

        for (Table_order order : mergedOrders) {
            boolean flag = false;
            for (third_question data : average_price) {
                if (data.productId == order.product_id) { //product_id eşit olduğu taktirde
                    data.total_price += order.total_price(); //spesifik mal için her bir siparişden edinilen total_price güncelleniyor
                    data.total_amount += order.amount; //spesifik mal için her bir miktar da toplanıyor
                    flag = true;
                    break;
                }
            }
            if (!flag) { //flag kontrol amaçlı eğer listede yoksa yeni bir obje oluşturması için
                average_price.add(new third_question(order.product_id, order.total_price(), order.amount));
            }
        }

        for (third_question data : average_price) {
            double averagePrice = data.total_price / data.total_amount; //Her bir mal numarası için ortalama fiyatı verir
            System.out.println("Mal Numarası: " + data.productId + ", Ortalama Fiyat: " + averagePrice);
        }

        //--------------------------------------------------------------------------------------------
        System.out.println();
        System.out.println("Tek tek mal bazlı, malların hangi siparişlerde kaç adet olduğunun çıktısını veren java kodu");


        ArrayList<ProductData> productDataList = new ArrayList<>();
        //Aynı 3. sorudaki gibi karşılaştırma amaçlı yeni bir class oluşturuldu

        for (Table_order order : tableOrderArrayList) { //Genel veri setimiz
            boolean productFlag = false;

            for (ProductData product : productDataList) { //Mal takibi
                if (product.productId == order.product_id) {

                    boolean orderFlag = false;
                    for (OrderData orderData : product.orders) { //Sipariş numarası için
                        if (orderData.orderId == order.order_id) {
                            //Eğer veri orderData içinde varsa amount ekleniyor her bir mal için
                            orderData.amount += order.amount;
                            orderFlag = true;
                            break;
                        }
                    }
                    if (!orderFlag) {
                        product.orders.add(new OrderData(order.order_id, order.amount));
                    }
                    productFlag = true;
                    break;
                }
            }

            if (!productFlag) {
                ProductData newProduct = new ProductData(order.product_id);
                newProduct.orders.add(new OrderData(order.order_id, order.amount));
                productDataList.add(newProduct);
            }
        }

        for (ProductData product : productDataList) {
            System.out.println("Mal Numarası: " + product.productId);
            for (OrderData order : product.orders) {
                System.out.println("Sipariş Numarası: " + order.orderId + ", Miktar: " + order.amount);
            }
        }

    }


}