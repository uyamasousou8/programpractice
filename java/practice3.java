import java.util.*;

//�b���玞�Ԃ�����o���v���O����
public class practice3 {
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int inputInt = Integer.parseInt(input);
		
		int hour = inputInt / 3600;
		inputInt -= hour * 3600;
		int minute = inputInt / 60;
		inputInt -= minute * 60;
		int second = inputInt;
		
		System.out.println(hour + ":" + minute + ":" + second);
		sc.close();
	}
}
