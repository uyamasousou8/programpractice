import java.util.*;

//Swap Number
public class practice8 {
	public static void main(String args[])
	{
		String[] str = new String[2];
		int[][] output = new int[3000][2];
		Scanner sc = new Scanner(System.in);
		str = sc.nextLine().split(" ");
		int i = 0;
		while(Integer.parseInt(str[0]) != 0 || Integer.parseInt(str[1]) != 0 )
		{
			output[i][0] = Integer.parseInt(str[0]);
			output[i][1] = Integer.parseInt(str[1]);
			str = sc.nextLine().split(" ");
			i++;
		}
		int n = 0;
		for(n = 0;n<i;n++)
		{
			int temp = 0;
			if(output[n][0] > output[n][1])
			{
				temp = output[n][0];
				output[n][0] = output[n][1];
				output[n][1] = temp;
			}
			System.out.println(output[n][0] + " " + output[n][1]);
		}
		sc.close();
	}
}