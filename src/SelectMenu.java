// �޴� ����
public class SelectMenu extends Super_Select {
    public SelectMenu() {
        this.message    = "\t�� �޴� ����: ";
        this.errorMsg   = "\t[!] �޴� ����Ʈ ��ȣ���� ������ϴ�. �ٽ� �Է����ּ���.";
//        this.minNum = 1;
    }

    @Override
    public int menuSelect(int listSize,int minNum) {
        super.minNum = minNum;
        return super.menuSelect(listSize,minNum);
    }
}