package network_practice;
import java.io.*;

public class MyFileInput {
	public MyFileInput()
	{
		//�������Ȃ��R���X�g���N�^
	}
	public String FileOpen(String input) throws IOException
	{
		StringBuffer output = new StringBuffer("");
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(input));
			//�f�o�b�O�p
			//System.out.println("test");
			//�t�@�C�����I���܂ŏo�͂�������B
			String buf = br.readLine();
			while(buf!=null)
			{
				output.append(buf);
				buf = br.readLine();
			}
			br.close();
			//�f�o�b�O�p
			System.out.println(output);
		}catch(IOException e)
		{
			System.out.println("File Open Error");
		}
		return output.toString();
	}
}
