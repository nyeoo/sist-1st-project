import java.io.IOException;


/*
 ���� ----------------------------------------------------------------
*/

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // ��ü ������ȭ
        // ����ȭ �ּ� 231018
//        FileMg f = new FileMg();
//        MemberMg.hm = f.memberFileIn();
//        SalesMg.receipts = f.receiptFileIn();
//        CacheData.allProductList = f.list1FileIn();
//        CacheData.masterProductList = f.list2FileIn();
//        CacheData.list3 = f.list3FileIn();
//        CacheData.list4 = f.list4FileIn();

        CacheData cacheData = new CacheData();
        cacheData.testData();

        // ������ �α��� ��
        ad_login al = new ad_login();
        al.adLogin();
//
//        // ��ü ���� ��������
       /* f.memberFileOut();
        f.receiptFileOut();*/

//        System.out.println("\n\n\t====[[[[[ ����� ȭ�� ]]]]]====");
//
//        // ȯ���λ� //TODO �ٹж� �ֱ�
//        Emp emp = new Emp(":)");
//        emp.empWelcome();
//
        // ����� ��
        /*ProductService productService = new ProductService(); // ProductService ��ü ����
        Kiosk ks = new Kiosk(productService);
        ks.kioskStart();*/


    }
}
