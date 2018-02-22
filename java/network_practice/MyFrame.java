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
	byte crlf [] = {13,10};//�L�����b�W���^�[���iCR)�C���s(LF)�̃o�C�g�ł̕��тő��M���̋�؂�p
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
				//�T�[�o�[���̏���
				//textArea.append("Sever wait...\n");
				ServerSocket ssocket = new ServerSocket(port);
				Socket socket = ssocket.accept();
				textArea.append("Sever Connect...\n");
				
				//�N���C�A���g����̓��͂�ǂݍ��݃R�l�N�g����
				InputStream is = socket.getInputStream();
				textArea.append(socket.getRemoteSocketAddress() + " is connect\n");
				InputStreamReader isb = new InputStreamReader(is);
				
				textArea.append("Sever send...\n");
				//�R�l�N�g������ɃA�E�g�v�b�g��n���B
				OutputStream os = socket.getOutputStream();
				String send = "HTTP/1.1 200 OK"; //���X�|���X���b�Z�[�W
				os.write(send.getBytes());
				os.write(crlf);
				os.write(crlf);
				
				//�^��HTML�R�[�h��n��
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
