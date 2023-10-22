import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class FileMg
{
    // ������ȭ : ���� -> ��ü �����ϱ�
    public HashMap<String,Member> memberFileIn() throws IOException, ClassNotFoundException
    {
        HashMap<String,Member> h2 = new HashMap<String,Member>();
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/member.ser"));
            h2 = (HashMap<String,Member>) ois.readObject();
            ois.close();
            return h2;
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("\tmember.ser ������ ã�� �� �����ϴ�.");
            return h2;
        }
    }

    public ArrayList<Receipt> receiptFileIn() throws IOException, ClassNotFoundException
    {
        ArrayList<Receipt> l2 = new ArrayList<> ();
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/receipt.ser"));
            l2 = (ArrayList<Receipt>) ois.readObject();
            ois.close();
            return l2;

        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("\treceipt.ser ������ ã�� �� �����ϴ�.");
            return l2;
        }
    }

    public List<Product> list1FileIn() throws IOException, ClassNotFoundException{
        List<Product> list1fileIn = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/list1.ser"));
            list1fileIn = (List<Product>) ois.readObject();
            ois.close();
            return list1fileIn;
        }catch (FileNotFoundException fnfe)
        {
            System.out.println("\tlist1.ser ������ ã�� �� �����ϴ�.");
            return list1fileIn;
        }
    }
    public List<MasterRc> list2FileIn() throws IOException, ClassNotFoundException{
        List<MasterRc> list2fileIn = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/list2.ser"));
            list2fileIn = (List<MasterRc>) ois.readObject();
            ois.close();
            return list2fileIn;
        }catch (FileNotFoundException fnfe)
        {
            System.out.println("\tlist2.ser ������ ã�� �� �����ϴ�.");
            return list2fileIn;
        }
    }

    //-------------------------------------------------------------------------------------------------------------
    // ����ȭ : ��ü -> ���� �����ϱ�
    public void memberFileOut() throws IOException
    {
        String appDir = System.getProperty("user.dir");
        //-- �ý��� �Ӽ����κ��� ���� ����ڰ� ������� ���丮 ���� ������

        File f0 = new File(appDir, "/data/member.ser");

        // test.ser ������ ��������� �� ���丮 ��ΰ� �����Ǿ� ���� �ʴٸ�..
        if (!f0.getParentFile().exists())
        {
            f0.getParentFile().mkdirs();
        }
        HashMap<String,Member> h1 = MemberMg.hm;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f0));

        // ������ ��Ʈ���� ������ ��ü�� ���
        oos.writeObject(h1);

        oos.close();
    }
    public void receiptFileOut() throws IOException
    {
        String appDir = System.getProperty("user.dir");
        //-- �ý��� �Ӽ����κ��� ���� ����ڰ� ������� ���丮 ���� ������

        File f0 = new File(appDir, "/data/receipt.ser");

        ArrayList<Receipt> l1 = SalesMg.receipts;

        //l1.add(new Receipt(2023, 10, 4, 18, "01012341234", true, 1000, "�Ϲ�ī��", 100000, false));
        //l1.add(new Receipt(2022, 10, 2, 20, "ȸ���� �ƴմϴ�", false, 0, "īī��", 50000, false));
        //l1.add(new Receipt(2022, 10, 2, 18, "01024562456", true, 400,  "�Ϲ�ī��", 20000, false));
        //l1.add(new Receipt(2022, 10, 2, 18, "ȸ���� �ƴմϴ�", false, 0, "īī��", 30000, false));
        //l1.add(new Receipt(2020, 10, 2, 18, "01023456789", true, 300, "�Ｚ����", 10000, false));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f0));

        // ������ ��Ʈ���� ������ ��ü�� ���
        oos.writeObject(l1);

        oos.close();
    }

    public void list1FileOut() throws IOException, ClassNotFoundException{
        String appDir = System.getProperty("user.dir");
        //-- �ý��� �Ӽ����κ��� ���� ����ڰ� ������� ���丮 ���� ������

        File f0 = new File(appDir, "/data/list1.ser");

        List<Product> list1 = CacheData.allProductList;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f0));

        // ������ ��Ʈ���� ������ ��ü�� ���
        oos.writeObject(list1);

        oos.close();
    }
    public void list2FileOut() throws IOException, ClassNotFoundException{
        String appDir = System.getProperty("user.dir");
        //-- �ý��� �Ӽ����κ��� ���� ����ڰ� ������� ���丮 ���� ������

        File f0 = new File(appDir, "/data/list2.ser");

        List<MasterRc> list2 = CacheData.masterRcList;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f0));

        // ������ ��Ʈ���� ������ ��ü�� ���
        oos.writeObject(list2);

        oos.close();
    }

}