import java.io.IOException;


/*
 메인 ----------------------------------------------------------------
*/

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // 객체 역직렬화
        FileMg f = new FileMg();
        MemberMg.hm = f.memberFileIn();
        SalesMg.receipts = f.receiptFileIn();
        CacheData.allProductList = f.list1FileIn();
        CacheData.masterRcList = f.list2FileIn();

        CacheData cacheData = new CacheData();
        cacheData.testData();

        // 관리자 로그인 폼
        ad_login al = new ad_login();
        al.adLogin();

    }
}
