import java.util.ArrayList;
import java.util.List;

public class CacheData {
    static List<Order> orderOuterList = new ArrayList<>();      // ����� ���� �ٱ�(Ʋ) ����Ʈ
    static List<OrderValues> orderInnerValues;                  // ����� ���� ����(��) ����Ʈ
    static List<Product> allProductList = new ArrayList<>();             // ������õ�̿�(������ ������, ����, ���̵�, �����弼����� ��)�� ����Ʈ
    static List<MasterRc> masterProductList = new ArrayList<>();            // ������õ ����Ʈ
    static List<Product> list3 = new ArrayList<>();
    static List<MasterRc> list4 = new ArrayList<>();
//    static ArrayList<Receipt> receipts = SalesMg.receipts;

    public void testData(){
        // ����� ���� ����Ʈ �� �Է��� ���� ù��° ��ü �߰�
        orderOuterList.add(new Order("1", "20231012100200", 0, 0));
        orderInnerValues = orderOuterList.get(orderOuterList.size()-1).innerList;

        //  settingAllProductList
        allProductList.add(new Product(1,ProductType.S_BASE,1,"�����","80g",50,19,5,2000));
        allProductList.add(new Product(2,ProductType.S_BASE,1,"������","80g",50,19,5,2000));
        allProductList.add(new Product(3,ProductType.S_BASE,1,"�","170g",50,225,5,1000));
        allProductList.add(new Product(4,ProductType.S_MAIN,2,"�߰��","50g",50,60,5,2500));
        allProductList.add(new Product(5,ProductType.S_MAIN,2,"�Ұ��","40g",50,108,5,3000));
        allProductList.add(new Product(6,ProductType.S_MAIN,2,"����","60g",50,101,5,2500));
        allProductList.add(new Product(7,ProductType.S_MAIN,2,"����","50g",50,60,5,2800));
        allProductList.add(new Product(8,ProductType.S_MAIN,2,"���","50g",50,71,5,1000));
        allProductList.add(new Product(9,ProductType.S_SIDE,7,"�丶��","40g",50,7,5,400));
        allProductList.add(new Product(10,ProductType.S_SIDE,7,"�ø���","25g",50,46,5,700));
        allProductList.add(new Product(11,ProductType.S_SIDE,7,"ũ������","10g",50,31,5,700));
        allProductList.add(new Product(12,ProductType.S_SIDE,7,"��ٶ���","30g",50,25,5,1000));
        allProductList.add(new Product(13,ProductType.S_SIDE,7,"������","30g",50,39,5,1200));
        allProductList.add(new Product(14,ProductType.S_SIDE,7,"����","30g",50,2,5,1300));
        allProductList.add(new Product(15,ProductType.S_SIDE,7,"����Ÿġ��","14g",50,54,5,1000));
        allProductList.add(new Product(16,ProductType.S_SIDE,7,"�긮Ÿġ��","14g",50,54,5,1000));
        allProductList.add(new Product(17,ProductType.S_SOURCE,8,"������Ż","50g",50,128,5,500));
        allProductList.add(new Product(18,ProductType.S_SOURCE,8,"�߻��","50g",50,127,5,500));
        allProductList.add(new Product(19,ProductType.S_SOURCE,8,"����","50g",50,239,5,500));
        allProductList.add(new Product(20,ProductType.S_SOURCE,8,"ũ����","50g",50,237,5,500));
        allProductList.add(new Product(21,ProductType.S_SOURCE,8,"ĥ��","50g",50,237,5,500));
        allProductList.add(new Product(22,ProductType.S_SOURCE,8,"�������","50g",50,250,5,500));
        allProductList.add(new Product(23,ProductType.DRINK,3,"�ݶ�","250ml",50,80,5,1500));
        allProductList.add(new Product(24,ProductType.DRINK,3,"���̴�","250ml",50,110,5,1500));
        allProductList.add(new Product(25,ProductType.DRINK,3,"�ݶ�Zero","250ml",50,0,5,1600));
        allProductList.add(new Product(26,ProductType.DRINK,3,"���̴�Zero","250ml",50,0,5,1600));
        allProductList.add(new Product(27,ProductType.SIDE,4,"����̽���","100g",243,150,5,2500));
        allProductList.add(new Product(28,ProductType.SIDE,4,"��ġ���","100g",243,150,5,2500));
        allProductList.add(new Product(29,ProductType.SIDE,4,"����ƾ��","70g",50,414,5,1200));

        //  settingMasterRCProductList
        List<Product> r_products1 = new ArrayList<>();
        List<Product> r_products2 = new ArrayList<>();
        List<Product> r_products3 = new ArrayList<>();

        Product product1 = new Product(4,ProductType.S_MAIN,2,"�߰��","50g",50,60,5,2500);
        Product product2 = new Product(11,ProductType.S_SIDE,7,"ũ������","10g",50,31,5,700);
        Product product3 = new Product(19,ProductType.S_SOURCE,8,"����","50g",50,239,5,500);
        r_products1.add(product1);
        r_products1.add(product2);
        r_products1.add(product3);
        masterProductList.add(new MasterRc(1, ProductType.RCMND, "����ġŲ������",4000, 3500, r_products1,10,50));

        Product product11 = new Product(8,ProductType.S_MAIN,2,"���","50g",50,71,5,1000);
        Product product12 = new Product(13,ProductType.S_SIDE,7,"������","30g",50,39,5,1200);
        Product product13 = new Product(19,ProductType.S_SOURCE,8,"����","50g",50,239,5,500);
        r_products2.add(product11);
        r_products2.add(product12);
        r_products2.add(product13);
        masterProductList.add(new MasterRc(2, ProductType.RCMND, "�߻�����",4000, 2500, r_products2,10,50));

        Product product21 = new Product(6,ProductType.S_MAIN,2,"����","60g",50,101,5,2500);
        Product product22 = new Product(9,ProductType.S_SIDE,7,"�丶��","40g",50,7,5,400);
        Product product23 = new Product(20,ProductType.S_SOURCE,8,"ũ����","50g",50,237,5,500);
        r_products3.add(product21);
        r_products3.add(product22);
        r_products3.add(product23);
        masterProductList.add(new MasterRc(3, ProductType.RCMND, "���������", 4000, 3000, r_products3,10,50));


//        orderOuterList.add(new Order("�̸�1", "20231005153350", 200, 200));
//        orderOuterList.add(new Order("�̸�2", "20231005153350", 200, 200));
//        orderOuterList.add(new Order("�̸�3", "20231005153350", 200, 200));
//        orderOuterList.add(new Order("�̸�4", "20231005153350", 200, 200));
//        orderOuterList.add(new Order("�̸�5", "20231005153350", 200, 200));
//        for (int i = 0; i < 3; i++) {
//            orderOuterList.get(i).innerList.add(new OrderValues("���̽�", 1, 100, 200));
//        }




    }

    public CacheData(){
//        settingOuterOrder();  // �׽�Ʈ ������: ����ڼ��� �ٱ� ����Ʈ
//        settingInnerOrder();  // �׽�Ʈ ������: ����ڼ��� ���� ����Ʈ
    }

    void settingOuterOrder() {  // testData
//        orderOuterList.add(new Order("�̸�1", "20231005153350", 200, 200));
//        orderOuterList.add(new Order("�̸�2", "20231005153350", 200, 200));
//        orderOuterList.add(new Order("�̸�3", "20231005153350", 200, 200));
//        orderOuterList.add(new Order("�̸�4", "20231005153350", 200, 200));
//        orderOuterList.add(new Order("�̸�5", "20231005153350", 200, 200));
    }

    void settingInnerOrder() {  // testData
        for (int i = 0; i < 3; i++) {
            orderOuterList.get(i).innerList.add(new OrderValues("���̽�", 1, 100, 200));
        }
        System.out.println(orderOuterList.get(1).innerList.get(0).getCalorie());
    }

}