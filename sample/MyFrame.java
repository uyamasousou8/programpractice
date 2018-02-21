package sample;
import javax.swing.*;
import java.io.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.datatransfer.*;
import java.awt.image.*;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.imageio.*;
import java.util.List;
public class MyFrame extends JFrame
{
	JPanel p;
	JLabel l;
	JTextArea textArea;
	StringBuffer fileList = new StringBuffer();
	public MyFrame()
	{
		this.setTitle("test");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public MyFrame(int x,int y,int w,int h,String title)
	{
		this.initalize(x,y,w,h,title);
	}
	private void initalize(int x,int y,int w,int h,String title)
	{
		this.setTitle(title);
		this.setBounds(x,y,w,h);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel();
		l = new JLabel("");
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(p);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JButton btn = new JButton("�O���[�X�P�[����");
		btn.addActionListener(new MyListener(this,this.l,this.fileList));
		
		//p.add(l);
		//p.add(btn);
		this.getContentPane().add(btn, BorderLayout.SOUTH);
		this.getContentPane().add(l, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setTransferHandler(new DropFileHandler());
		
		
	}

	public void setLabel(String input)
	{
		l.setText(input);
	}
	private class DropFileHandler extends TransferHandler 
	{
		//�h���b�v���ꂽ���̂��󂯎�邩���f�i�t�@�C���̂Ƃ��󂯎��j
		@Override
		public boolean canImport(TransferSupport support)
		{
			if(!support.isDrop())
			{
				return false;
			}
			if(!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
			{
				return false;
			}
			return true;
		}
		@Override
		public boolean importData(TransferSupport support)
		{
			if(!canImport(support))
			{
				return false;
			}
			
			Transferable t = support.getTransferable();
			try
			{
				//�t�@�C�����󂯎��
				List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

				//�e�L�X�g�G���A�ɕ\������t�@�C�������X�g���쐬����B
				for(File file : files)
				{
					fileList.append(file.getPath());
					fileList.append("\n");
				}
				//�e�L�X�g�G���A�Ƀt�@�C���������X�g�\������
				textArea.setText(fileList.toString());
				
			}catch(UnsupportedFlavorException | IOException e)
			{
				e.printStackTrace();
			}
			return true;
		}
	}
}

class MyListener implements ActionListener
{
	JFrame frame;
	JLabel label;
	StringBuffer fileList;
	public MyListener(JFrame iframe,JLabel ilabel,StringBuffer ifileList)
	{
		frame = iframe;
		label = ilabel;
		fileList = ifileList;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		boolean result;
		BufferedImage img = null;
		String[] inname = fileList.toString().split("\n");
		String[] outname = new String[inname.length];
		for(int i=0;i<inname.length;i++)
		{
			//�t�@�C�����摜�t�@�C��������������B
			if(inname[i].indexOf(".jpg") != -1)
			{
				outname[i] = inname[i].replace(".jpg", "gs.jpg");
			}
			else if(inname[i].indexOf(".png") != -1)
			{
				outname[i] = inname[i].replace(".png", "gs.png");
			}
			else
			{
				//�����t�@�C������.jpg,.png���܂܂Ȃ���Ώ������s��Ȃ�
				return;
			}
			//�f�o�b�O�p
			System.out.println(inname[i]);
			System.out.println(outname[i]);
			try
			{
				img = ImageIO.read(new File(inname[i]));
			}catch(Exception ee)
			{
				ee.printStackTrace();
				return ;
			}
			
			if(BufferedImage.TYPE_3BYTE_BGR != img.getType())
			{
				label.setText("�Ή����Ă��Ȃ��J���[���f���ł��I(" + inname[i] + ")");
				continue;
			}
			
			int x,y;//�摜�̈ʒu
			int width,height;//�摜�̃T�C�Y
			int color,r,g,b; //�F
			int p;
			int newColor;
			
			//�摜�T�C�Y�̎擾
			width = img.getWidth();
			height = img.getHeight();
			
			for(y = 0;y<height;++y)
			{
				for(x = 0;x<width;++x)
				{
					color = img.getRGB(x, y);
					r = (color >> 16)& 0xff;
					g = (color >> 8)& 0xff;
					b = color & 0xff;
					
					//rgb�̕��ϒl���v�Z
					p = (r + g + b)/3;
					
					r = p;
					g = p;
					b = p;
					
					newColor = (r << 16) + (g << 8) + b;
					
					//���������F���ix,y)�ɐݒ�
					img.setRGB(x, y, newColor);
				}
			}
			try
			{
				result = ImageIO.write(img, "jpg", new File(outname[i]));
			}catch(Exception ee)
			{
				ee.printStackTrace();
				return;
			}
			if(result)
			{
				label.setText("����");
			}else
			{
				label.setText("���s");
			}
		}
	}
}
