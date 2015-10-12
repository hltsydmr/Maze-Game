import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class map extends JPanel	{
	
	public static int x=10 ,y=10;
	public static short Map[][] ;
	public boolean visited[][];
	boolean arr[][][];
	private Image tile,brick,free,here,heresol,done;
	private int start_x=0,start_y=0,finish_x=0,finish_y=0;
	public static int current_x,current_y,next_x,next_y;
	public int scale_x,scale_y;
	public Stack<Integer> stack_x = new Stack<Integer>();
	public Stack<Integer> stack_y = new Stack<Integer>();
	
	Thread td=new Thread();
	
	
	public void getInputs()
	{
		String input_x=JOptionPane.showInputDialog("X :");
		String input_y=JOptionPane.showInputDialog("Y :");
		try
		{
			x=Integer.parseInt(input_x);
			y=Integer.parseInt(input_y);
	}
		catch(Exception e){
		JOptionPane.showMessageDialog(null, "Entered invalid input , please enter valid integer");
		getInputs();
		}
		
	}

	public boolean dortKomsu(int a,int b){

		if(arr[a][b][0]==true && arr[a][b][1]==true && arr[a][b][2]==true && arr[a][b][3]==true){
			return true;
		}
		else{
			return false;
		}
		
		
	}
	
	public void GIRIS(){
		Random bit = new Random();
		int fbit = bit.nextInt(2);
		
		if(fbit==0){
		Random sx = new Random();
		start_x= sx.nextInt(2*x);
		Random fx = new Random();
		finish_x=fx.nextInt(2*x);
		start_y=1;
		finish_y=(2*y)-1;
			if(Map[start_x][1]==1 && Map[finish_x][(2*y)-1]==1){
				Map[start_x][0]=6;
				Map[finish_x][(2*y)]=6;
			//	Map[start_x][start_y]=6;
			//	Map[finish_x][finish_y]=6;

				Map[start_x][0]=4;
				current_x=start_x;
				current_y=0;
			}
			else {GIRIS();}
		}
		
		else{
			Random sy = new Random();
			start_y= sy.nextInt(2*y);
			Random fy = new Random();
			finish_y=fy.nextInt(2*y);
			start_x=1;
			finish_x=(2*x)-1;
			

			if(Map[1][start_y]==1 && Map[(2*x)-1][finish_y]==1){
				Map[0][start_y]=6;
				Map[(2*x)][finish_y]=6;

				Map[0][start_y]=4;
				current_x=0;
				current_y=start_y;
			}
			else {GIRIS();}
		}
			
		}
		
	

	public void GenerateStartPoint()
	{
		
		while(Map[start_x][start_y]==2 || Map[finish_x][finish_y]==2)
		{
			Random r1x=new Random();
			Random r1y=new Random();
			Random r2x=new Random();
			Random r2y=new Random();
			
			start_x=(r1x.nextInt(2*x));
			start_y=(r1y.nextInt(2*y));
			finish_x=(r2x.nextInt(2*x));
			finish_y=(r2y.nextInt(2*y));
			
			
		}
		current_x=start_x;
		current_y=start_y;
	}
	
	public void ortakinit(){
		
		for(int i=0;i<(2*x)+1;i=i+1)
		{
			
			for(int j=0;j<(y*2)+1;j=j+1)
			{
				
				visited[i][j]=false;
				
				for(int k=0;k < 4;k++)
				{
					arr[i][j][k]=false;
					
				}
				
				arr[1][j][0]=true;
				arr[(2*x)-1][j][2]=true;
			}
			
			arr[i][1][3]=true;
			arr[i][(2*y)-1][1]=true;
		}
		
		
	}
	
	public void init()
	{
		
		visited=new boolean[(2*x)+1][(y*2)+1];
		Map = new short [(2*x)+1][(y*2)+1];
		arr=new boolean[(2*x)+1][(2*y)+1][4];
		
		
		for(int i=0;i<(2*x)+1;i=i+1)
		{
			
			for(int j=0;j<(y*2)+1;j=j+1)
			{
				
				Map[i][j]=2;
				visited[i][j]=true;
				
				for(int k=0;k < 4;k++)
				{
					arr[i][j][k]=false;
					
				}
				
				arr[1][j][0]=true;
				arr[(2*x)-1][j][2]=true;
			}
			
			arr[i][1][3]=true;
			arr[i][(2*y)-1][1]=true;
		}
	
		
		for(int c=1;c<(2*x)+1;c=c+2)
		{
			for(int v=1;v<(2*y)+1;v=v+2)
			{
				Map[c][v]=1;
				visited[c][v]=false;
			}
		}
		

		
		
	}
	

	public void paint(Graphics g) 
	{
		
		super.paint(g);
	
		scale_x=maze.WIDTH/(x*2)+1;
		scale_y=maze.HEIGHT/(y*2)+1;
	
		for(int a=0;a<(x*2)+1;a++)
		{
		
			for(int b=0;b<(y*2)+1;b++)
			{
			
				if(Map[a][b]==1)
				{
					
					g.drawImage(tile, a*scale_x, b*scale_y, scale_x, scale_y, null);
				}
			
				if(Map[a][b]==2)
				{
					
					g.drawImage(brick, a*scale_x, b*scale_y, scale_x, scale_y, null);
				
				}
			
				if(Map[a][b]==3)
				{
					
					g.drawImage(free, a*scale_x, b*scale_y, scale_x, scale_y, null);
				
				}
			
				if(Map[a][b]==4)
				{
				
					g.drawImage(here,a*scale_x,b*scale_y,scale_x,scale_y,null);
					
				}
			
				if(Map[a][b]==5)
				{
					
					g.drawImage(heresol,a*scale_x,b*scale_y,scale_x,scale_y,null);
				
				}
				
				if(Map[a][b]==6)
				{
					
					g.drawImage(done,a*scale_x,b*scale_y,scale_x,scale_y,null);
				
				}
			}
		}
		
		repaint();
	
	}

	public void update(Graphics g)
	{
	
		paint(g);
		
	}

	public map()
	{
	
		addKeyListener(new Key());
		getInputs();
	
		init();
	
		
		ImageIcon imgtile=new ImageIcon("sourcee\\Tile.jpg");
		tile=imgtile.getImage();
		
		ImageIcon imgbrick=new ImageIcon("sourcee\\Brick.jpg");
		brick=imgbrick.getImage();
	
		ImageIcon imgfree=new ImageIcon("sourcee\\Free.jpg");
		free=imgfree.getImage();
	
	
		ImageIcon imgHere=new ImageIcon("sourcee\\Here.jpg");
		here=imgHere.getImage();
	
		ImageIcon imgHereSol=new ImageIcon("sourcee\\Heresol.jpg");
		heresol=imgHereSol.getImage();
	
		ImageIcon imgDone=new ImageIcon("sourcee\\Done.png");
		done=imgDone.getImage();
		
		
	        ActionListener animate = new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                repaint();
	            }
	        };
	        Timer timer = new Timer(1000,animate);
	        timer.start();
	    
		//repaint();
		
		DFS();
		//GenerateStartPoint();
		GIRIS();
		
		
		
		//current_x=start_x;
		//current_y=start_y;
		
	}

	public void Done(int yon,int a,int b){
		
		stack_x.add(a);
		stack_y.add(b);
		
		visited[stack_x.peek()][stack_y.peek()]=true;
		
		if(yon==0)
			Map[stack_x.peek()+1][stack_y.peek()]=1;
			
		else if(yon==1)
			Map[stack_x.peek()][stack_y.peek()-1]=1;
			
		else if(yon==2)
			Map[stack_x.peek()-1][stack_y.peek()]=1;
			
		else if(yon==3)
			Map[stack_x.peek()][stack_y.peek()+1]=1;
		
		repaint();
		
	}

	public void checkKomsu(int yon,int a,int b)
	{
		
		
		
		if (yon == 0)
		{	System.out.println("yon0");							//if ( yon==0){
			if (a-2 <0)							// if( a-2 > 0 && visited [a-2][b]==true ){
			{										//		KomsuBul(a,b);
/*-----*/		DFS_VISIT(a,b);						//	}
			}										// else {
			else if(visited[a-2][b]==true){			//		Done( a-2 , b)
				arr[a-2][b][2]=true;
				arr[a][b][0]=true;								//		DFS_VISIT( a-2, b);
				DFS_VISIT(a,b);						//	}
			}										//}
			else 
			{
				Done(0,a-2,b);
				
				arr[a-2][b][2]=true;
				arr[a][b][0]=true;
				DFS_VISIT(stack_x.peek(),stack_y.peek());
			}
		}
		
		else if (yon == 1)
		{System.out.println("yon1");
			if ( b+2 >((2*y)))
			{
/*-----*/				DFS_VISIT(a,b);
			}
			else if(visited[a][b+2]==true){
				arr[a][b][1]=true;
				arr[a][b+2][3]=true;		
				DFS_VISIT(a,b);
			}
			else 
			{
				Done(1,a,b+2);
				
				arr[a][b][1]=true;
				arr[a][b+2][3]=true;
				DFS_VISIT(stack_x.peek(),stack_y.peek());
			}
		}
		
		else if (yon == 2)
		{System.out.println("yon 2 ye grdi");

			if ( a+2 >((2*x)) )
			{System.out.println("a+2kosul");
/*-----*/				DFS_VISIT(a,b);
			}
			else if(visited[a+2][b]==true){System.out.println("a+2 true ya");
			arr[a][b][2]=true;
			arr[a+2][b][0]=true;	
				DFS_VISIT(a,b);
			}
			else 
			{System.out.println("else e gýrdý");
				Done(2,a+2,b);
				
				arr[a][b][2]=true;
				arr[a+2][b][0]=true;
				DFS_VISIT(stack_x.peek(),stack_y.peek());
			}
		}
		
		else if (yon == 3)
		{System.out.println("yon3");

			if ( b-2 <0)
			{
/*-----*/				DFS_VISIT(a,b);
			}
			else if(visited[a][b-2]==true){
				arr[a][b][3]=true;
				arr[a][b-2][1]=true;
				DFS_VISIT(a,b);
			}
			else 
			{
				
				Done(3,a,b-2);
				
				arr[a][b][3]=true;
				arr[a][b-2][1]=true;
				DFS_VISIT(stack_x.peek(),stack_y.peek());
			}
		}
	}
	
	public void KomsuBul(int a,int b)
	{
		
		Random rnd = new Random();
		int yon = rnd.nextInt(4);
		
		checkKomsu(yon,a,b);
	}

	public void DFS_VISIT(int k,int l)
	{
		try{
			
			repaint();
			
			System.out.println(k+","+l);
			System.out.println(stack_x+"\n"+stack_y+"stackten");
			if(!stack_x.isEmpty()){
	
				if(dortKomsu(k,l)==false)
				{System.out.println(arr[k][l][0]+""+arr[k][l][1]+""+arr[k][l][2]+""+arr[k][l][3]);
					System.out.println("hata burada");
					KomsuBul(stack_x.peek(),stack_y.peek());
				}
				else
				{
					System.out.println("pop a gýrýor");
					stack_x.pop();
					stack_y.pop();
					System.out.println(dortKomsu(k,l));
					System.out.println("popped");
			
			
			
					DFS_VISIT(stack_x.peek(),stack_y.peek());
				
				}
			}
			
		}
					catch(Exception e){	
						e.printStackTrace();
			
		}
	}

	public void DFS()
	{
		System.out.println("dfs baslýyor");
		
		stack_x.push(3);
		stack_y.push(3);
		visited[3][3]=true;
		
		DFS_VISIT(3,3);
		
	}
	
	public void COZUMDONE( int a, int b)
	{
		stack_x.add(a);
		stack_y.add(b);
		
		visited[stack_x.peek()][stack_y.peek()]=true;
		//Map[stack_x.peek()][stack_y.peek()]=6;
		COZUMVISIT(a,b);
	
		repaint();	
	}
	
	public void COZUM(int a,int b){
		
		if(visited[a-1][b]==false && Map[a-1][b]==1){
			COZUMDONE(a-1,b);
		}
		else if(visited[a][b+1]==false && Map[a][b+1]==1){
			COZUMDONE(a,b+1);
		}
		else if(visited[a+1][b]==false && Map[a+1][b]==1){
			COZUMDONE(a+1,b);
		}
		else if(visited[a][b-1]==false && Map[a][b-1]==1){
			COZUMDONE(a,b-1);
		}
		else{
			stack_x.pop();
			stack_y.pop();
			COZUM(stack_x.peek(),stack_y.peek());
		}
	
	
	}
		
		
	/*	catch(Exception e){
			System.out.println("CATCH E GIRDI");
			stack_x.pop();
			stack_y.pop();
			System.out.println("POP ETTI");
			COZUM(stack_x.peek(),stack_y.peek());
		System.out.println("COZUMU PEEK ILE CALISTIRDI");
		}
		*/
			
		
	
	
	
	public void COZUMVISIT(int k,int l){
		
		try{
			if(finish_x!=k || finish_y!=l){
				COZUM(k,l);
			}
			else{
				System.out.println(stack_x+"\n"+stack_y);
				while (!stack_y.isEmpty()){
					System.out.println(stack_x+"\n"+stack_y);
					Map[stack_x.pop()][stack_y.pop()]=6;
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void DFS_COZUM(){
		
		ortakinit();
		//GenerateStartPoint();
		//start_x=3;
		//start_y=3;
		
		stack_x.clear();
		stack_y.clear();
		
		stack_x.push(start_x);
		stack_y.push(start_y);
		//finish_x=21;
		//finish_y=21;
		
		COZUM(start_x,start_y);
		System.out.println("start"+start_x+start_y);
		System.out.println("finish"+finish_x+finish_y);
		
	}

}