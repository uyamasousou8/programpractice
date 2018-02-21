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
		
		JButton btn = new JButton("グレースケール化");
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
		//ドロップされたものを受け取るか判断（ファイルのとき受け取る）
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
				//ファイルを受け取る
				List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

				//テキストエリアに表示するファイル名リストを作成する。
				for(File file : files)
				{
					fileList.append(file.getPath());
					fileList.append("\n");
				}
				//テキストエリアにファイル名をリスト表示する
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
			//ファイルが画像ファイルかを検索する。
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
				//もしファイル名が.jpg,.pngを含まなければ処理を行わない
				return;
			}
			//デバッグ用
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
				label.setText("対応していないカラーモデルです！(" + inname[i] + ")");
				continue;
			}
			
			int x,y;//画像の位置
			int width,height;//画像のサイズ
			int color,r,g,b; //色
			int p;
			int newColor;
			
			//画像サイズの取得
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
					
					//rgbの平均値を計算
					p = (r + g + b)/3;
					
					r = p;
					g = p;
					b = p;
					
					newColor = (r << 16) + (g << 8) + b;
					
					//合成した色を（x,y)に設定
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
				label.setText("成功");
			}else
			{
				label.setText("失敗");
			}
		}
	}
}
