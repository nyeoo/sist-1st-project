import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

enum ProductType {              // ��ǰ ������(Enum)
    /*
        Enum �������: ������ �� ��������, ������(enum Ÿ���� ���� ���), �ڵ� �ߺ� ����, Ư�� �� ���� ǥ��
     */
    RCMND(1, 1, "������õ"),
    MY_SALAD(2, 1, "������ ������"),
    DRINK(3, 1, "����"),
    SIDE(4, 1, "���̵�"),

    S_BASE(5, 2, "���̽�"),
    S_MAIN(6, 2, "��������"),
    S_SIDE(7, 2, "���̵�����"),
    S_SOURCE(8, 2, "�ҽ�"),

    CANCEL(-1, -1, "���");

    private int index;
    private int depth;
    private String name;

    ProductType(int index, int depth, String name) {
        this.index = index;
        this.depth = depth;
        this.name = name;
    }


    // getter
    public int getIndex() {
        return index;
    }

    public int getDepth() {
        return depth;
    }

    public String getName() {
        return name;
    }
}



public class Product implements Serializable, Impl_admin {

    private static final long serialVersionUID = 4570189582182369883L;
    //���丮 ������ ��ắ�� ����
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int p_checkNumber;
    private int p_material;
    private String p_name;
    private String p_unit;
    private int p_count;
    private int p_calorie;
    private int p_stock;
    private int p_price;
    private ProductType type;
    private int p_limitCount;
    private boolean Saleflag;


    public Product(int p_checkNumber, ProductType type,int p_material, String p_name, String p_unit, int p_count, int p_calorie, int p_stock, int p_price, boolean Saleflag) {
        this.p_checkNumber = p_checkNumber;
        this.type = type;
        this.p_material = p_material;
        this.p_name = p_name;
        this.p_unit = p_unit;
        this.p_count = p_count;
        this.p_calorie = p_calorie;
        this.p_stock = p_stock;
        this.p_price = p_price;
        this.Saleflag = Saleflag;
    }

    // ������
    public Product(int p_checkNumber, int p_material, String p_name, String p_unit, int p_count, int p_calorie, int p_stock, int p_price) {
        this.p_checkNumber = p_checkNumber;
        this.p_material = p_material;
        this.p_name = p_name;
        this.p_unit = p_unit;
        this.p_count = p_count;
        this.p_calorie = p_calorie;
        this.p_stock = p_stock;
        this.p_price = p_price;
    }

    // ������
    public Product(){ this(0, 0, "", "", 0, 0, 0, 0); }

    //getter setter
    public ProductType getType() { return type;}
    public int getP_checkNumber() { return p_checkNumber; }
    public void setP_checkNumber(int p_checkNumber) { this.p_checkNumber = p_checkNumber; }
    public int getP_material() { return p_material; }
    public void setP_material(int p_material) { this.p_material = p_material; }
    public String getP_name() { return p_name; }
    public void setP_name(String p_name) { this.p_name = p_name; }
    public String getP_unit() { return p_unit; }
    public void setP_unit(String p_unit) { this.p_unit = p_unit; }
    public int getP_count() { return p_count; }
    public void setP_count(int p_count) { this.p_count = p_count; }
    public int getP_calorie() { return p_calorie; }
    public void setP_calorie(int p_calorie) { this.p_calorie = p_calorie; }
    public int getP_stock() { return p_stock; }
    public void setP_stock(int p_stock) { this.p_stock = p_stock; }
    public int getP_price() { return p_price; }
    public void setP_price(int p_price) { this.p_price = p_price; }
    public int getP_limitCount() { return p_limitCount; }
    public void setP_limitCount(int p_limitCount) { this.p_limitCount = p_limitCount; }
    public boolean getSaleflag() { return Saleflag; }
    public void setSaleflag(boolean Saleflag) { this.Saleflag = Saleflag; }

    @Override
    public String toString() {
        return "Product{" +
//                "appDir='" + appDir + '\'' +
                ", br=" + br +
                ", p_checkNumber=" + p_checkNumber +
                ", p_material=" + p_material +
                ", p_name='" + p_name + '\'' +
                ", p_unit='" + p_unit + '\'' +
                ", p_count=" + p_count +
                ", p_calorie=" + p_calorie +
                ", p_stock=" + p_stock +
                ", p_price=" + p_price +
                ", type=" + type +
                ", p_limitCount=" + p_limitCount +
                '}';
    }

    @Override
    public void ad_print() throws IOException, ClassNotFoundException {

        List<Product> product = CacheData.allProductList;



        System.out.println("\n\t[ 1. ������� ��� ]===============");
        System.out.println("\t1. ��ü ������� ���  \n\t2. ���� ������� ��� ");
        System.out.println("\t==============================");

        int seletCheckNnumber=0;
        int materialNumber=0;
        while (true)
        {
            try{
                System.out.print("\t�� ������ �׸��� ���� �Է� : ");
                seletCheckNnumber = Integer.parseInt(br.readLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                continue;
            }

            switch (seletCheckNnumber)
            {
                case 1 :
                    System.out.println("\n\t[ 1. ��ü ������� ��� ]");
                    System.out.println("\t==========================================================================================================");
                    System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||%5s ||\n",
                            "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                    System.out.println("\t==========================================================================================================");

                    // Iterator Ȱ���Ͽ� ���
                    Iterator<Product> itList = product.iterator();
                    while (itList.hasNext())
                    {
                        Product itS = itList.next();
                        String saleText = "";
                        saleText = itS.getSaleflag()? "�Ǹ� O": "�Ǹ� X";
                        System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                itS.getP_stock(), itS.getP_price(), saleText);
                    }
                    System.out.println("\t==========================================================================================================");
                    System.out.println();
                    return;

                case 2 :
                    System.out.println("\n\t[ 2. ���� ������� ��� ]");
                    System.out.println("\t==========================================================================================================");
                    System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n",
                            "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�");
                    System.out.println("\t==========================================================================================================");
                    System.out.println("\t*** [�з���ȣ �ȳ�] 1: ������õ, 3:����, 4:���̵�, 5:���̽�, 6:��������, 7:���̵�����, 8:�ҽ�");
                    while (true) {
                        try {
                            System.out.print("\t�� �з���ȣ �Է� : ");
                            materialNumber = Integer.parseInt(br.readLine());

                            boolean found = false; // found ���� �ʱ�ȭ

                            for (int i = 0; i < product.size(); i++) {
                                if (materialNumber == product.get(i).getP_material()) {
                                    found = true;
                                    break; // �ش� �з���ȣ�� ã�����Ƿ� ���� ����
                                }
                            }

                            if (found) {
                                break; // �ùٸ� �з���ȣ�� ã�����Ƿ� ���� ����
                            } else {
                                System.out.println("\t�� ���й�ȣ�� �������� �ʽ��ϴ�.");
                            }
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                        }

                    }


                    // Iterator Ȱ���Ͽ� ������ �з���ȣ�� �ش��ϴ� ������� ���
                    Iterator<Product> itList1 = product.iterator();
                    while (itList1.hasNext())
                    {
                        Product itS = itList1.next();
                        if (itS.getP_material() == materialNumber)
                        {
                            String saleText = "";
                            saleText = itS.getSaleflag()? "�Ǹ� O": "�Ǹ� X";
                            System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||%5s ||\n", itS.getP_checkNumber(),
                                    itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                    itS.getP_stock(), itS.getP_price(), saleText);
                        }
                    }
                    System.out.println("\t==========================================================================================================");
                    System.out.println();
                    return;

                default:
                    System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                    continue;

            }

        }

    }


    @Override
    public void ad_add() throws IOException,ClassNotFoundException {

        //�ڷᱸ�� ����
        List<Product> list1 = CacheData.allProductList;

        boolean shouldExit = false;
        System.out.println("\n\t[ 2. �ű���� ��� ]");
        System.out.println("\t==========================================================================================================");
        System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�");
        System.out.println("\t==========================================================================================================");
		System.out.println("\t*** [�з���ȣ �ȳ�] 1: ������õ, 3:����, 4:���̵�, 5:���̽�, 6:��������, 7:���̵�����, 8:�ҽ�");

        System.out.print("\t�� ������� �Է� (�����̽��� ����): ");
        String input = br.readLine();

        // ��ũ�������� �����̽��� �����ֱ�
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        if (tokenizer.countTokens() != 8) {
            System.out.println("\t[!] �Է��� �׸��� ������ ���� �ʽ��ϴ�.");
            return;
        }

        try{

            int p_checkNumber = Integer.parseInt(tokenizer.nextToken());
            int p_material = Integer.parseInt(tokenizer.nextToken());

            ProductTypeChange productTypeChange = new ProductTypeChange();
            ProductType productType= productTypeChange.ProductTypeChange(p_material);

            String p_name = tokenizer.nextToken();
            String p_unit = tokenizer.nextToken();
            int p_count = Integer.parseInt(tokenizer.nextToken());
            int p_calorie = Integer.parseInt(tokenizer.nextToken());
            int p_stock = Integer.parseInt(tokenizer.nextToken());
            int p_price = Integer.parseInt(tokenizer.nextToken());

            List<Product> existingList;
            existingList = CacheData.allProductList;

            boolean m = false;
            for (int i = 0; i < existingList.size(); i++){
                if (p_checkNumber == existingList.get(i).getP_checkNumber()){
                    m = true;
                    break;
                }
            }
            if (m){
                System.out.println("\t[!] �̹� ���й�ȣ�� �����մϴ�.");
                return;
            }
            else {
                System.out.print("\t�� �����Ͻðڽ��ϱ�?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y') {
                    Product product = new Product(p_checkNumber, productType, p_material, p_name, p_unit, p_count, p_calorie, p_stock, p_price, Saleflag);
                    list1.add(product);
                    // ����ȭ �ּ� 231018
//                    FileMg f = new FileMg();
//                    f.list1FileOut();
//                    f.list3FileOut();

                    System.out.println("\t�� ����Ǿ����ϴ�. ��");
                } else {
                    System.out.println("\t[!] �ùٸ� ���� �Է��Ͻÿ�.");
                    return;
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("\t[!] �߸��� ���� �����Դϴ�. �ٽ� �Է��ϼ���.");
        }
        KioskMg.productflag = false;
    }



    @Override
    public void ad_modify() throws IOException, ClassNotFoundException {
        List<Product> product = CacheData.allProductList;
        List<Product> list3 = CacheData.list3;

        System.out.println("\n\t[ 3. ������� ���� ]");
        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
        int pointNumber;

        try {
            pointNumber = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("\t[!] �ùٸ� ���й�ȣ�� �Է��ϼ���.");
            return;
        }

        boolean found = false;

        for (int i = 0; i < product.size(); i++) {
            if (pointNumber == product.get(i).getP_checkNumber()) {
                found = true;
                Product itS = product.get(i);

                String saleText = "";
                saleText = itS.getSaleflag()? "�Ǹ� O": "�Ǹ� X";
				System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price(), saleText);
                System.out.println("\t==========================================================================================================");

                System.out.println("\n\t[ ������ ���� ���� ]-----------------------------------------------------");
                System.out.println("\t1.���й�ȣ 2. �з���ȣ 3. �̸� 4. ���� 5. ���� 6. Į�θ� 7. ������� 8. �ݾ�");
                System.out.println("\t----------------------------------------------------------------------");
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
                        // ���� �Ϸ� ��, ����� ������ ����
                        // ����ȭ �ּ� 231018
//                        FileMg f = new FileMg();
//                        f.list1FileOut();
//                        f.list3FileOut();
                        System.out.println("\t�� �����Ǿ����ϴ�. ��");
                        return; // �޼ҵ带 ���������ϴ�.
                    } else if (choice < 1 || choice > 8) {
                        System.out.println("\t[!] �߸��� �����Դϴ�. �ٽ� �����ϼ���.");
                    } else {
                        System.out.print("\t�� ���ο� �� �Է� : ");
                        String newValue = br.readLine();
                        try {
                            switch (choice) {
                                case 1:
                                    int newCheckNumber = Integer.parseInt(newValue);
                                    itS.setP_checkNumber(newCheckNumber);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_checkNumber(newCheckNumber);
                                        }
                                    }
                                    break;
                                case 2:
                                    int newMaterial = Integer.parseInt(newValue);
                                    itS.setP_material(newMaterial);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_material(newMaterial);
                                        }
                                    }
                                    break;
                                case 3:
                                    itS.setP_name(newValue);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_name(newValue);
                                        }
                                    }
                                    break;
                                case 4:
                                    itS.setP_unit(newValue);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_unit(newValue);
                                        }
                                    }
                                    break;
                                case 5:
                                    int newCount = Integer.parseInt(newValue);
                                    itS.setP_count(newCount);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_count(newCount);
                                        }
                                    }
                                    break;
                                case 6:
                                    int newCalorie = Integer.parseInt(newValue);
                                    itS.setP_calorie(newCalorie);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_calorie(newCalorie);
                                        }
                                    }
                                    break;
                                case 7:
                                    int newStock = Integer.parseInt(newValue);
                                    itS.setP_stock(newStock);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_stock(newStock);
                                        }
                                    }
                                    break;
                                case 8:
                                    int newPrice = Integer.parseInt(newValue);
                                    itS.setP_price(newPrice);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_price(newPrice);
                                        }
                                    }
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

        KioskMg.productflag = false;
    }



    @Override
    public void ad_delete() throws IOException, ClassNotFoundException {
        List<Product> product = CacheData.allProductList;
        List<Product> list3 = CacheData.list3;

        System.out.println("\n\t[ 4. ������� ���� ]");
        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է� (�ڷΰ���:0) : ");
        int pointNumber;


        try {
            pointNumber = Integer.parseInt(br.readLine());
            if(pointNumber==0)
            {
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] �ùٸ� ���й�ȣ�� �Է��ϼ���.");
            return;
        }

        boolean found = false;

        int deleteIndex = -1; // ������ �������� �ε��� �ʱ�ȭ

        for (int i = 0; i < product.size(); i++) {
            if (pointNumber == product.get(i).getP_checkNumber()) {
                found = true;
                Iterator<Product> itList;
                itList = product.iterator();
                Product itS = product.get(i);

                String saleText = "";
                saleText = itS.getSaleflag()? "�Ǹ� O": "�Ǹ� X";
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price(), saleText);
				System.out.println("\t==========================================================================================================");
                System.out.println();
                System.out.println();
                System.out.print("\t�� �����Ͻðڽ��ϱ�? (Y/N) : ");
                char x = br.readLine().charAt(0);

                if (x == 'Y' || x == 'y') {
                    deleteIndex = i; // ���� ��� ��ǰ�� �ε��� ����
                    for (Iterator<Product> iterator = list3.iterator(); iterator.hasNext(); ) {
                        Product p = iterator.next();
                        if (p.getP_checkNumber() == pointNumber) {
                            iterator.remove(); // list3���� �����ϰ� ����
                        }
                    }
                    break;
                } else if (x == 'N' || x == 'n') {
                    return; // 'N'�� �Է��ϸ� �޼ҵ带 �������ɴϴ�.
                } else {
                    System.out.println("\t[!] �ùٸ� ���� �Է��Ͻÿ�.");
                    return;
                }
            }
        }

        if (!found) {
            System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
            return; // ��ġ���� ������ ���� �۾��� ���� �ʰ� ����
        }

        // ���� ��� ��ᰡ ������ ��쿡�� ���� ����
        if (deleteIndex != -1) {
            product.remove(deleteIndex);

            // ����ȭ �ּ� 231018
//            FileMg f = new FileMg();
//            f.list1FileOut();
//            f.list3FileOut();
            System.out.println("\t�� �����Ǿ����ϴ�. ��");
        }

        KioskMg.productflag = false;
    }

}