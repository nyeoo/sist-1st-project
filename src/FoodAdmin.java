import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.io.InputStreamReader;

public class FoodAdmin implements Serializable {
    // Assuming Product and MasterRc classes are defined somewhere

    List<Product> allProductList = CacheData.allProductList;
    List<MasterRc> masterProductList = CacheData.masterRcList;

    int materialDetailCount = 3;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String SaleTrue = "�Ǹ� O";
    String SaleFalse = " �Ǹ� X";

    public void setting_print() throws IOException, ClassNotFoundException{
//        FileMg f = new FileMg();
//        f.list1FileIn();

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
                        System.out.println("\t=============================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                        System.out.println("\t=============================================================================================================");

                        for(Product allProductList:allProductList){
                            if(allProductList.getSaleflag()){     // �Ǹ������� true �϶�
                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", allProductList.getP_checkNumber(),
                                        allProductList.getP_material(), allProductList.getP_name(), allProductList.getP_unit(), allProductList.getP_count(), allProductList.getP_calorie(),
                                        allProductList.getP_stock(), allProductList.getP_price(), SaleTrue);
                            }
                        }
                        System.out.println("\t=============================================================================================================");
                        System.out.println();
                        return;

                    case 2:
                        System.out.println("\n\t[ 2. ���� ��õ ���� ��� ]");
                        System.out.println("\t=============================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���", "����", "�Ǹſ���", "���(" + materialDetailCount +")");
                        System.out.println("\t=============================================================================================================");

                        // MasterRc ��ü�� ���
                        for (MasterRc masterRc : masterProductList) {
                            if(masterRc.getSaleflag()){     // �Ǹ������� true �϶�
                                System.out.printf("\t|| %5d || %5s || %5d || %5d || %5d \n",
                                    masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie()
                                        , masterRc.getR_price(), masterRc.getR_count());

                                // ����� ���
                                String materialDetail="";
                                for (int i = 0; i<masterRc.getR_products().size(); i++)
                                {
                                    if(i!=0) materialDetail +=", ";
                                    String materialItem = masterRc.getR_products().get(i).getP_name();
                                    materialDetail += materialItem;
                                }
                                System.out.printf("|| %-12s ||\n",materialDetail);

                                // ������� ���
                                for (Product product : masterRc.getR_products()) {
                                    System.out.printf("\t- �����: %8s\t|| ��ᰳ��: %5d\n",product.getP_name(),product.getP_count());
                                }
                            }
                        }
                        System.out.println("\t=============================================================================================================");
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
                        System.out.println("\n\t[ 1. �Ǹ���� ���� ]");

                        // �Ǹ�X ���
                        System.out.println("\n\t[ �Ǹ�X ��� ��� ]");
                        System.out.println("\t==========================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                        System.out.println("\t==========================================================================================================");

                        Iterator<Product> itList = allProductList.iterator();
                        while (itList.hasNext())
                        {
                            Product itS = itList.next();
                            if(!itS.getSaleflag()){
                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                        itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                        itS.getP_stock(), itS.getP_price(), SaleFalse);
                            }
                        }
                        System.out.println("\t==========================================================================================================");
                        System.out.println();

                        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
                        int pointNumber = Integer.parseInt(br.readLine());
                        boolean found = false;
                        boolean k = false;

                        for (int i = 0; i < allProductList.size(); i++) {
                            if (pointNumber == allProductList.get(i).getP_checkNumber() && allProductList.get(i).getSaleflag()) {
                                k = true;
                                break;
                            }
                        }
                        if (k) {
                            System.out.println("\t[!] �̹� ���й�ȣ�� �����մϴ�.");
                            return;
                        }

                        for (int i = 0; i < allProductList.size(); i++) {
                            if (pointNumber == allProductList.get(i).getP_checkNumber()) {
                                found = true;
                                Product selectedProduct = allProductList.get(i);

                                System.out.println("\t=============================================================================================================");
                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                        "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                                System.out.println("\t=============================================================================================================");

                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                        selectedProduct.getP_checkNumber(), selectedProduct.getP_material(), selectedProduct.getP_name(),
                                        selectedProduct.getP_unit(), selectedProduct.getP_count(), selectedProduct.getP_calorie(),
                                        selectedProduct.getP_stock(), selectedProduct.getP_price(), SaleFalse);
                                System.out.println("\t=============================================================================================================");
                                System.out.println();

                                System.out.print("\t�� �Ǹ��׸� �߰��Ͻðڽ��ϱ�?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // ���õ� �׸� ����
                                    selectedProduct.setSaleflag(true);
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
                        for (int i = 0; i < masterProductList.size(); i++) {
                            if (pointNumber2 == masterProductList.get(i).getR_checkNumber() && masterProductList.get(i).getSaleflag()) {
                                p = true;
                                break;
                            }
                        }
                        if (p) {
                            System.out.println("\t[!] �̹� ���й�ȣ�� �����մϴ�.");
                            return;
                        }

                        for (int i = 0; i < masterProductList.size(); i++) {
                            if (pointNumber2 == masterProductList.get(i).getR_checkNumber()) {
                                found2 = true;
                                MasterRc selectedMasterRc = masterProductList.get(i);

                                System.out.println("\t=============================================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                                        "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���");
                                System.out.printf("\t|| %5d || %5s || %5d || %5d ||\n",
                                        selectedMasterRc.getR_checkNumber(), selectedMasterRc.getR_name(),
                                        selectedMasterRc.getR_totalcalorie(), selectedMasterRc.getR_price());
                                System.out.println("\t=============================================================================================================");

                                System.out.println();
                                System.out.print("\t�� �Ǹ��׸� �߰��Ͻðڽ��ϱ�?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // ���õ� �׸� ����
                                    selectedMasterRc.setSaleflag(true);
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

                        for (int i = 0; i < allProductList.size(); i++) {
                            if (pointNumber == allProductList.get(i).getP_checkNumber() && allProductList.get(i).getSaleflag()) {
                                found3 = true;
                                Product selectedProduct = allProductList.get(i);

                                System.out.println("\t=============================================================================================================");
                                System.out.printf("\t|| %5s || %5s || %9s  || %5s|| %5s || %5s || %5s || %5s ||\n",
                                        "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�");
                                System.out.println("\t=============================================================================================================");

                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s ||\n",
                                        selectedProduct.getP_checkNumber(), selectedProduct.getP_material(), selectedProduct.getP_name(),
                                        selectedProduct.getP_unit(), selectedProduct.getP_count(), selectedProduct.getP_calorie(),
                                        selectedProduct.getP_stock(), selectedProduct.getP_price());
                                System.out.println("\t=============================================================================================================");

                                System.out.print("\t�� �Ǹ��׸� �����Ͻðڽ��ϱ�?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // ���õ� �׸� ����
                                    selectedProduct.setSaleflag(false);
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

                        for (int i = 0; i < masterProductList.size(); i++) {
                            if (pointNumber2 == masterProductList.get(i).getR_checkNumber() && masterProductList.get(i).getSaleflag()) {
                                found4 = true;
                                MasterRc selectedMasterRc = masterProductList.get(i);

                                System.out.println("\t=============================================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                                        "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���");
                                System.out.printf("\t|| %5d || %5s || %5d || %5d ||\n",
                                        selectedMasterRc.getR_checkNumber(), selectedMasterRc.getR_name(),
                                        selectedMasterRc.getR_totalcalorie(), selectedMasterRc.getR_price());
                                System.out.println("\t=============================================================================================================");

                                System.out.print("\t�� �Ǹ��׸� �����Ͻðڽ��ϱ�?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // ���õ� �׸� ����
                                    selectedMasterRc.setSaleflag(false);
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
