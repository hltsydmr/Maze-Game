import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class maze implements ActionListener
{
	private JFrame jf;
	private Button btn,czm;
	private map mymap;
	public static int WIDTH =600 , HEIGHT=600;
	
	public static void main(String[] args)
	{
		
		new maze();
		
	}
	
	public maze()
	{
	
		jf = new JFrame();
				
		jf.setTitle("Find the way !");
		jf.setSize(708,710);
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp=new JPanel();
		jf.add(jp,BorderLayout.SOUTH);
		jp.setLayout(new GridLayout(1,2));
	
		
		
		btn=new Button("Generate Maze");
		btn.setForeground(Color.yellow);
		btn.setBackground(Color.black);
		
		//jf.add(btn,new GridLayout(BorderLayout.SOUTH));
		btn.addActionListener(this);
		
		czm=new Button("Solve");
		czm.setForeground(Color.BLACK);
		czm.setBackground(Color.yellow);
		//jf.add(czm,BorderLayout.EAST);
		czm.addActionListener(this);
		
		jp.add(btn);
		jp.add(czm);
		
		mymap = new map();        
		mymap.setFocusable(true);
		jf.add(mymap);
		
		jf.setVisible(true);  
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource()==btn)
		{
			
			mymap=new map();
	
		}
		
		if(e.getSource()==czm)
		{
			
			mymap.DFS_COZUM();
		
		}
		
	}
	
}
