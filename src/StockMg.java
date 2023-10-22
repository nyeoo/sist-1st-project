import java.io.*;
import java.util.HashMap;
import java.util.List;

public class StockMg implements Serializable {
// ������ �޴� Ŭ����

    public final int E_SETTING = 1;                    //-- ���(ȸ��) �߰�
    public final int E_SOLDOUT = 2;
    public final int E_EXIT = 3;
    private static BufferedReader br;		         //-- ����ڰ� �Է½� Ȱ��
    private static Integer sel;				         //-- ���� ��

    List<Product> allProductList = CacheData.allProductList;

    static
    {
        //BufferedReader ��ü ����
        br = new BufferedReader(new InputStreamReader(System.in));

        // ����� �Է°� �ʱ�ȭ
        sel = 1;
    }



    public void menuDisp()
    {
        System.out.println("\n\t[ ������ �޴� ���� ]===========");
        System.out.println("\t1. ������");
        System.out.println("\t2. ǰ������");
        System.out.println("\t3. ������ �޴� ȭ������ �̵�");
        System.out.println("\t=================================");
        System.out.print("\t�� �޴� ����(1~3) : ");
    }
    public void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }
    public void menuRun() throws IOException, ClassNotFoundException
    {
        switch (sel)
        {
            case E_SETTING : product_setting(); break;
            case E_SOLDOUT : soldout_management(); break;
            case E_EXIT : exit(); break;
            default : System.out.print("\t[!] �޴� ���� ����");
        }
    }


    public void product_setting() throws IOException, ClassNotFoundException {
        System.out.println("\n\t[ 1. ��Ἴ�� ]");
        while (true) {
            try {
                System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
                int pointNumber = Integer.parseInt(br.readLine());
                boolean found = false;

                for (int i = 0; i < allProductList.size(); i++) {
                    if (pointNumber == allProductList.get(i).getP_material()) {
                        found = true;
                        break; // �ش� �з���ȣ�� ã�����Ƿ� ���� ����
                    }
                }

                for (int i = 0; i < allProductList.size(); i++) {
                    if (pointNumber == allProductList.get(i).getP_checkNumber()) {
                        found = true;
                        Product itS = allProductList.get(i);

                        System.out.println("\t==========================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                        System.out.println("\t==========================================================================================================");

                        String saleText = "";
                        saleText = itS.getSaleflag() ? "�Ǹ� O" : "�Ǹ� X";
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                itS.getP_stock(), itS.getP_price(), saleText);
                        System.out.println();

                        System.out.print("\t�� ������ �׸��� ���� �Է� : ");
                        int newCount = Integer.parseInt(br.readLine());
                        allProductList.get(i).setP_count(newCount);

                        break;
                    }
                }

                if (found) {
                    break; // �ùٸ� �з���ȣ�� ã�����Ƿ� ���� ����
                } else {
                    System.out.println("\t�� ���й�ȣ�� �������� �ʽ��ϴ�.");
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
            }

        }
    }

    public void soldout_management() throws IOException, ClassNotFoundException {
        System.out.println("\n\t[ 2. ǰ������ ]");
        while (true) {
            try {
                System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
                int pointNumber = Integer.parseInt(br.readLine());
                boolean found = false;

                for (int i = 0; i < allProductList.size(); i++) {
                    if (pointNumber == allProductList.get(i).getP_material()) {
                        found = true;
                        break; // �ش� �з���ȣ�� ã�����Ƿ� ���� ����
                    }
                }

                for (int i = 0; i < allProductList.size(); i++) {
                    if (pointNumber == allProductList.get(i).getP_checkNumber()) {
                        found = true;
                        Product itS = allProductList.get(i);

                        System.out.println("\t==========================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                        System.out.println("\t==========================================================================================================");

                        String saleText = "";
                        saleText = itS.getSaleflag() ? "�Ǹ� O" : "�Ǹ� X";
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                itS.getP_stock(), itS.getP_price(), saleText);
                        System.out.println();

                        System.out.print("\t�� ������ �׸��� ������� �Է� : ");
                        int newStock = Integer.parseInt(br.readLine());
                        allProductList.get(i).setP_stock(newStock);
                    }
                }

                if (found) {
                    break; // �ùٸ� �з���ȣ�� ã�����Ƿ� ���� ����
                } else {
                    System.out.println("\t�� ���й�ȣ�� �������� �ʽ��ϴ�.");
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
            }
        }
    } // soldout_management
    public void exit()
    {
        System.out.println("\n\t�� ������ �޴��� ���ư��ϴ�. ��");
        KioskMg.stockflag = false;
    }
}