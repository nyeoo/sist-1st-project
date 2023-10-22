import java.util.*;

// �ֹ�
public class OrderSetting {
    void calculateOrderTotal() {            // ���� ���� ���� �� Į�θ�, �� ���� ���
        List<Order> OrderList = CacheData.userSelectOrderList;
        List<OrderValues> OrderValueList = CacheData.selectValueCart;

        OrderCart oC = new OrderCart();
        OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());

        if (OrderList.isEmpty()) {
            OrderList.add(new Order());
            OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());
        }
        else {
            OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());
        }

        for (Order order: OrderList){
            order.setO_totCalorie(0);
            order.setO_totPrice(0);
        }
        for (Order order: OrderList){
          for (OrderValues orderValues: OrderValueList){
              order.setO_totCalorie(order.getO_totCalorie()+(orderValues.getCalorie() * orderValues.getCount()));
              order.setO_totPrice(order.getO_totPrice()+(orderValues.getPrice() * orderValues.getCount()));
          }
        }
    }

    void calculateOrderDiscount() {            // ���� ���� ���� ����� ���ð� ���ֱ�
//        list1
        List<Product> allProductList = CacheData.allProductList;  //��� ����Ʈ
        List<Order> OrderList = CacheData.userSelectOrderList;  //��ٱ��� ����Ʈ
        List<OrderValues> OrderValueList = CacheData.selectValueCart; //���� ����Ʈ
        int productIdx = 0;
        for (Order order: OrderList){
            for (OrderValues orderValues: OrderValueList){
                for(Product list1 : allProductList){
                    if(list1.getP_name().equals(orderValues.getName())){
                        productIdx = allProductList.indexOf(list1);
                        break;
                    }
                }
                int productCount = allProductList.get(productIdx).getP_count();
                int productDiscount = orderValues.getCount();
                allProductList.get(productIdx).setP_count(productCount - productDiscount);
           }
        }

    }

    void printOrderList() {             // ���� �� ���
        List<Order> OrderList = CacheData.userSelectOrderList;
        List<OrderValues> OrderValueList = CacheData.selectValueCart;
        System.out.println("\n\t[ ���� ��ٱ��� ]");
        System.out.println("\t--------------------------------------------------------------------------");
        System.out.printf("\t%-4s| %-8s \t%-8s \t%-8s \t%-8s\n", "NO", "�̸�", "����Ͻð�", "��Į�θ�", "�ѱݾ�");
        System.out.println("\t--------------------------------------------------------------------------");

        int i=1,j=1;
        for (Order order: OrderList){
            System.out.printf("\t%-4d   %-8s \t%-8s \t%-8d \t%-8d\n", i++, order.getO_name(), order.getO_nowTime(), order.getO_totCalorie(), order.getO_totPrice());
            System.out.printf("\t%-4s) %-4s \t%-4s \t%-8s \t%-8s\n", "NO", "��ǰ", "���ż���", "Į�θ�", "�ݾ�");
            for (OrderValues orderValues: OrderValueList) {
                System.out.printf("\t%d-%d) %-8s \t%-8s \t%-8d \t%-8d\n", i-1 , j++, orderValues.getName(), orderValues.getCount(), orderValues.getCalorie() * orderValues.getCount(), orderValues.getPrice() * orderValues.getCount());
            }
        }
        System.out.println("\t--------------------------------------------------------------------------");

    }

}