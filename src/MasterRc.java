import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Serializable;

class MasterRc implements Serializable, Impl_admin
{
    private static final long serialVersionUID = -2026629343147755130L;
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int r_checkNumber;
    private String r_name;
    private int r_totalcalorie;
    private int r_price;
    private double r_discount;
    private List<Product> r_products; // ��� ���
    private int[] r_details = new int[3]; // ��� ��� ��
    private ProductType type;
    private int r_count;
    private int r_limitCount;
    private boolean Saleflag;

    String SaleTrue = CacheData.SaleTrue;
    String SaleFalse = CacheData.SaleFalse;

    public ProductType getType() { return type; }

    int materialDetailCount = 3;

    public MasterRc(int r_checkNumber, ProductType type, String r_name, int r_totalcalorie, int r_price, List<Product> r_products, double r_discount, int r_count, boolean Saleflag)
    {
        this.r_checkNumber = r_checkNumber;
        this.type = type;
        this.r_name = r_name;
        this.r_totalcalorie = r_totalcalorie;
        this.r_price = r_price;
        this.r_products = r_products;
        this.r_discount = r_discount;
        this.r_count = r_count;
        this.Saleflag = Saleflag;
    }

    public MasterRc(int r_checkNumber, ProductType type, String r_name, int r_totalcalorie, int r_price, int[] r_details, double r_discount, int r_count, boolean Saleflag) {
        this.r_checkNumber = r_checkNumber;
        this.type = type;
        this.r_name = r_name;
        this.r_totalcalorie = r_totalcalorie;
        this.r_price = r_price;
        this.r_details = r_details;
        this.r_discount = r_discount;
        this.r_count = r_count;
        this.Saleflag = Saleflag;
    }

    public MasterRc() {
        this(0, ProductType.RCMND, "", 0, 0, new ArrayList<>(), 0, 0, false);
    }

    public int getR_checkNumber() {
        return r_checkNumber;
    }

    public void setR_checkNumber(int r_checkNumber) {
        this.r_checkNumber = r_checkNumber;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public int getR_totalcalorie() {
        return r_totalcalorie;
    }

    public void setR_totalcalorie(int r_totalcalorie) {
        this.r_totalcalorie = r_totalcalorie;
    }

    public int getR_price() {
        return r_price;
    }

    public void setR_price(int r_price) {
        this.r_price = r_price;
    }

    public List<Product> getR_products() {
        return r_products;
    }

    public double getR_discount() {
        return r_discount;
    }

    public void setR_discount(double r_discount) {
        this.r_discount = r_discount;
    }

    public int getR_count() {
        return r_count;
    }

    public void setR_count(int r_count) {
        this.r_count = r_count;
    }

    public void setR_products(List<Product> r_products) {
        this.r_products = r_products;
    }

    public int getR_limitCount() {
        return r_limitCount;
    }

    public void setR_limitCount(int r_limitCount) {
        this.r_limitCount = r_limitCount;
    }

    public boolean getSaleflag() {
        return Saleflag;
    }

    public void setSaleflag(boolean Saleflag) {
        this.Saleflag = Saleflag;
    }

    public int[] getR_details() {
        return r_details;
    }

    public void setR_details(int r_details) {
        this.r_details = new int[]{r_details};
    }

    // MasterRc ��ü ���� ����� �� Į�θ� ���
    private int calculateTotalCalorie_01(int[] detailNum) {
        int totalCalorie = 0;
        for (int i = 0; i < detailNum.length; i++) {
            for (Product p1 : CacheData.allProductList) {
                if (i == p1.getP_checkNumber()){
                    totalCalorie += p1.getP_calorie();
                }
            }
        }
        r_totalcalorie = totalCalorie;
        return totalCalorie;
    }

    // MasterRc ��ü ���� ����� �� �ݾ� ���
    private int calculateTotalPrice_01(int[] detailNum) {
        int totalPrice = 0;
        for (int i = 0; i < detailNum.length; i++) {
            for (Product p1 : CacheData.allProductList) {
                if (i == p1.getP_checkNumber()){
                    totalPrice += p1.getP_price();
                }
            }
        }
        return totalPrice;
    }

    // MasterRc ��ü ���� ��� ���� ���
    public int calculateMinCount_01(int[] detailNum) {
        int minCount = 10000;
        for (int i = 0; i < detailNum.length; i++) {
            for (Product p1 : CacheData.allProductList) {
                if (detailNum[i] == p1.getP_checkNumber()){
                    if (minCount > p1.getP_count())
                        minCount = p1.getP_count();
                }

            }
        }
        return minCount;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public final int E_PRINT = 1;
    public final int E_ADD = 2;
    public final int E_MOD = 3;
    public final int E_DEL = 4;
    public final int E_EXIT = 5;


    private static Integer sel;				         //-- ���� ��


    static
    {
        // ����� �Է°� �ʱ�ȭ
        sel = 1;
    }

    public void menuDisp()
    {
        System.out.println("\n\t[ ������õ ���� �޴� ���� ]===========");
        System.out.println("\t1. ��õ���� ���");
        System.out.println("\t2. ��õ���� ���");
        System.out.println("\t3. ��õ���� ���� ����");
        System.out.println("\t4. ��õ���� ���� ����");
        System.out.println("\t5. ������ �޴� ȭ������ �̵�");
        System.out.println("\t=================================");
        System.out.print("\t�� �޴� ����(1~5) : ");
    }

    public void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    // �޴� ���࿡ ���� ��� ȣ�� �޼ҵ�
    public void menuRun() throws IOException, ClassNotFoundException
    {
        switch (sel)
        {
            case E_PRINT : ad_print(); break;
            case E_ADD : ad_add(); break;
            case E_MOD : ad_modify(); break;
            case E_DEL : ad_delete(); break;
            case E_EXIT : exit(); break;
            default : System.out.print("\t[!] �޴� ���� ����");
        }
    }

    @Override
    public void ad_print() {
        List<MasterRc> masterProductList = CacheData.masterRcList;

        System.out.println("\n\t[ 1. ���� ��õ ���� ��� ]");
        System.out.println("\t=============================================================================================================");
        System.out.printf("\t|| %5s || %-9s || %5s || %5s || %5s || %5s || %5s ||\n",
                "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "����", "�Ǹſ���", "���(" + materialDetailCount + ")");
        System.out.println("\t=============================================================================================================");

        // MasterRc ��ü�� ���
        for (MasterRc masterRc : masterProductList) {
            // �Ǹ����� ���
            String saleText = "";
            saleText = masterRc.getSaleflag() ? SaleTrue : SaleFalse;

            // ���� ����ȭ
            int calTotalMin = calculateMinCount_01(masterRc.r_details);
            masterRc.setR_count(calTotalMin);

            System.out.printf("\t|| %5d || %-9s || %5d || %5d || %5d || %5s ",
                    masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie()
                    , masterRc.getR_price(), calTotalMin, saleText);

            // ����� ���
            String materialDetail = "";
            for (int i = 0; i < masterRc.getR_details().length; i++) {
                for (Product p1 : CacheData.allProductList) {
                    if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                        if (i != 0){
                            materialDetail += ", ";
                        }
                        String materialItem = p1.getP_name();
                        materialDetail += materialItem;
                    }
                }
            }
            System.out.printf("|| %-12s\n", materialDetail);

            // ������� ���
            for (int i = 0; i < masterRc.getR_details().length; i++) {
                for (Product p1 : CacheData.allProductList) {
                    if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                        System.out.printf("\t- �����: %8s\t|| ��ᰳ��: %5d\n", p1.getP_name(), p1.getP_count());
                    }
                }
            }
        }
        System.out.println("\t=============================================================================================================");
    }

    @Override
    public void ad_add() throws IOException{
        List<MasterRc> masterProductList = CacheData.masterRcList;

        try {
            System.out.println();
            System.out.println("\n\t[ 2. �ű� ���� ��õ ���� ��� ]");
            System.out.println("\t=============================================================================================================");
            System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                    "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "���(" + materialDetailCount + ")");
            System.out.println("\t=============================================================================================================");

            List<Product> selectedProducts = new ArrayList<>();
            int[] detailNum = new int[materialDetailCount];

            int idx = 1;
            System.out.printf("\n\t*** [�ȳ�] ���տ� �� ����� ����: %d ***\n", materialDetailCount);
            while (idx <= materialDetailCount) {
                System.out.print("\t�� ���տ� �� ���(" + idx + ")�� ���й�ȣ �Է�: ");
                int pointNumber = Integer.parseInt(br.readLine());
                boolean found = false;

                for (Product p : CacheData.allProductList) {
                    if (pointNumber == p.getP_checkNumber() && p.getSaleflag()) {
                        idx++;
                        found = true;
                        selectedProducts.add(p);
                        detailNum [idx-2] = pointNumber;

                        System.out.println("\t�� ������ ��� : ");
                        for (int i : detailNum) {
                            for (Product p1 : CacheData.allProductList) {
                                if (i == p1.getP_checkNumber()){
                                    System.out.println("\t\t-�̸� : " + p1.getP_name() + ", ���� : " + p1.getP_count());
                                }
                            }
                        }
                    }
                }

                if (!found) {
                    System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                }
            }

            // Į�θ� ���� ���
            int calTotalcalorie = calculateTotalCalorie_01(detailNum);
            // �ݾ� ���� ���
            int originTotalPrice = calculateTotalPrice_01(detailNum);
            // ��� ���� ���
            int calCount = calculateMinCount_01(detailNum);

            System.out.println("\n\t*** [�ȳ�] '������õ'�� ���� '��������հ�' ���� ���� �ݾ��� �Է����ּ���. ***");
            System.out.println("\t�� ������õ ������� �հ� : " + originTotalPrice);
            System.out.print("\t�� ���� ������ �� [���й�ȣ,�̸�,�ݾ�] �Է�(�����̽��� ����) : ");
            String input = br.readLine();
            StringTokenizer tokenizer = new StringTokenizer(input, " ");

            if (tokenizer.countTokens() != 3) {
                System.out.println("\t[!] �Է��� �׸��� ������ ���� �ʽ��ϴ�.");
                return;
            }

            int r_checkNumber = Integer.parseInt(tokenizer.nextToken());
            String r_name = tokenizer.nextToken();
            int r_price = Integer.parseInt(tokenizer.nextToken());

            // ������ ���
            double calculateDiscount = 100 - (((double) r_price / originTotalPrice) * 100);

            // �̹� �����ϴ� ���й�ȣ���� Ȯ��
            boolean isDuplicate = false;
            for (MasterRc existingRc : masterProductList) {
                if (r_checkNumber == existingRc.getR_checkNumber()) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println("\t[!] �̹� ���й�ȣ�� �����մϴ�.");
                return;
            } else {
                System.out.print("\t�� �����Ͻðڽ��ϱ�?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y') {
                    // MasterRc ��ü ���� �� ����Ʈ�� �߰�
                    MasterRc masterRc = new MasterRc(r_checkNumber, ProductType.RCMND, r_name, calTotalcalorie, r_price, selectedProducts, calculateDiscount, calCount, Saleflag);
                    masterProductList.add(masterRc);
                    System.out.println("\t�� ����Ǿ����ϴ�. ��");
                } else {
                    System.out.println("\t[!] �ùٸ� ���� �Է��Ͻÿ�.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
        }

    }

    @Override
    public void ad_modify() throws IOException {
        List<MasterRc> masterProductList = CacheData.masterRcList;
        int modifyNum = 9;

        // ����ȭ �ּ� 231018
//            FileMg f = new FileMg();

        System.out.println("\n\t[ 3. �������� ���� ]");
        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
        int pointNumber = Integer.parseInt(br.readLine());
        boolean found = false;

        for (MasterRc masterRc : masterProductList) {
            if (pointNumber == masterRc.getR_checkNumber()) {
                found = true;

                System.out.println("\t=============================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "����", "�Ǹſ���", "���(" + materialDetailCount + ")");
                
                // �Ǹ����� ���
                String saleText = "";
                saleText = masterRc.getSaleflag() ? SaleTrue : SaleFalse;

                // ���� ����ȭ
                int calTotalMin = calculateMinCount_01(masterRc.r_details);
                masterRc.setR_count(calTotalMin);

                System.out.printf("\t|| %5d || %8s || %5d || %5d || %5d || %5s ",
                        masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie()
                        , masterRc.getR_price(), masterRc.getR_count(), saleText);

                // ����� ���
                String materialDetail = "";
                for (int i = 0; i < masterRc.getR_details().length; i++) {
                    for (Product p1 : CacheData.allProductList) {
                        if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                            if (i != 0){
                                materialDetail += ", ";
                            }
                            String materialItem = p1.getP_name();
                            materialDetail += materialItem;
                        }
                    }
                }
                System.out.printf("|| %-12s\n", materialDetail);
                System.out.println("\t=============================================================================================================");

                System.out.println("\n\t[ ������ ���� ���� ]-----------------------------------------------------------------------");
                System.out.println("\t1. ���й�ȣ 2. �̸� 3. �ݾ� 4. �Ǹſ���");
                System.out.println("\t----------------------------------------------------------------------------------------");
                System.out.println();

                while (true) {
                    System.out.print("\t�� ������ ������ ���� �Է� (0: ���� �Ϸ�) : ");
                    int choice;

                    try {
                        choice = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("\t[!] �ùٸ� ������ �Է��ϼ���.");
                        return;
                    }

                    if (choice == 0) {
//                            f.list2FileOut();
                        System.out.println("\t�� �����Ǿ����ϴ�. ��");
                        break;
                    } else if (choice < 1 || choice > modifyNum) {
                        System.out.println("\t[!] �߸��� �����Դϴ�. �ٽ� �����ϼ���.");
                    } else {
                        if (choice == 4) {
                            System.out.println("\t*** [�Ǹſ��� �ȳ�] 1: �Ǹ� O, 2: �Ǹ� X");
                        }
                        System.out.print("\t�� ���ο� �� �Է� : ");
                        String newValue = br.readLine();
                        try {
                            switch (choice) {
                                case 1:
                                    int newCheckNumber = Integer.parseInt(newValue);
                                    masterRc.setR_checkNumber(newCheckNumber);
                                    break;
                                case 2:
                                    masterRc.setR_name(newValue);
                                    break;
                                case 5:
                                    int newCount = Integer.parseInt(newValue);
                                    masterRc.setR_count(newCount);
                                    break;
                                case 4:
                                    int newSale = Integer.parseInt(newValue);
                                    boolean p_saleflag = false;
                                    p_saleflag = newSale == 1 ? true : false;
                                    masterRc.setSaleflag(p_saleflag);
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\t[!] �ùٸ� ���� �Է��ϼ���.");
                        }
                    }
                }
            }
        }

        if (!found) {
            System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
        }
    }


    @Override
    public void ad_delete() throws IOException{
        try {
            // ������ȭ ���� ������ �ҷ�����
            List<MasterRc> masterProductList = CacheData.masterRcList;
            // ����ȭ �ּ� 231018
            //FileMg f = new FileMg();

            System.out.println("\n\t[ 4. �������� ���� ]");
            System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
            int pointNumber = Integer.parseInt(br.readLine());
            boolean found = false;

            int deleteIndex = -1; // ������ �������� �ε��� �ʱ�ȭ

            for (MasterRc masterRc : masterProductList) {
                if (pointNumber == masterRc.getR_checkNumber()) {
                    found = true;

                    System.out.println("\t=============================================================================================================");
                    System.out.printf("\t|| %5s || %-9s || %5s || %5s || %5s || %5s || %5s ||\n",
                            "���й�ȣ", "�̸�", "Į�θ�", "�ݾ�", "����", "�Ǹſ���", "���(" + materialDetailCount + ")");
                    System.out.println("\t=============================================================================================================");

                    // �Ǹ����� ���
                    String saleText = "";
                    saleText = masterRc.getSaleflag() ? SaleTrue : SaleFalse;

                    // ���� ����ȭ
                    int calTotalMin = calculateMinCount_01(masterRc.r_details);
                    masterRc.setR_count(calTotalMin);

                    System.out.printf("\t|| %5d || %8s || %5d || %5d || %5d || %5s ",
                            masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie()
                            , masterRc.getR_price(), masterRc.getR_count(), saleText);

                    // ����� ���
                    String materialDetail = "";
                    for (int i = 0; i < masterRc.getR_details().length; i++) {
                        for (Product p1 : CacheData.allProductList) {
                            if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                                if (i != 0){
                                    materialDetail += ", ";
                                }
                                String materialItem = p1.getP_name();
                                materialDetail += materialItem;
                            }
                        }
                    }
                    System.out.printf("|| %-12s\n", materialDetail);
                    System.out.println("\t=============================================================================================================");

                    System.out.print("\t�����Ͻðڽ��ϱ�?(Y/N) : ");
                    char x = br.readLine().charAt(0);
                    if (x == 'Y' || x == 'y') {
                        deleteIndex = pointNumber-1; // ���� ��� ����� �ε��� ����
                        masterProductList.remove(deleteIndex);
                        System.out.println("\t�� �����Ǿ����ϴ�. ��");
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

        } catch (NumberFormatException e) {
            System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
        }
    }

    public void exit()
    {
        System.out.println("\n\t�� ������ �޴��� ���ư��ϴ�. ��");
        KioskMg.masterrcflag = false;
    }
}