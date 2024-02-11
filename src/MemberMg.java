import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class MemberMg implements Impl_admin
{
    public final int E_PRINT = 1;                    //-- ���(ȸ��) �߰�
    public final int E_ADD = 2;
    public final int E_MOD = 3;
    public final int E_DEL = 4;
    public final int E_EXIT = 5;

    public static HashMap<String, Member> hm; //-- �ڷᱸ�� ����
    private static BufferedReader br;		         //-- ����ڰ� �Է½� Ȱ��
    private static Integer sel;				         //-- ���� ��


    static
    {
        //hashmap �ڷᱸ�� ����
        hm = new HashMap<String, Member>();

        //BufferedReader ��ü ����
        br = new BufferedReader(new InputStreamReader(System.in));

        // ����� �Է°� �ʱ�ȭ
        sel = 1;
    }

    // �޴� ��� �޼ҵ�
    public void menuDisp()
    {
        System.out.println("\n\t[ ȸ������ �޴� ���� ]===========");
        System.out.println("\t1. ȸ�� ��� ���");
        System.out.println("\t2. ȸ�� ���");
        System.out.println("\t3. ȸ�� ���� ����");
        System.out.println("\t4. ȸ�� ����");
        System.out.println("\t5. ������ �޴� ȭ������ �̵�");
        System.out.println("\t=================================");
        System.out.print("\t�� �޴� ����(1~5) : ");
    }

    // �޴� ���� �޼ҵ�
    public void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    // �޴� ���࿡ ���� ��� ȣ�� �޼ҵ�
    public void menuRun() throws IOException
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

    // ���(ȸ��) ��� �޼ҵ�
    @Override
    public void ad_print() {
        System.out.println();
        System.out.println("\t[ ȸ����� ]------------------------------");
        System.out.println("\tID(��ȭ��ȣ)    password    ������ ����Ʈ");
        Iterator it = hm.keySet().iterator();            // key �������� �̷���� Iterator ����
        while (it.hasNext())                             // Iterator �ȿ� ���� ��Ұ� �ִ���? ������ true ��ȯ
        {
            String key = (String)it.next();              // next()�� �ش� ��ҿ� ����
            System.out.println("\t" + hm.get(key));      // key ���� ������ value ��(Member ��ü)�� ��ȯ

        }
        System.out.println("\t-----------------------------------------");
        System.out.println();
    }


    // ���(ȸ��) �߰� �޼ҵ�
    @Override
    public void ad_add(){

            try
            {
                System.out.println();
                System.out.println("\t�� ȸ�� ������ �����մϴ�. ��");
                System.out.println("\t�� ������ ȸ���� ������ �Է����ּ���. ��");

                while (true)
                {
                    boolean idCheck = false;                                     // ���� �� �ʱ�ȭ
                    System.out.print("\t�� ID �Է�(��ȭ��ȣ) : ");
                    String id = br.readLine();                                   // �����ڰ� ID�� �Է�
                    char[] arr1 = id.toCharArray();                              // �����ڰ� �Է��� id�� char �迭 arr1�� �ɰ��� ���
                    for (int i=3; i<id.length(); i++)                            // 010 �޺κ��� �ε��� 3~10
                    {
                        if ('0'<=arr1[i] && arr1[i]<='9')                        // 010 �޺κ��� 0���� 9������ �������·� �Է��ߴ��� Ȯ��
                            idCheck = true;
                    }

                    if (hm.containsKey(id))
                        System.out.println("\n\t[!] �̹� �����ϴ� ID�Դϴ�.");
                    else if (id.length()==11 && idCheck && id.substring(0,3).equals("010"))   // �� 11�ڸ��� �̰�, 010���� �����ϰ�, 010 �޺κ��� �������·� �Է��� ���
                    {
                        String memPw;
                        while(true) {
                            boolean pwCheck = false;                              // ���� �� �ʱ�ȭ
                            System.out.print("\t�� Password �Է�(���� 4�ڸ�) : ");
                            memPw = br.readLine();                                // �����ڰ� Password�� �Է�
                            char[] arr2 = memPw.toCharArray();                    // �����ڰ� �Է��� Password�� char �迭 arr2�� �ɰ��� ���
                            for (int i = 0; i <memPw.length(); i++)               // �ε��� 0~3
                                if ('0' <= arr2[i] && arr2[i] <= '9')             // 0���� 9������ �������·� �Է��ߴ��� Ȯ��
                                    pwCheck = true;
                            if (memPw.length() == 4 && pwCheck)                  // �� 4�ڸ��̰ų�, �������·� �Է��� ���
                                break;
                            else
                                System.out.println("\n\t[!] Password�� ���� 4�ڸ��� �Է°����մϴ�. �ٽ� �Է����ּ���.");
                        }
                        hm.put(id,new Member(id,memPw,1000));
                        System.out.println("\n\t�� ȸ������� �Ϸ�Ǿ����ϴ�. ��");
                        break;
                    }
                    else
                        System.out.println("\n\t[!] ID�� ��ȭ��ȣ�� �Է°����մϴ�. �ٽ� �Է����ּ���.");
                }
            }
            catch(IOException e)
            {
                System.out.println(e.toString());
            }
    }


    // ���(ȸ��) ���� �޼ҵ�
    @Override
    public void ad_modify()
    {
        try
        {
            System.out.println();
            System.out.println("\t�� ������ ������ ȸ���� ID�� �Է��ϼ���. ��");
            System.out.print("\t�� ID : ");
            String id = br.readLine();
            if (!hm.containsKey(id))                           // �Է��� ID�� ���� ȸ���� �����ϴ��� Ȯ��
            {
                System.out.println("\t[!] �Է��Ͻ� ȸ�� ID�� �������� �ʽ��ϴ�.");
            }
            else
            {
                // ȸ�� ���� ����
                System.out.println("\t�� ������ password�� �Է��ϼ���. ��");
                System.out.print("\t�� password : ");
                String memPw = br.readLine();
                System.out.println("\t�� ������ point�� �Է��ϼ���. ��");
                System.out.print("\t�� point : ");
                int memPoint = Integer.parseInt(br.readLine());
                hm.put(id,new Member(id,memPw,memPoint));     // �Է��� ������ �־ value �� �ٲ��ֱ�
                System.out.println("\t�� ȸ�� ���� ������ �Ϸ�Ǿ����ϴ�. ��");
            }
        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
    }


    // ���(ȸ��) ���� �޼ҵ�
    @Override
    public void ad_delete()
    {

        try
        {
            System.out.println("\n\t�� ������ ȸ���� ID�� �Է��ϼ���. ��");
            System.out.print("\t�� ID : ");
            String id = br.readLine();
            if (!hm.containsKey(id))                           // �Է��� ID�� ���� ȸ���� �����ϴ��� Ȯ��
            {
                System.out.println("\t[!] �Է��Ͻ� ȸ�� ID�� �������� �ʽ��ϴ�.");
            }
            else
            {
                hm.remove(id);
                System.out.println("\t�� ȸ�� ������ ���������� �Ϸ�Ǿ����ϴ�. ��");
            }

        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
    }

    public void exit()
    {
        System.out.println("\n\t�� ������ �޴��� ���ư��ϴ�. ��");
        KioskMg.memflag = false;
    }
}

