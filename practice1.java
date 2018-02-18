import java.util.*;

//ｘの３乗を計算し、結果を出力するプログラム
public class practice1 {
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int x = Integer.parseInt(input);
		x = x*x*x;
		System.out.println(x);
		
		sc.close();
	}
}
