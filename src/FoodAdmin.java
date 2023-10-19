import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.io.InputStreamReader;

public class FoodAdmin implements Serializable {
    // Assuming Product and MasterRc classes are defined somewhere

    Product product = new Product();
    MasterRc masterrc = new MasterRc();

    KioskMg km = new KioskMg();

    List<Product> list1 = CacheData.allProductList;
    List<MasterRc> list2 = CacheData.masterProductList;

    List<Product> list3 = CacheData.list3;
    List<MasterRc> list4 = CacheData.list4;




    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void setting_print() throws IOException, ClassNotFoundException{
        // ����ȭ �ּ� 231018
//        FileMg f = new FileMg();
//        f.list1FileIn();
//        f.list3FileIn();
//        f.list2FileIn();
//        f.list4FileIn();

        System.out.println("\n\t[ �Ǹ����� ��� ]===============");
        System.out.println("\t1. �Ǹ� ������� ���\n\t2. �Ǹ� ������õ ���� ���");
        System.out.println("\t==============================");

        while (true) {
            try {
                System.out.print("\t�� ������ �׸��� ���� �Է� : ");
                int z = Integer.parseInt(br.readLine());
                switch (z)
                {
                    case 1:
                        System.out.println("\n\t[ 1. �Ǹ� ������� ��� ]");
                        System.out.println("\t===========================================================================================");
                        System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�");
                        System.out.println("\t===========================================================================================");

                        // Iterator Ȱ���Ͽ� ���
                        Iterator<Product> itList;
                        itList = list3.iterator();
                        while (itList.hasNext()) {
                            Product itS = itList.next();
                            System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                    itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                    itS.getP_stock(), itS.getP_price());
                        }
                        System.out.println("\t===========================================================================================");
                        System.out.println();
                        return;

                    case 2:
                        System.out.println("\n\t[ 2.���� ��õ ���� ��� ]");
                        System.out.println("\t===========================================================================================");
                        System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s || %5s \n",
                                "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���", "����");
                        System.out.println("\t===========================================================================================");

                        // existingList2�� �ִ� MasterRc ��ü�� ���
                        for (MasterRc masterRc : list4) {
                            System.out.printf("\t|| %5d || %5s || %5d || %5d || %5d \n",
                                    masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie(),
                                    masterRc.getR_price(), masterRc.getR_count());

                            // ������� ���
                            for (Product product : masterRc.getR_products()) {
                                System.out.println("\t����̸�: " + product.getP_name() + " || ��� ����: " + product.getP_count());
                            }

                            System.out.println();
                            break;
                        }
                        System.out.println("\t===========================================================================================");
                        System.out.println();

                        return;
                    default:
                        System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\t[!] �ùٸ� ���ڸ� �Է��ϼ���.");
            }
        }
    }

    public void product_setting() throws IOException, ClassNotFoundException {

        while (true) {
            try {
                System.out.println("\n\t[ 2. �Ǹ��׸� ���� ]=============");
                System.out.println("\t1. �Ǹ���� ����  \n\t2. �ǸŻ������� ����");
                System.out.println("\t============================");
                System.out.print("\t�� ������ �׸��� ���� �Է� : ");
                int c = Integer.parseInt(br.readLine());

                switch (c) {
                    case 1:
                        System.out.println("\n\t[ 1.�Ǹ���� ���� ]");
                        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
                        int pointNumber = Integer.parseInt(br.readLine());
                        boolean found = false;
                        boolean k = false;

                        for (int i = 0; i < list3.size(); i++) {
                            if (pointNumber == list3.get(i).getP_checkNumber()) {
                                k = true;
                                break;
                            }
                        }
                        if (k) {
                            System.out.println("\t[!] �̹� ���й�ȣ�� �����մϴ�.");
                            return;
                        }

                        for (int i = 0; i < list1.size(); i++) {
                            if (pointNumber == list1.get(i).getP_checkNumber()) {
                                found = true;
                                Product selectedProduct = list1.get(i);

                                System.out.println("\t===========================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                        "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�");
                                System.out.println("\t===========================================================================================");

                                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                        selectedProduct.getP_checkNumber(), selectedProduct.getP_material(), selectedProduct.getP_name(),
                                        selectedProduct.getP_unit(), selectedProduct.getP_count(), selectedProduct.getP_calorie(),
                                        selectedProduct.getP_stock(), selectedProduct.getP_price());
                                System.out.println("\t===========================================================================================");
                                System.out.println();

                                System.out.print("\t�� �Ǹ��׸� �߰��Ͻðڽ��ϱ�?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // ���õ� �׸��� �ٸ� �迭�� ����
                                    list3.add(selectedProduct);
                                    System.out.println("\t�� ����Ǿ����ϴ�. ��");

                                    return;
                                } else
                                    return;
                            }
                        }
                        if (!found) {
                            System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                            break;
                        }
                        break;

                    case 2:
                        System.out.println("\n\t[ 2. �ǸŻ������� ���� ]");
                        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
                        int pointNumber2 = Integer.parseInt(br.readLine());
                        boolean found2 = false;
                        boolean p = false;
                        for (int i = 0; i < list4.size(); i++) {
                            if (pointNumber2 == list4.get(i).getR_checkNumber()) {
                                p = true;
                                break;
                            }
                        }
                        if (p) {
                            System.out.println("\t[!] �̹� ���й�ȣ�� �����մϴ�.");
                            return;
                        }

                        for (int i = 0; i < list2.size(); i++) {
                            if (pointNumber2 == list2.get(i).getR_checkNumber()) {
                                found2 = true;
                                MasterRc selectedMasterRc = list2.get(i);

                                System.out.println("\t===========================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                                        "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���");
                                System.out.printf("\t|| %5d || %5s || %5d || %5d ||\n",
                                        selectedMasterRc.getR_checkNumber(), selectedMasterRc.getR_name(),
                                        selectedMasterRc.getR_totalcalorie(), selectedMasterRc.getR_price());
                                System.out.println("\t===========================================================================================");

                                System.out.println();
                                System.out.print("\t�� �Ǹ��׸� �߰��Ͻðڽ��ϱ�?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    list4.add(selectedMasterRc);
                                    System.out.println("\t�� ����Ǿ����ϴ�. ��");
                                    return;
                                } else
                                    return;
                            }
                        }

                        if (!found2) {
                            System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                            break;
                        }
                        break;

                    default:
                        System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\t[!] �ùٸ� ���ڸ� �Է��ϼ���.");
            }
        }
    }

    public void soldout_management() throws IOException, ClassNotFoundException {

        while (true) {
            try {
                System.out.println("\n\t[ 3. �Ǹ��׸� ���� ]============");
                System.out.println("\t1. �Ǹ���� ����  \n\t2. �ǸŻ������� ����");
                System.out.println("\t============================");
                System.out.print("\t�� ������ �׸��� ���� �Է� : ");
                int c = Integer.parseInt(br.readLine());

                switch (c) {
                    case 1:
                        System.out.println("\n\t[ 1.�Ǹ���� ���� ]");
                        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
                        int pointNumber = Integer.parseInt(br.readLine());
                        boolean found3 = false;

                        for (int i = 0; i < list3.size(); i++) {
                            if (pointNumber == list3.get(i).getP_checkNumber()) {
                                found3 = true;
                                Product selectedProduct = list3.get(i);

                                System.out.println("\t===========================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                        "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�");
                                System.out.println("\t===========================================================================================");

                                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                        selectedProduct.getP_checkNumber(), selectedProduct.getP_material(), selectedProduct.getP_name(),
                                        selectedProduct.getP_unit(), selectedProduct.getP_count(), selectedProduct.getP_calorie(),
                                        selectedProduct.getP_stock(), selectedProduct.getP_price());
                                System.out.println();
                                System.out.println("\t===========================================================================================");

                                System.out.print("\t�� �Ǹ��׸� �����Ͻðڽ��ϱ�?(Y/N) :");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // ���õ� �׸��� �ٸ� �迭�� ����
                                    list3.remove(selectedProduct);
                                    System.out.println("\t�� ����Ǿ����ϴ�. ��");

                                    return;
                                } else
                                    return;
                            }
                        }

                        if (!found3) {
                            System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                            break;
                        }
                        break;
                    case 2:
                        System.out.println("\n\t[ 2. �ǸŻ������� ���� ]========================================================================");
                        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
                        int pointNumber2 = Integer.parseInt(br.readLine());
                        boolean found4 = false;

                        for (int i = 0; i < list4.size(); i++) {
                            if (pointNumber2 == list4.get(i).getR_checkNumber()) {
                                found4 = true;
                                MasterRc selectedMasterRc = list4.get(i);

                                System.out.println("\t===========================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                                        "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���");
                                System.out.printf("\t|| %5d || %5s || %5d || %5d ||",
                                        selectedMasterRc.getR_checkNumber(), selectedMasterRc.getR_name(),
                                        selectedMasterRc.getR_totalcalorie(), selectedMasterRc.getR_price());
                                System.out.println("\t===========================================================================================");

                                System.out.print("\t�� �Ǹ��׸� �����Ͻðڽ��ϱ�?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    list4.remove(selectedMasterRc);
                                    System.out.println("\t�� ����Ǿ����ϴ�. ��");
                                    return;
                                } else
                                    return;
                            }
                        }

                        if (!found4) {
                            System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                            break;
                        }
                        break;
                    default:
                        System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\t[!] �ùٸ� ���ڸ� �Է��ϼ���.");
            }
        }
    }

}
