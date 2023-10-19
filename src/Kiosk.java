import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 Ű����ũ ----------------------------------------------------------------
*/
class Kiosk {
    final String USER_NAME = "��";      //-- ����� ������ �̸�
    public Imp_info info;                      //-- info Ʋ ����

    static boolean storeflag = true;

    public Kiosk(ProductService productService) {
        this.info = new InfoService(productService);
    }
    public void kioskStart() throws IOException {
        storePack();    // ���� or ���� ����
        menuDisp();     // ����� �޴� ����

        // ���ð� üũ
        SelectMenu selectMenu = new SelectMenu();
        int listSize = 4;
        int userSelect = selectMenu.menuSelect(listSize,1);
        menuRun(userSelect);

        // ���ð� ����Ʈ�� ���
        OrderSetting orderSetting = new OrderSetting();
        orderSetting.calculateOrderTotal();
        orderSetting.printOrderList();

        cart ca = new cart();               //-- ��ٱ��� �ν��Ͻ� ����
        do
        {
            ca.menuDis();
            ca.menuSel(userSelect);
            ca.menuR(userSelect);
        }
        while (true);
    }

    public void storePack() {
        List<Order> OrderList = CacheData.orderOuterList;
        String payMe = null;
        System.out.println("\n\t[ ����/���� ���� ]=============");
        System.out.println("\t1. ����");
        System.out.println("\t2. ����");
        System.out.println("\t==============================");
        SelectMenu selectMenu = new SelectMenu();
        int listSize = 2;
        int adminNum = -1;
        int userSelect = selectMenu.menuSelect(listSize,adminNum);

        if(userSelect==1)
        {
            storeflag = false;
        }
        else {
            storeflag = true;
        }

        if(userSelect == adminNum){
            System.out.println("\n\t�� ������ȭ������ �̵��մϴ�. ��");
            ad_login al = new ad_login();

            try {
                al.adLogin();
            } catch (IOException e) {
                System.out.println("e.toString: " + e.toString());
                System.out.println("e.getMessage: " + e.getMessage());
                System.out.println("printStackTrace................");
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                System.out.println("e.toString: " + e.toString());
                System.out.println("e.getMessage: " + e.getMessage());
                System.out.println("printStackTrace................");
                e.printStackTrace();
            }
        }

        // ���ð� �迭-���� �ӽ� �̸� �ֱ�
        List<Order> outerList = CacheData.orderOuterList;           //-- ����� ���� �ٱ�(Ʋ) ����
        List<Order> orderOuterList = CacheData.orderOuterList;      //-- ����� ���� ����(��) ����

        if (orderOuterList.isEmpty()) {
            orderOuterList.add(new Order(USER_NAME + 1, "20231012100200", 0, 0));
        }
        else {
            int outerListSize = outerList.size();
            outerList.set(outerList.size()-1,new Order(USER_NAME + outerListSize, "20231012100200", 0, 0));
        }
    }

    public void menuDisp() {
        System.out.println("\n\t[ ����� �޴� ���� ]===========");
        System.out.println("\t1. ������õ");
        System.out.println("\t2. ������ ������");
        System.out.println("\t3. ����");
        System.out.println("\t4. ���̵�");
//        System.out.println("\t - �ڷΰ���(c)");
        System.out.println("\t=============================");
    }

    public void menuRun(int userSelect) {
        // userSelect�� ProductType���� ��ȯ
        ProductTypeChange productTypeChange = new ProductTypeChange();
        ProductType productType = productTypeChange.ProductTypeChange(userSelect);

        switch (productType) { // DESC: swtich���� ���ǿ� String Ÿ���� �ְԵǸ�, case������ enumŸ������ ���� �� �� ����
            case RCMND:
                menuRcmd();
                break;
            case MY_SALAD:
                menuMySalad();
                break;
            case DRINK:
                menuDrink();
                break;
            case SIDE:
                menuSide();
                break;
            case CANCEL:
                menuCancel();
                break;
        }
    }

    public void menuRcmd() {
        System.out.println("\n\t[ 1. ������õ ]");
        info.printInfo(ProductType.RCMND);
    }

    public void menuMySalad() {
        System.out.println("\n\t[ 2. ������ ������ ]");

        System.out.println("\t[ ���̽� �� �� �� �� ]");
        info.printInfo(ProductType.S_BASE);

        System.out.println("\t[ �� �������� �� �� �� ]");
        info.printInfo(ProductType.S_MAIN);

        System.out.println("\t[ �� �� ���̵����� �� �� ]");
        info.printInfo(ProductType.S_SIDE);

        System.out.println("\t[ �� �� �� �ҽ� �� ]");
        info.printInfo(ProductType.S_SOURCE);
    }

    public void menuDrink() {
        System.out.println("\n\t[ 3. ���� ]");
        info.printInfo(ProductType.DRINK);
    }

    public void menuSide() {
        System.out.println("\n\t[ 4. ���̵� ]");
        info.printInfo(ProductType.SIDE);
    }

    public void menuCancel() {

    }
}