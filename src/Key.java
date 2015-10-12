import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;


public class Key extends KeyAdapter
{
	
	public void keyPressed(KeyEvent e)
	{
		
		int k=e.getKeyCode();
		
			if(k==KeyEvent.VK_UP)
			{
				
				if(map.current_y!=0)
				{
					map.Map[map.current_x][map.current_y]=6;
					map.current_y--;
			
						if(map.Map[map.current_x][map.current_y]==2)
						{
							map.current_y++;
						}
			
					map.Map[map.current_x][map.current_y]=4;
			
				}
			}
		
			else if(k==KeyEvent.VK_DOWN)
			{
				if(map.current_y!=(map.y*2))
				{
					map.Map[map.current_x][map.current_y]=6;
					map.current_y++;
				
						if(map.Map[map.current_x][map.current_y]==2)
						{
							map.current_y--;
						}
				
					map.Map[map.current_x][map.current_y]=4;
				}
			}
			
			else if(k==KeyEvent.VK_RIGHT)
			{
				if(map.current_x!=(map.x)*2)
				{
					map.Map[map.current_x][map.current_y]=6;
					map.current_x++;
					
						if(map.Map[map.current_x][map.current_y]==2)
						{
							map.current_x--;
						}
						
					map.Map[map.current_x][map.current_y]=4;
				
				}
			}
			
			else if(k==KeyEvent.VK_LEFT)
			{
				if(map.current_x!=0)
				{	
					map.Map[map.current_x][map.current_y]=6;
					map.current_x--;
				
						if(map.Map[map.current_x][map.current_y]==2)
						{
							map.current_x++;
						}
				
					map.Map[map.current_x][map.current_y]=5;
				
				}
			
			}
	
	}

}
