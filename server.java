import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Server implements ActionListener	
{
	ArrayList<Socket> al = new ArrayList<Socket>();
	SortedMap<Long,String> order= new TreeMap<Long,String>();
	 ServerSocket ss;
	JFrame jf;
	TextArea ta;
	JLabel lab;
	String pre;
	JButton jb;
	JPanel jp;
	Socket s;
	GUI gui;
	int ind_ex=0;
	int userID=0;
	int pressed=0;
	int Nteam=10,Nque=15;
/*	public void gui()
	{
		jp = new JPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		jf = new JFrame();
		jf.setSize(width,height);
		jf.setVisible(true);
		lab = new JLabel();
		//ta = new TextArea();
		lab.setBounds((int)(0.6)*width,(int)(0.1)*height,(int)(0.3)*width,height);
		jf.setBackground(Color.GRAY);
		jb = new JButton("Show Order");
		jp.add(jb);
		jb.setBounds(300,20,50,50);
		jp.add(lab);
		jb.addActionListener(this);
		lab.setBackground(Color.BLUE);
		
	}
*/
//	GUI gui;
	public Server()
	{
//		gui();
		gui = new GUI(10,15);
		System.out.println("Server Started!! ... ");

	        
		
			try
			{
			        ss = new ServerSocket(5000);
				int clientCount = 0;
				while(clientCount<Nteam)
				{
					s = ss.accept();
					clientCount++;
					
					String 	name = s.getInetAddress().getHostName();
		 			System.out.println(s.getInetAddress().getHostName() + "Connected!!!");
					al.add(s);
					userID++;
					Runnable r = new MyThread(s,al,name,userID);	
					Thread t   = new Thread(r);
					t.start();
				}
			
		       	}	
	
			catch(Exception e)
			{
				System.out.println("Connect :"+ e);
			}
		
	}
	
	private class MyThread implements Runnable
	{
		Socket s;	
		ArrayList al;
		String name;
		int userID;
		MyThread(Socket s,ArrayList al,String name,int userID)
		{
			this.s = s;
			this.al = al;
			this.name = name;
			this.userID = userID;
		}
		public void run()
		{
			
		  try{
                

                DataInputStream stream = new DataInputStream(s.getInputStream());
        	//System.out.println("YYYY");
              	//BufferedReader reader =  new BufferedReader(stream);
///              System.out.println("bbbb");
                String message = null;	
	//	String pre = null;
		int count  = 0;
                while(true)
                {
               //         System.out.println("Inread");
		//	pre = ta.getText();
		//	pre = lab.getText();
                        message  = stream.readUTF();
                        long  output = System.currentTimeMillis();
			order.put(output,name);
		 synchronized(this)
			{
			System.out.println("" + ind_ex++);
			 gui.setColorIdentity((ind_ex++)%3,userID);	
			}
			System.out.println("GOT!!");
                  //      lab.setText( pre + "\n@"+output+"-:"+name+" : " +  message+"\n");
		//	count++;
                }
                //      stream.close();
                }
                catch(Exception e)
                {
                System.out.println("Read : "+e);
                e.printStackTrace();
                }

		}
	}	

	public static void main(String s[])
	{
		Thread t = Thread.currentThread();
		Server serv = 	new Server();
	//	serv.join();
			System.out.println("hi");
		//	serv.printOrder();	
		}
 	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("Printing Order...");
		Set st = order.entrySet();
		Iterator i = st.iterator();
		while(i.hasNext())
		{
			Map.Entry m = (Map.Entry)i.next();
			long time = (Long)m.getKey();
			String n = (String)m.getValue();
			System.out.println(time + "@" + n);
		}
	}	
	
}
