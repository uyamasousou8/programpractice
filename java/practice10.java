import javax.swing.*;
//import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
//ウィンドウを持つアプリケーションを創る
public class practice10 extends JFrame implements ActionListener
{
	JLabel label;
	public static void main(String[] args)
	{
		Main frame = new Main("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public practice10(String title)
	{
		setTitle(title);
		setBounds(10,10,300,200);
		
		//setLayout(new FlowLayout());
		
		label = new JLabel("");
		
		JPanel p = new JPanel();
		
		JButton btn = new JButton("Push");
		btn.addActionListener(this);
		
		p.add(label);
		p.add(btn);
		/*JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(100,50));
		p1.setBackground(Color.BLUE);
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(50,100));
		p2.setBackground(Color.ORANGE);
		
		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		p2.setBorder(border);
		
		//JButton btn = new JButton("ボタン");
		
		//add(btn,BorderLayout.NORTH);
		Container contentPane = getContentPane();
		contentPane.add(p1);
		*/
		getContentPane().add(p);
	}
	public void actionPerformed(ActionEvent e)
	{
		label.setText("push");
	}
}
