import java.util.*;

//Circle in a Rectangle
public class Main {
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");
		int W = Integer.parseInt(input[0]);
		int H = Integer.parseInt(input[1]);
		int x = Integer.parseInt(input[2]);
		int y = Integer.parseInt(input[3]);
		int r = Integer.parseInt(input[4]);
		String str;
		if(x <= 0 || x >= W || y <= 0 || y>=H )
		{
			//Circle is over
			str = "No";
		}
		else
		{
			if(x + r > W || x - r < 0 || y + r > H || y - r < 0)
			{
				str = "No";
			}
			else
			{
				str = "Yes";
			}
		}
		System.out.println(str);
		sc.close();
	}
}
