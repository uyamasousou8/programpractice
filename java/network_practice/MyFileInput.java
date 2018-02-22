package network_practice;
import java.io.*;

public class MyFileInput {
	public MyFileInput()
	{
		//何もしないコンストラクタ
	}
	public String FileOpen(String input) throws IOException
	{
		StringBuffer output = new StringBuffer("");
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(input));
			//デバッグ用
			//System.out.println("test");
			//ファイルが終わるまで出力し続ける。
			String buf = br.readLine();
			while(buf!=null)
			{
				output.append(buf);
				buf = br.readLine();
			}
			br.close();
			//デバッグ用
			System.out.println(output);
		}catch(IOException e)
		{
			System.out.println("File Open Error");
		}
		return output.toString();
	}
}
