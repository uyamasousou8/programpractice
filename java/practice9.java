import java.util.*;

//divisor Number
public class practice9 {
	public static void main(String args[])
	{
		String[] str = new String[3];
		int[] output = new int[3];
		Scanner sc = new Scanner(System.in);
		str = sc.nextLine().split(" ");
		output[0] = Integer.parseInt(str[0]);
		output[1] = Integer.parseInt(str[1]);
		output[2] = Integer.parseInt(str[2]);
		
		int number = 0;
		int divisor = 0;
		for(number = output[0];number<=output[1];number++)
		{
			if(output[2]%number == 0)
			{
				divisor++;
			}
		}
		System.out.println(divisor);
		sc.close();

	}
}