import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class KioskMg
{
    public static final int E_STOCKMG = 1; // 재고 관리
    public static final int E_INGMG = 2; // 재료 관리
    public static final int E_RECMG = 3; // 사장추천 관리
    public static final int E_SALESMG = 4; // 매출 관리
    public static final int E_MEMBERMG = 5; // 회원 관리
    public static final int E_KIOSKSTART = 6; // 판매 시작(사용자 화면으로 이동)
    public static final int E_END = 7; // 종료

    private static BufferedReader br;		         //-- 사용자가 입력시 활용
    private static Integer sel;				         //-- 선택 값





    MemberMg mm = new MemberMg();
    public static boolean memflag;
    public static boolean salesflag;
    public static boolean stockflag;
    public static boolean ingredientflag;
    public static boolean masterrcflag;
    static
    {
        //BufferedReader 객체 생성
        br = new BufferedReader(new InputStreamReader(System.in));

        // 사용자 입력값 초기화
        sel = 1;

    }


    public static void adMenuDisp()
    {
        System.out.println("\n\t[ 키오스크 관리 메뉴 선택 ]=============");
        System.out.println("\t1. 재고 관리");
        System.out.println("\t2. 재료 관리");
        System.out.println("\t3. 사장추천 관리");
        System.out.println("\t4. 매출 관리");
        System.out.println("\t5. 회원 관리");
        System.out.println("\t6. 판매 시작(사용자 화면으로 이동)");
        System.out.println("\t7. 종료");
        System.out.println("\t====================================");
        System.out.print("\t▶ 메뉴 선택(1~7) : ");
    }

    // 메뉴 선택 메소드
    public static void adMenuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    // 메뉴 실행에 따른 기능 호출 메소드
    public static void adMenuRun() throws IOException, ClassNotFoundException {

        MemberMg mm = new MemberMg();
        SalesMg sm = new SalesMg();
        IngredientMg im = new IngredientMg();
        StockMg stm = new StockMg();
        MasterRc masterRc = new MasterRc();


        if (sel==E_STOCKMG){        // 1. 재고 관리
            stockflag = true;
            while(stockflag)
            {
                stm.menuDisp();
                stm.menuSelect();
                stm.menuRun();
            }

        }
        else if (sel==E_INGMG){     // 2. 재료 관리
            ingredientflag = true;
            while(ingredientflag)
            {
                im.menuDisp();
                im.menuSelect();
                im.menuRun();
            }

        }
        else if (sel==E_RECMG){     // 3. 사장추천 관리
            masterrcflag = true;
            while(masterrcflag)
            {
                masterRc.menuDisp();
                masterRc.menuSelect();
                masterRc.menuRun();
            }

        }
        else if (sel==E_SALESMG){   // 4. 매출 관리
            salesflag = true;
            while(salesflag)
            {
                sm.menuDisp();
                sm.menuSelect();
                sm.menuRun();
            }
        }
        else if (sel==E_MEMBERMG){  // 5. 회원관리
            memflag = true;
            while(memflag)
            {
                mm.menuDisp();
                mm.menuSelect();
                mm.menuRun();
            }
        }
        else if (sel==E_KIOSKSTART) // 6. 판매시작(사용자 화면으로 이동)
        {
            System.out.println("\n\n\t====[[[[[ 사용자 화면 ]]]]]====");
            ProductService productService = new ProductService(); // ProductService 객체 생성
            Kiosk ks = new Kiosk(productService);
            ks.kioskStart();
        }
        else if (sel == E_END)      // 7. 프로그램 종료
        {
            exitCart();
        }
        else
            System.out.print("\t[!] 메뉴 선택 오류");
    }
    public static void exitCart()
    {
        try
        {
            // 객체 직렬화
            FileMg f = new FileMg();
            f.memberFileOut();
            f.receiptFileOut();
            f.list1FileOut();
            f.list2FileOut();
        } catch (IOException e)
        {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

}