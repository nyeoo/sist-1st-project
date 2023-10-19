/*
 �޴� ���� ----------------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

interface Super_Select_Interface {
    int menuSelect(int listSize,int minNum);
}

// ����
public abstract class Super_Select implements Super_Select_Interface {
    static BufferedReader br;
    protected String message;   // �Է¾ȳ� �޼���
    protected String errorMsg;  // ���� �޼���
    int minNum;                 // ���� �ּҰ�

    public int menuSelect(int listSize,int minNum) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(message);
                String userInput = br.readLine();
                if (userInput.isEmpty()) {
                    System.out.println("\t[!] �߸� �ԷµǾ����ϴ�. �ٽ� �Է����ּ���.");
                    continue;
                }
                userSelect = Integer.parseInt(userInput);
                if (userSelect < minNum || userSelect > listSize)
                    System.out.println(errorMsg);
            } while (userSelect < minNum || userSelect > listSize);
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        return userSelect;
    }

    public int menuSelectProduct(int listSize,int minNum,List<Product> productList,int menuUserSelect) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(message);
                String userInput = br.readLine();
                if (userInput.isEmpty()) {
                    System.out.println("\t[!] �߸� �ԷµǾ����ϴ�. �ٽ� �Է����ּ���.");
                    continue;
                }
                userSelect = Integer.parseInt(userInput);
                if (userSelect < minNum || userSelect > listSize){
                    System.out.println(errorMsg);
                }
                if(userSelect > listSize){
                    String userSelectName = productList.get(menuUserSelect-1).getP_name();
                    System.out.println("\t�� [" + userSelectName + "] ���Ű��� ���� : " + listSize);
                }
            } while (userSelect < minNum || userSelect > listSize);
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        return userSelect;
    }

    public int menuSelectMasterRc(int listSize,int minNum,List<MasterRc> productList,int menuUserSelect) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(message);
                String userInput = br.readLine();
                if (userInput.isEmpty()) {
                    System.out.println("\t[!] �߸� �ԷµǾ����ϴ�. �ٽ� �Է����ּ���.");
                    continue;
                }
                userSelect = Integer.parseInt(userInput);
                if (userSelect < minNum || userSelect > listSize){
                    System.out.println(errorMsg);
                }
                if(userSelect > listSize){
                    String userSelectName = productList.get(menuUserSelect-1).getR_name();
                    System.out.println("\t�� [" + userSelectName + "] ���Ű��� ���� : " + listSize);
                }
            } while (userSelect < minNum || userSelect > listSize);
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        return userSelect;
    }
}

// �޴��߰��ȳ� �� �޴�, ���� �Է¹ޱ�
class SelectContinue extends Super_Select {
    public void menuSelectProduct(List<Product> productList){
        this.message    = "\n\t�� �޴��� �߰� �Ͻðڽ��ϱ�?(Y/N): ";
        this.errorMsg   = "\t[!] �߸� �ԷµǾ����ϴ�. �ٽ� �Է����ּ���.";

        br = new BufferedReader(new InputStreamReader(System.in));
        InsertSelectValue insertSelectValue = new InsertSelectValue();
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        OrderValues orderValues = null;
        char answer;

        try {
            while (true){

                do {
                    // ��ٱ��� OuterList�� �ִ� �̸��� ���� ������ ���� limit�� ��Ƶΰ�, limit�� select�� �Ѱ��ֱ�
                    for (Product product: productList){
                        product.setP_limitCount(product.getP_count()-product.getP_stock());
                        for (OrderValues orderValues2: orderInnerValues){
                            if(orderValues2.getName().equals(product.getP_name())){
                                product.setP_limitCount(product.getP_limitCount() - orderValues2.getCount());
                            }
                        }
                    }

                    System.out.printf(message);
                    answer = br.readLine().charAt(0);
                }while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n');
                if(answer == 'N' || answer == 'n') // N�̰ų� n�̸� -> �ݺ��� ����������
                    break;

                orderValues = insertSelectValue.insertSelectValueProduct(productList);
                orderInnerValues.add(orderValues);

                System.out.println(orderInnerValues);            // test ���õ� �޴� ���

            }
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
    }

    public void menuSelectMasterRc(List<MasterRc> productList){
        this.message    = "\n\t�� �޴��� �߰� �Ͻðڽ��ϱ�?(Y/N): ";
        this.errorMsg   = "\t[!] �߸� �ԷµǾ����ϴ�. �ٽ� �Է����ּ���.";

        br = new BufferedReader(new InputStreamReader(System.in));
        InsertSelectValue insertSelectValue = new InsertSelectValue();
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        OrderValues orderValues = null;
        char answer;

        try {
            while (true){

                do {
                    // ��ٱ��� OuterList�� �ִ� �̸��� ���� ������ ���� limit�� ��Ƶΰ�, limit�� select�� �Ѱ��ֱ�
                    for (MasterRc masterRc: productList){
                        masterRc.setR_limitCount(masterRc.getR_count());
                        for (OrderValues orderValues2: orderInnerValues){
                            if(orderValues2.getName().equals(masterRc.getR_name())){
                                masterRc.setR_limitCount(masterRc.getR_limitCount() - orderValues2.getCount());
                            }
                        }
                    }

                    System.out.printf(message);
                    answer = br.readLine().charAt(0);
                }while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n');
                if(answer == 'N' || answer == 'n') // N�̰ų� n�̸� -> �ݺ��� ����������
                    break;

                orderValues = insertSelectValue.insertSelectValueMaster(productList);
                orderInnerValues.add(orderValues);

                System.out.println(orderInnerValues);            // test ���õ� �޴� ���

            }
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
    }
}

// ���� �� ����
class InsertSelectValue{
    public OrderValues insertSelectValueProduct(List<Product> productList){     //Procudt Ÿ���� �޴� ���� ���ð� ����
        // ���� �޴� ���� ����
        SelectMenu selectMenu = new SelectMenu();
        SelectCount selectCount = new SelectCount();

        int userSelect = selectMenu.menuSelect(productList.size(),1);
        int pdCount = productList.get(userSelect - 1).getP_limitCount();

        int userStock = selectCount.menuSelectProduct(pdCount,0,productList,userSelect); // ���� ��� ���� ����

        // ���� ���ð� �����
        return new OrderValues(
                productList.get(userSelect - 1).getP_name(),    // ���� ���� ��ǰ>�̸�
                userStock,                                      // ���� ���� ����
                productList.get(userSelect - 1).getP_calorie(), // ���� ���� ��ǰ>Į�θ�
                productList.get(userSelect - 1).getP_price()    // ���� ���� ��ǰ>����
        );
    }

    public OrderValues insertSelectValueMaster(List<MasterRc> productList){     //MasterRc Ÿ���� �޴� ���� ���ð� ����
        // ���� �޴� ���� ����
        SelectMenu selectMenu = new SelectMenu();
        SelectCount selectCount = new SelectCount();

        int userSelect = selectMenu.menuSelect(productList.size(),1);
        int totalPdCount = productList.get(userSelect - 1).getR_count(); // ������ ��� ����
        int pdCount = totalPdCount;

        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        for (OrderValues orderValues: orderInnerValues){
            if(orderValues.getName().equals(productList.get(userSelect - 1).getR_name())){
                pdCount -= orderValues.getCount();
            }
        }

        int userStock = selectCount.menuSelectMasterRc(pdCount,0,productList,userSelect); // ���� ��� ���� ����
        
        // ���� ���ð� �����
        return new OrderValues(
                productList.get(userSelect - 1).getR_name(),    // ���� ���� ��ǰ>�̸�
                userStock,                                      // ���� ���� ����
                productList.get(userSelect - 1).getR_totalcalorie(),  // ���� ���� ��ǰ>Į�θ�
                productList.get(userSelect - 1).getR_price()   // ���� ���� ��ǰ>����
        );
    }
}



// ���� ����
class SelectCount extends Super_Select {
    public SelectCount() {
        this.message    = "\t�� ���� ����: ";
        this.errorMsg   = "\t[!] ���� �������� ������ϴ�. �ٽ� �Է����ּ���.";
//        this.minNum = 0;
    }

    @Override
    public int menuSelect(int listSize,int minNum) {
        super.minNum = minNum;
        return super.menuSelect(listSize,minNum);
    }

    public int menuSelectProduct(int listSize,int minNum,List<Product> productList, int menuUserSelect) {
        super.minNum = minNum;
        return super.menuSelectProduct(listSize,minNum,productList,menuUserSelect);
    }

    public int menuSelectMasterRc(int listSize,int minNum,List<MasterRc> productList, int menuUserSelect) {
        super.minNum = minNum;
        return super.menuSelectMasterRc(listSize,minNum,productList,menuUserSelect);
    }

}