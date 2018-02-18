import java.util.*;

//sort
public class Main {
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");
		int [] abc = new int[input.length];
		for(int i = 0;i<abc.length;i++)
		{
			abc[i] = Integer.parseInt(input[i]);
		}
		for(int i=0;i<abc.length - 1;i++)
		{
			for(int t = i +1;t<abc.length;t++)
			{
				int temp = 0;
				if(abc[i] > abc[t])
				{
					temp = abc[i];
					abc[i] = abc[t];
					abc[t] = temp;
				}
			}
		}
		System.out.println(abc[0] + " " + abc[1] + " " + abc[2]);
		sc.close();
	}
}
