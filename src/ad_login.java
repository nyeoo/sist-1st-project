import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class ad_login {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String adId, adPw;                         // ������ ID�� Password ���� ����
    public static boolean kioskFlag;
    public void adLogin() throws IOException, ClassNotFoundException   // ���߿� main���� �������� ��������� trycatch�� �����ϱ�
    {
        System.out.println();
        System.out.println("\t������ �α����� �����մϴ�.");

        while(true)
        {

            System.out.println("\n\t===============================");
            System.out.print("\t�� ������ ID �Է� : ");
            adId = br.readLine();
            System.out.print("\t�� ������ ��й�ȣ �Է� : ");
            adPw = br.readLine();
            System.out.println("\t===============================");
			System.out.println();

            if (adId.equals("admin") && adPw.equals("1234"))
            {
                System.out.println("\t�� ������ �α��� �Ǿ����ϴ�. ��");
                System.out.println("\t�� ������ ��带 �����մϴ�. ��");
                break;
            }
            else
            {
                System.out.println("\t[!] �߸��� ID �Ǵ� Password �Դϴ�. �ٽ� �Է����ּ���.");
                System.out.println();
            }
        }
        while(true)
        {
            KioskMg km = new KioskMg();
            km.adMenuDisp();
            km.adMenuSelect();
            km.adMenuRun();
        }



    }
}
