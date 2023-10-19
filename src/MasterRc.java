import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Serializable;

class MasterRc implements Serializable ,Impl_admin
{
    private static final long serialVersionUID = -2026629343147755130L;
    Product product = new Product();
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int r_checkNumber;
    private String r_name;
    private int r_totalcalorie;
    private int r_price;
    private double r_discount;
    private List<Product> r_products; // ��� ���
    private ProductType type;
    private int r_count;
    private int r_limitCount;

    public ProductType getType() { return type; }

    public MasterRc(int r_checkNumber, ProductType type, String r_name, int r_totalcalorie, int r_price, List<Product> r_products,double r_discount, int r_count)
    {
        this.r_checkNumber = r_checkNumber;
        this.type = type;
        this.r_name = r_name;
        this. r_totalcalorie = r_totalcalorie;
        this.r_price = r_price;
        this.r_products = r_products;
        this.r_discount = r_discount;
        this.r_count = r_count;
    }

    public MasterRc(int r_checkNumber, String r_name, int r_totalcalorie, int r_price, List<Product> r_products)
    {
        this.r_checkNumber = r_checkNumber;
        this.r_name = r_name;
        this.r_price = r_price;
        this.r_products = r_products;
    }

    public MasterRc(){this(0, ProductType.RCMND, "", 0, 0, new ArrayList<>(),0,0);}
    public int getR_checkNumber(){return r_checkNumber;}
    public void setR_checkNumber(int r_checkNumber){this.r_checkNumber = r_checkNumber;}
    public String getR_name(){return r_name;}
    public void setR_name(String r_name){this.r_name = r_name;}
    public int getR_totalcalorie(){return r_totalcalorie;}
    public void setR_totalcalorie(int r_totalcalorie){this.r_totalcalorie = r_totalcalorie;}
    public int getR_price(){return r_price;}
    public void setR_price(int r_price){this.r_price = r_price;}
    public List<Product> getR_products(){return r_products;}
    public double getR_discount(){return r_discount;}
    public void setR_discount(double r_discount){this.r_discount = r_discount;}
    public int getR_count() { return r_count; }
    public void setR_count(int r_count) { this.r_count = r_count; }
    public void setR_products(List<Product> r_products)
    {
        this.r_products = r_products;
    }
    public int getR_limitCount() { return r_limitCount; }
    public void setR_limitCount(int r_limitCount) { this.r_limitCount = r_limitCount; }

    // MasterRc ��ü ���� ����� �� Į�θ� ���
    private int calculateTotalCalorie(List<Product> products)
    {
        int totalCalorie = 0;
        for (Product product : products)
        {
            totalCalorie += product.getP_calorie();
        }
        r_totalcalorie = totalCalorie;
        return totalCalorie;
    }

    // MasterRc ��ü ���� ����� �� �ݾ� ���
    private int calculateTotalPrice(List<Product> products)
    {
        int totalPrice = 0;
        for (Product product : products)
        {
            totalPrice += product.getP_price();
        }
        return totalPrice;
    }

    // MasterRc ��ü ���� ��� ���� ���
    public int calculateMinCount(List<Product> products)
    {
        int minCount=10000;
        for (Product product : products)
        {
            if(minCount > product.getP_count())
                minCount = product.getP_count();
        }
        return minCount;
    }


    @Override
    public void ad_print()
    {
        List<MasterRc> masterProduct = CacheData.masterProductList;

        System.out.println("\n\t[ 1. ���� ��õ ���� ��� ]");
        System.out.println("\t===========================================================================================");
        System.out.printf("\t|| %5s || %8s || %5s || %5s || %5s || %5s \n",
                "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "����", "���");
        System.out.println("\t===========================================================================================");

        // existingList2�� �ִ� MasterRc ��ü�� ���
        for (MasterRc masterRc : masterProduct)
        {
            
            System.out.printf("\t|| %5d || %8s || %5d || %5d || %5d ",
                    masterRc.getR_checkNumber(), masterRc.getR_name(),
                    r_totalcalorie, masterRc.getR_price(), masterRc.getR_count());
            String materialDetail="";
            for (int i = 0; i<masterRc.getR_products().size(); i++)
            {
                if(i!=0) materialDetail +=", ";
                String materialItem = masterRc.getR_products().get(i).getP_name();
                materialDetail += materialItem;
            }
            System.out.printf("|| %12s\n",materialDetail);


            // ������� ���
            for (Product product : masterRc.getR_products())
            {
                System.out.printf("\t- �����: %8s\t|| ��ᰳ��: %5d\n",product.getP_name(),product.getP_count());
            }
            System.out.println();
        }
        System.out.println("\t===========================================================================================");

        KioskMg.masterrcflag = false;

    }


    @Override
    public void ad_add() throws IOException, ClassNotFoundException {
        List<MasterRc> list2 = CacheData.masterProductList;

        try {
            System.out.println();
            System.out.println("\n\t[ 2.�ű� ���� ��õ ���� ��� ]");
            System.out.println("\t===========================================================================================");
            System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                    "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���");
            System.out.println("\t===========================================================================================");

            List<Product> selectedProducts = new ArrayList<>();
            List<Product> product = CacheData.allProductList;

            int materialDetailCount = 3;
            while (true) {
                System.out.printf("\t*** [�ȳ�] ���տ� �� ����� ����: %d\n", materialDetailCount);
                System.out.print("\t�� ���տ� �� ����� ���й�ȣ �Է�: ");
                int pointNumber = Integer.parseInt(br.readLine());
                if (pointNumber == 0) {
                    break;
                }

                boolean found = false;

                for (Product p : product) {
                    if (pointNumber == p.getP_checkNumber()) {
                        found = true;
                        selectedProducts.add(p);

                        System.out.println("\t�� ������ ���:");
                        for (Product selected : selectedProducts) {
                            System.out.println("\t\t-�̸�: " + selected.getP_name() + ", ����: " + selected.getP_count());
                        }
                    }
                }

                if (!found) {
                    System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                }
            }

            // Į�θ� ���� ���
            r_totalcalorie = calculateTotalCalorie(selectedProducts);
            // �ݾ� ���� ���
            r_price = calculateTotalPrice(selectedProducts);
            // ��� ���� ���
            r_count = calculateMinCount(selectedProducts);

            System.out.println("\t�� ������õ ������� �հ� :" + r_price);
            System.out.println("\t*** [�ȳ�] '������õ'�� ���� '��������հ�' ���� ���� �ݾ��� �Է����ּ���.");

            System.out.print("\t�� ���� ������ �� [���й�ȣ,�̸�,�ݾ�] �Է�(�����̽��� ����) : ");
            String input = br.readLine();
            StringTokenizer tokenizer = new StringTokenizer(input, " ");

            if (tokenizer.countTokens() != 3) {
                System.out.println("\t�Է��� �׸��� ������ ���� �ʽ��ϴ�.");
                return;
            }

            int r_checkNumber = Integer.parseInt(tokenizer.nextToken());
            String r_name = tokenizer.nextToken();
            int r_price = Integer.parseInt(tokenizer.nextToken());

            // ������ ���
            double r_discount = 100 - ((r_price / r_price) * 100);

            // �̹� �����ϴ� ���й�ȣ���� Ȯ��
            boolean isDuplicate = false;
            for (MasterRc existingRc : list2) {
                if (r_checkNumber == existingRc.getR_checkNumber()) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println("\t[!] �̹� ���й�ȣ�� �����մϴ�.");
                return;
            } else {
                System.out.print("\t�����Ͻðڽ��ϱ�?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y') {
                    // MasterRc ��ü ���� �� ����Ʈ�� �߰�
                    MasterRc masterRc = new MasterRc(r_checkNumber, ProductType.RCMND, r_name, r_totalcalorie, r_price, selectedProducts, r_discount, r_count);
                    list2.add(masterRc);
                    System.out.println("\t�� ����Ǿ����ϴ�. ��");
                } else {
                    System.out.println("\t[!] �ùٸ� ���� �Է��Ͻÿ�.");
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
        }

        KioskMg.masterrcflag = false;
    }


    @Override
    public void ad_modify() throws IOException, ClassNotFoundException {
        try {
            List<MasterRc> existingList2 = CacheData.masterProductList;
            List<MasterRc> list4 = CacheData.list4;

            // ����ȭ �ּ� 231018
//            FileMg f = new FileMg();

            System.out.println();
            System.out.println("\n\t[ 3. �������� ���� ]");
            System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
            int pointNumber = Integer.parseInt(br.readLine());
            boolean found = false;

            for (int i = 0; i < existingList2.size(); i++) {
                if (pointNumber == existingList2.get(i).getR_checkNumber()) {
                    found = true;
                    MasterRc masterRc = existingList2.get(i);

                    System.out.println("\t===========================================================================================");
                    System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                            "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���");
                    System.out.printf("\t|| %5d || %5s || %5d || %5d ||",
                            masterRc.getR_checkNumber(), masterRc.getR_name(),
                            calculateTotalCalorie(masterRc.getR_products()), masterRc.getR_price());

                    // ������� ���
                    for (Product product : masterRc.getR_products()) {
                        System.out.print(" "+product.getP_name());
                    }
                    System.out.println(" ||");
                    System.out.println("\t===========================================================================================");

                    System.out.println("\t������ ������ �����Ͻÿ�.");
                    System.out.println("\t1.���й�ȣ 2. �̸� 3. �ݾ�");
                    System.out.println();
                    while (true) {
                        System.out.print("\t�� ������ ������ ���� �Է� (0: ���� �Ϸ�) : ");
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 0) {
//                            f.list2FileOut();
//                            f.list4FileOut();
                            System.out.println("\t�� �����Ǿ����ϴ�. ��");
                            break;
                        }
                        switch (choice) {
                            case 1:
                                System.out.print("\t���ο� ���й�ȣ �Է�: ");
                                int newCheckNumber = Integer.parseInt(br.readLine());
                                masterRc.setR_checkNumber(newCheckNumber);
                                for (MasterRc p : list4) {
                                    if (p.getR_checkNumber() == pointNumber) {
                                        p.setR_checkNumber(newCheckNumber);
                                    }
                                }
                                break;
                            case 2:
                                System.out.print("\t���ο� �̸� �Է�: ");
                                String newName = br.readLine();
                                masterRc.setR_name(newName);
                                for (MasterRc p : list4) {
                                    if (p.getR_checkNumber() == pointNumber) {
                                        p.setR_name(newName);
                                    }
                                }
                                break;
                            case 3:
                                System.out.print("\t���ο� �ݾ� �Է�: ");
                                int newPrice = Integer.parseInt(br.readLine());
                                masterRc.setR_price(newPrice);
                                for (MasterRc p : list4) {
                                    if (p.getR_checkNumber() == pointNumber) {
                                        p.setR_price(newPrice);
                                    }
                                }
                                break;
                            default:
                                System.out.println("\t�Էµ� �׸��� �����ϴ�.");
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
        }
        KioskMg.masterrcflag = false;
    }


    @Override
    public void ad_delete() throws IOException, ClassNotFoundException {
        try {
            // ������ȭ ���� ������ �ҷ�����
            List<MasterRc> existingList2 = CacheData.masterProductList;
            List<MasterRc> list4 = CacheData.list4;
            // ����ȭ �ּ� 231018
            //FileMg f = new FileMg();

            System.out.println("\n\t[ 4. �������� ���� ]");
            System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
            int pointNumber = Integer.parseInt(br.readLine());
            boolean found = false;

            int deleteIndex = -1; // ������ �������� �ε��� �ʱ�ȭ

            for (int i = 0; i < existingList2.size(); i++) {
                if (pointNumber == existingList2.get(i).getR_checkNumber()) {
                    found = true;
                    MasterRc masterRc = existingList2.get(i);

                    System.out.println("\t===========================================================================================");
                    System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                            "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���");
                    System.out.printf("\t|| %5d || %5s || %5d || %5d ||",
                            masterRc.getR_checkNumber(), masterRc.getR_name(),
                            calculateTotalCalorie(masterRc.getR_products()), masterRc.getR_price());

                    // ������� ���
                    for (Product product : masterRc.getR_products()) {
                        System.out.print(" "+product.getP_name());
                    }
                    System.out.println(" ||");
                    System.out.println("\t===========================================================================================");

                    System.out.print("\t�����Ͻðڽ��ϱ�?(Y/N) : ");
                    char x = br.readLine().charAt(0);
                    if (x == 'Y' || x == 'y') {
                        deleteIndex = i; // ���� ��� ����� �ε��� ����
                        for (Iterator<MasterRc> iterator = list4.iterator(); iterator.hasNext(); ) {
                            MasterRc p = iterator.next();
                            if (p.getR_checkNumber() == pointNumber) {
                                iterator.remove(); // list4���� �����ϰ� ����
                            }
                        }
                        break;
                    } else {
                        System.out.println("\t[!] �ùٸ� ���� �Է��Ͻÿ�.");
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("\t���й�ȣ�� ��ġ���� �ʽ��ϴ�");
                return; // ��ġ���� ������ ���� �۾��� ���� �ʰ� ����
            }

            // ���� ��� ��ᰡ ������ ��쿡�� ���� ����
            if (deleteIndex != -1) {
                existingList2.remove(deleteIndex);
                System.out.println("\t�� �����Ǿ����ϴ�. ��");

//                f.list2FileOut();
//                f.list4FileOut();
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
        }

        KioskMg.masterrcflag = false;
    }

}
