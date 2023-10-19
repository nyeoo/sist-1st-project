import java.io.*;
import java.util.*;

class Menus
{
    public static final int E_CP = 1; // ���� ���
    public static final int E_REC = 2; // ������ ���
    public static final int E_SC = 3; // ������ȸ!
    public static final int E_EX = 4; // ����
}

public class SalesMg
{
    static ArrayList<Receipt> receipts = new ArrayList<>();
    private static BufferedReader br;
    private static Integer sel;

    static
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        sel = 1;
    }

    public static void menuDisp()
    {
        System.out.println("\n\t[ ���� ���� �ý��� ]=============");
        System.out.println("\t1. ���� ���");
        System.out.println("\t2. ������ ���");
        System.out.println("\t3. ���� ��ȸ");
        System.out.println("\t4. ������ �޴� ȭ������ �̵�");
        System.out.println("\t=================================");
        System.out.print("\t�� �޴� ���� : ");
   }

    public static void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    public static void menuRun() throws IOException
    {
        switch (sel)
        {
            case Menus.E_CP : cancelPayment(); break;
            case Menus.E_REC : receipt(); break;
            case Menus.E_SC : salesCheck(); break;
            case Menus.E_EX : exit(); break;
            default:
                System.out.println("\t[!] �޴� ���� ����");
        }
    }

    public static void receiptSave(){

        for (Order orders: CacheData.orderOuterList){
            String nowTime = orders.getO_nowTime();
            int year = Integer.parseInt(nowTime.substring(0,4));
            int month = Integer.parseInt(nowTime.substring(4,6));
            int day = Integer.parseInt(nowTime.substring(6,8));
            int hour = Integer.parseInt(nowTime.substring(8,10));
            String memberid = orders.getO_name();
            boolean ismemeber = orders.isMember();
            int usedpoints = orders.getUsedPoints();
            String paymentmethod = orders.getPaymentMethod();
            double totalamout = orders.getO_totPrice();
            receipts.add(new Receipt(year, month, day, hour, memberid, ismemeber, usedpoints, paymentmethod, totalamout));
        }
    }

    public static void cancelPayment() throws IOException
    {
        System.out.println("\n\t[ ���� ��� ]====================");
        System.out.print("\t�� �� �Է�: ");
        int cancelYear = Integer.parseInt(br.readLine());
        System.out.print("\t�� �� �Է�: ");
        int cancelMonth = Integer.parseInt(br.readLine());
        System.out.print("\t�� �� �Է�: ");
        int cancelDay = Integer.parseInt(br.readLine());
        System.out.println("\t================================");

        boolean foundReceipt = false;

        int receiptIndex = 1;

        System.out.println("\n\t[ ������� ����Ʈ ]");
        System.out.println("\t===========================================================================");
        System.out.printf("\t%-5s \t%5s \t%10s \t%5s \t%5s \t%5s\n","���й�ȣ","ȸ������","���̵�","����Ʈ���","��������","����ݾ�");
        System.out.println("\t===========================================================================");
        for (Receipt receipt : receipts)
        {
            // TODO ��¹�� ���
//            ==============================
//            ���й�ȣ	ȸ������	���̵�			����Ʈ���		��������	�����ݾ�
//            1			��ȸ��		ȸ���ƴ�			0			īī������		400.0
//            2			ȸ��		01012341234			0			īī������		800.0
//
//            ���й�ȣ: 1
//            ȸ�� ����: ��ȸ��
//            ȸ���� �ƴմϴ�
//            ����Ʈ ���: 0
//            ���� ����: īī������
//            ���� �ݾ�: 400.0
            if (receipt.getYear() == cancelYear && receipt.getMonth() == cancelMonth && receipt.getDay() == cancelDay)
            {
                String isMemberId = "";
                if (receipt.isMember())
                    isMemberId = receipt.getMemberId();
                else
                    isMemberId = "���̵����";

                System.out.printf("\t%-5d \t%5s \t%16s \t%5s \t%5s \t%5.1f\n",
                        receiptIndex,(receipt.isMember() ? "ȸ��" : "��ȸ��"),isMemberId,receipt.getUsedPoints(),receipt.getPaymentMethod(),receipt.getTotalAmount());
                //System.out.printf("\t%8s %8s %16s %8s %10s %16s","���й�ȣ","ȸ������","���̵�","����Ʈ���","��������","����ݾ�");

//                System.out.println("���й�ȣ: " + receiptIndex);
//                System.out.println("ȸ�� ����: " + (receipt.isMember() ? "ȸ��" : "��ȸ��"));
//                if (receipt.isMember())
//                    System.out.println("ȸ�� ���̵� : " + receipt.getMemberId());
//                else
//                    System.out.println("ȸ���� �ƴմϴ�");
//                System.out.println("����Ʈ ���: " + receipt.getUsedPoints());
//                System.out.println("���� ����: " + receipt.getPaymentMethod());
//                System.out.println("���� �ݾ�: " + receipt.getTotalAmount());
//                System.out.println();
                foundReceipt  = true;
                receiptIndex++;
            }
        }




        if (foundReceipt)
        {

            System.out.print("\t�� ���� ����� ���� ��ȣ�� �Է��ϼ���: ");
            int selectedReceiptIndex = Integer.parseInt(br.readLine());

            // ����ڰ� �Է��� ��ȣ�� �ش��ϴ� ���� ������ ������
            if (selectedReceiptIndex >= 1 && selectedReceiptIndex <= receiptIndex - 1)
            {
                Receipt selectedReceipt = null;
                int currentIndex = 1;

                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == cancelYear && receipt.getMonth() == cancelMonth && receipt.getDay() == cancelDay)
                    {
                        if (currentIndex == selectedReceiptIndex)
                        {
                            selectedReceipt = receipt;
                            break;
                        }
                        currentIndex++;
                    }
                }


                if (selectedReceipt != null)
                {
                    int returnedPoints = selectedReceipt.getUsedPoints();
                    int remainingPoints = (int)(selectedReceipt.getTotalAmount() - selectedReceipt.getUsedPoints());

                    if (selectedReceipt.isMember()==true)
                    {
                        String id = selectedReceipt.getMemberId();
                        int memPoint = MemberMg.hm.get(id).getMemPoint()+returnedPoints-(int)(remainingPoints*0.1);
                        MemberMg.hm.get(id).setMemPoint(memPoint);
                    }


                    // ���õ� ���� ������ ����
                    receipts.remove(selectedReceipt);

                    System.out.println("\n\t[ ���� ��� ���� ]");
                    System.out.println("\t==========================================================================================");
                    System.out.printf("\t%-5s \t%5s \t%10s \t%5s \t%5s \t%5s \t%5s\n","���й�ȣ","ȸ������","���̵�","����Ʈ���","��������","�����ݾ�","��ȯ�� ����Ʈ");
                    System.out.println("\t==========================================================================================");

                    String isMemberId = "";
                    if (selectedReceipt.isMember())
                        isMemberId = selectedReceipt.getMemberId();
                    else
                        isMemberId = "���̵����";

                    System.out.printf("\t%-5d \t%5s \t%16s \t%5d \t%5s \t%5.1f \t%5d\n",
                            selectedReceiptIndex,(selectedReceipt.isMember() ? "ȸ��" : "��ȸ��"),isMemberId,selectedReceipt.getUsedPoints(),selectedReceipt.getPaymentMethod(),selectedReceipt.getTotalAmount(),returnedPoints);
                    //System.out.printf("\t%8s %8s %16s %8s %10s %16s","���й�ȣ","ȸ������","���̵�","����Ʈ���","��������","�����ݾ�");

//                    System.out.println("\t- ���й�ȣ: " + selectedReceiptIndex);
//                    System.out.println("\t- ȸ�� ����: " + (selectedReceipt.isMember() ? "ȸ��" : "��ȸ��"));
//                    if (selectedReceipt.isMember())
//                        System.out.println("\t- ȸ�� ���̵� : " + selectedReceipt.getMemberId());
//                    else
//                        System.out.println("\t- ȸ���� �ƴմϴ�");
//                    System.out.println("\t- ����Ʈ ���: " + selectedReceipt.getUsedPoints());
//                    System.out.println("\t- ���� ����: " + selectedReceipt.getPaymentMethod());
//                    System.out.println("\t- ���� �ݾ�: " + selectedReceipt.getTotalAmount());
//                    System.out.println("\t- ��ȯ�� ����Ʈ: " + returnedPoints);
//                    System.out.println("\t---------------------------------------");
                    System.out.println("\t==========================================================================================");

                    System.out.println("\t�� ������ ��ҵǾ����ϴ�. ��");


                }

            }
            else
                System.out.println("\t[!] �ùٸ� ���� ��ȣ�� �Է��ϼ���.");
        }

        else
            System.out.println("\t�� �ش� ���ڿ� ���� ������ �����ϴ�. ��");
        System.out.println();
    }



    public static void receipt() throws IOException
    {
        System.out.println("\n\t[ ������ ��� ]====================");
        System.out.print("\t�� �� �Է�: ");
        int receiptYear = Integer.parseInt(br.readLine());
        System.out.print("\t�� �� �Է�: ");
        int receiptMonth = Integer.parseInt(br.readLine());
        System.out.print("\t�� �� �Է�: ");
        int receiptDay = Integer.parseInt(br.readLine());
        System.out.print("\t�� �ð��� �Է� : ");
        int receiptHour = Integer.parseInt(br.readLine());
        System.out.println("\t=================================");

        int receiptIndex = 1;

        boolean foundReceipt = false;

        // �ش� ������ ������ ���� ���
        System.out.println("\n\t[ ������ ���� ��� ]");
        System.out.println("\t=============================================================================================");
        System.out.printf("\t%-5s \t%5s \t%5s \t%5s \t%5s \t%5s\n","���й�ȣ","ȸ������","����Ʈ���","��������","�����ݾ�", "���� �����ݾ�");
        System.out.println("\t=============================================================================================");

        for (Receipt receipt : receipts)
        {
            if (receipt.getYear() == receiptYear && receipt.getMonth() == receiptMonth && receipt.getDay() == receiptDay && receipt.getHour() == receiptHour)
            {

                System.out.printf("\t%-10d \t%5s \t%5d \t%14s \t%7.1f \t%7.1f\n",
                        receiptIndex,(receipt.isMember() ? "ȸ��" : "��ȸ��"),receipt.getUsedPoints(),receipt.getPaymentMethod(),receipt.getTotalAmount(),(receipt.getTotalAmount()-receipt.getUsedPoints()));
                //System.out.printf("\t%8s %8s %16s %8s %10s %16s","���й�ȣ","ȸ������","���̵�","����Ʈ���","��������","�����ݾ�");


//                System.out.println("���й�ȣ: " + receiptIndex);
//                System.out.println("ȸ�� ����: " + (receipt.isMember() ? "ȸ��" : "��ȸ��"));
//                System.out.println("����Ʈ ���: " + receipt.getUsedPoints());
//                System.out.println("���� ����: " + receipt.getPaymentMethod());
//                System.out.println("���� �ݾ�: " + receipt.getTotalAmount());
//                System.out.println("���� ���� �ݾ�: " + (receipt.getTotalAmount()-receipt.getUsedPoints()));
//                System.out.println();
                receiptIndex++;
                foundReceipt  = true;
            }
        }

        // ����ڰ� ���й�ȣ �Է��ϸ� �� ���й�ȣ�� �´� ������ ���� ���
        if (foundReceipt)
        {
            System.out.print("\t�� ����� �������� ���й�ȣ �Է� : ");
            int selectedReceiptIndex = Integer.parseInt(br.readLine());
            System.out.println();

            receiptIndex = 1;

            for (Receipt receipt : receipts)
            {
                if (receipt.getYear() == receiptYear && receipt.getMonth() == receiptMonth && receipt.getDay() == receiptDay && receipt.getHour() == receiptHour)
                {
                    if (receiptIndex == selectedReceiptIndex)
                    {
//                        System.out.println("--------------------------------------");
//                        System.out.println("���й�ȣ: " + receiptIndex);
//                        System.out.println("ȸ�� ����: " + (receipt.isMember() ? "ȸ��" : "��ȸ��"));
//                        System.out.println("����Ʈ ���: " + receipt.getUsedPoints());
//                        System.out.println("���� ����: " + receipt.getPaymentMethod());
//                        System.out.println("���� �ݾ�: " + receipt.getTotalAmount());
//                        System.out.println("���� ���� �ݾ�: " + (receipt.getTotalAmount()-receipt.getUsedPoints()));
//                        System.out.println("������ ����� �Ϸ� �Ǿ����ϴ�~!!!");
//                        System.out.println("--------------------------------------");
//                        System.out.println();

                        System.out.println("\t��������������������������������������������������������������������������������������������������������������");
                        System.out.println("\t��                                                     ��");
                        System.out.println("\t��  [ ������ ] ���������                              ��");
                        System.out.println("\t��  ---------------------------------------------      ��");
                        System.out.printf("\t��  %-8s \t%8s \t%8s         ��\n","��ȣ","ȸ������","�������Ʈ");
                        System.out.printf("\t��  %-8d \t%8s \t%8d              ��\n",receiptIndex,(receipt.isMember() ? "ȸ��" : "��ȸ��"),receipt.getUsedPoints());
                        System.out.println("\t��  ---------------------------------------------      ��");
                        System.out.printf("\t��  %-16s \t%14s   ��\n","��������",receipt.getPaymentMethod());
                        System.out.printf("\t��  %-16s \t%14.1f        ��\n","���������ݾ�",receipt.getTotalAmount()-receipt.getUsedPoints());

//                        System.out.printf("\t%-16s \t%14s\n","��������","�����ݾ�");
//                        System.out.printf("\t%-16s \t%14.1f\n",receipt.getPaymentMethod(),receipt.getTotalAmount());
                        System.out.println("\t��  ---------------------------------------------      ��");
                        System.out.println("\t��  ������ ����� �Ϸ� �Ǿ����ϴ�~!!!                  ��");
                        System.out.println("\t��  ---------------------------------------------      ��");
                        System.out.println("\t��                                                     ��");
                        System.out.println("\t��������������������������������������������������������������������������������������������������������������");

                        break;
                    }
                    receiptIndex++;
                }
            }
        }

        else
        {
            System.out.println("\t[!] �Է��� ��¥�� �ð��뿡 �ش��ϴ� �������� �����ϴ�.");
            System.out.println();
        }
    }

    public static void salesCheck()throws IOException
    {
        System.out.println("\n\t[ ���� ��ȸ ]====================");
        System.out.println("\t1. �� ���� ��ȸ");
        System.out.println("\t2. �� ���� ��ȸ");
        System.out.println("\t3. �� ���� ��ȸ");
        System.out.println("\t================================");
        System.out.print("\t�� �޴��� �����ϼ���: ");
        int reportChoice = Integer.parseInt(br.readLine());

        switch (reportChoice)
        {
            case 1:
                System.out.print("\t�� �� �Է�: ");
                int salesYear = Integer.parseInt(br.readLine());
                System.out.println();
                double YearlySales = 0.0;

                // ������ ���� �޼ҵ�
                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == salesYear)
                    {
                        YearlySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("\t[ %d�� ���� ��ȸ ]---------------------\n", salesYear);
                System.out.println("\t- "+salesYear + "�� ���� ��Ȳ: " + YearlySales + "��");
                System.out.println("\t�� �� ���� ��ȸ�� �Ϸ�Ǿ����ϴ�. ��");
                System.out.println("\t-------------------------------------");
                System.out.println();
                break;

            case 2:
                System.out.print("\t�� �� �Է�: ");
                int monthlySalesYear = Integer.parseInt(br.readLine());
                System.out.print("\t�� �� �Է�: ");
                int monthlySalesMonth = Integer.parseInt(br.readLine());
                System.out.println();
                double MonthlySales = 0.0;

                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == monthlySalesYear  && receipt.getMonth() == monthlySalesMonth )
                    {
                        MonthlySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("\t[ %d�� %d�� ���� ��ȸ ]-------------------\n", monthlySalesYear, monthlySalesMonth);
                System.out.println("\t- "+monthlySalesYear + "�� " + monthlySalesMonth + "�� ���� ��Ȳ: " + MonthlySales + "��");
                System.out.println("\t�� �� ���� ��ȸ�� �Ϸ�Ǿ����ϴ�. ��");
                System.out.println("\t-----------------------------------------");
                System.out.println();
                break;

            case 3:
                System.out.print("\t�� �� �Է�: ");
                int dailySalesYear = Integer.parseInt(br.readLine());
                System.out.print("\t�� �� �Է�: ");
                int dailySalesMonth = Integer.parseInt(br.readLine());
                System.out.print("\t�� �� �Է�: ");
                int dailySalesDay = Integer.parseInt(br.readLine());
                System.out.println();
                double dailySales = 0.0;

                // �Ϻ� ���� �޼ҵ�
                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == dailySalesYear && receipt.getMonth() == dailySalesMonth && receipt.getDay() == dailySalesDay)
                    {
                        dailySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("\t[ %d�� %d�� %d�� ���� ��ȸ ]---------------------\n", dailySalesYear, dailySalesMonth, dailySalesDay);
                System.out.println("\t- "+dailySalesYear + "�� " + dailySalesMonth + "�� " + dailySalesDay + "�� ���� ��Ȳ: " + dailySales + "��");
                System.out.println("\t�� �� ���� ��ȸ�� �Ϸ�Ǿ����ϴ�. ��");
                System.out.println("----------------------------------------------");
                System.out.println();
                break;

        }
    }

    public static void exit()
    {
        System.out.println();
        System.out.println("\n\t�� ������ �޴��� ���ư��ϴ�. ��");
        KioskMg.salesflag = false;
    }

}