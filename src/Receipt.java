import java.io.Serializable;
import java.util.*;

public class Receipt implements Serializable
{
    private static final long serialVersionUID = 467635289974541449L;

    private int year;				// ��
    private int month;				// ��
    private int day;				// ��	
    private int hour;				// �ð�
    private boolean isMember;			// ȸ�� ����
    private int usedPoints;			// ����Ʈ ���
    private String paymentMethod;	// ���� ����
    private double totalAmount;		// �� ���� �ݾ�
    private boolean isCancelled;
    private String memberId;



    // ������
    public Receipt(int year, int month, int day, int hour, String memberId, boolean isMember, int usedPoints, String paymentMethod, double totalAmount)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.isMember = isMember;
        this.usedPoints = usedPoints;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.memberId = memberId;
    }

    public Receipt(int year, int month, int day, int hour, String memberId, boolean isMember, int usedPoints, String paymentMethod, double totalAmount, boolean isCancelled)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.isMember = isMember;
        this.usedPoints = usedPoints;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.isCancelled = isCancelled;
        this.memberId = memberId;
    }

    // Getter �� Setter �޼ҵ� �߰�

    public int getYear() {return year;}

    public void setYear(int year){this.year = year;}

    public int getMonth() {return month;}

    public void setMonth(int month){this.month = month;}

    public int getDay() {return day;}

    public void setDay(int day) {this.day = day;}

    public int getHour(){return hour;}

    public void setHour(int hour) {this.hour = hour;}

    public boolean isMember() {return isMember;}

    public void setIsMember(boolean isMember) {this.isMember = isMember;}

    public int getUsedPoints() {return usedPoints;}

    public void setUsedPoints(int usedPoints) {this.usedPoints = usedPoints;}

    public String getPaymentMethod() {return paymentMethod;}

    public void setPaymentMethod(String paymentMethod){this.paymentMethod = paymentMethod;}

    public double getTotalAmount() {return totalAmount;}

    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    public boolean isCancelled() {return isCancelled;}

    public String getMemberId() {return memberId;}

    @Override
    public String toString() {
        return "Receipt{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", isMember=" + isMember +
                ", usedPoints=" + usedPoints +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", isCancelled=" + isCancelled +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}