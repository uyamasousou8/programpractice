package network_practice;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class MyFrame extends JFrame 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyFileInput fileinput;
	byte crlf [] = {13,10};//キャリッジリターン（CR)，改行(LF)のバイトでの並びで送信時の区切り用
	JPanel p;
	JLabel l;
	JTextArea textArea;
	
	public MyFrame()
	{
		this.setTitle("test");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public MyFrame(int x,int y,int w,int h,String Title)
	{
		this.Initialize(x,y,w,h,Title);
	}
	private void Initialize(int x,int y,int w,int h,String Title)
	{
		fileinput = new MyFileInput();
		this.setTitle(Title);
		this.setBounds(x,y,w,h);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel();
		l = new JLabel("");
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(p);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JButton btn = new JButton("network Start");
		
		this.getContentPane().add(btn, BorderLayout.SOUTH);
		this.getContentPane().add(l, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		btn.addActionListener(new MyListener(this,this.l,this.textArea));
	}
	class MyListener implements ActionListener
	{
		JFrame frame;
		JLabel label;
		JTextArea textArea;
		public MyListener(JFrame iframe,JLabel ilabel,JTextArea itextArea)
		{
			frame = iframe;
			label = ilabel;
			textArea = itextArea;
		}
		public void actionPerformed(ActionEvent e)
		{
			//label.setText("Start");
			try
			{
				int port = 80;
				//サーバー側の準備
				//textArea.append("Sever wait...\n");
				ServerSocket ssocket = new ServerSocket(port);
				Socket socket = ssocket.accept();
				textArea.append("Sever Connect...\n");
				
				//クライアントからの入力を読み込みコネクトする
				InputStream is = socket.getInputStream();
				textArea.append(socket.getRemoteSocketAddress() + " is connect\n");
				InputStreamReader isb = new InputStreamReader(is);
				
				textArea.append("Sever send...\n");
				//コネクトした先にアウトプットを渡す。
				OutputStream os = socket.getOutputStream();
				String send = "HTTP/1.1 200 OK"; //レスポンスメッセージ
				os.write(send.getBytes());
				os.write(crlf);
				os.write(crlf);
				
				//疑似HTMLコードを渡す
				String html = fileinput.FileOpen("c:\\server\\data\\test.txt");
				//html = "<html><body>\n";
				//html += "<h1>";
				//html += "Hello World";
				//html += "<h1>\n";
				//html += "</body></html>\n";
				os.write(html.getBytes());
				os.write(crlf);
				os.write(crlf);
				os.flush();
				os.close();
				textArea.append("success\n");
				textArea.append("Sever close...\n");

				ssocket.close();
				
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
			label.setText("Exit");
		}
	}
}
