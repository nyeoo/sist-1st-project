import java.io.*;
import java.util.Iterator;
import java.util.List;

public class FoodSetting implements Serializable {
// ������ �޴� Ŭ����
    List<Product> allProductList = CacheData.allProductList;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
                    }
                }

                if (found) {
                    break; // �ùٸ� �з���ȣ�� ã�����Ƿ� ���� ����
                } else {
                    System.out.println("\t�� ���й�ȣ�� �������� �ʽ��ϴ�.");
                }

                KioskMg.foodadminflag = false;
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

                KioskMg.foodadminflag = false;
                break;

            } catch (NumberFormatException e) {
                System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
            }
        }
    } // soldout_management
}