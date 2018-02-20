import java.util.*;

//ｘの３乗を計算し、結果を出力するプログラム
public class practice2 {
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");
		int x = Integer.parseInt(input[0]);
		int y = Integer.parseInt(input[1]);
		int menseki = x * y;
		int syuui = x + x + y + y;
		System.out.println(menseki + " " + syuui);
		
		sc.close();
	}
}
