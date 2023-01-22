import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.event.*;


@SuppressWarnings("serial")
public class Index extends JFrame implements ActionListener{
	JLabel nameofcom,slogan;
	Font ftop = new Font("Comic Sans MS",Font.BOLD,35);
	Font fbut = new Font("Comic Sans MS",Font.BOLD,25);
	JButton bpaxinsert,bdisplay,bflightinsert,bsearch,bupdate,bdelete;
	
	public Index(){
		super("OneWorld Airline");
		setSize(700,700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p1 = new JPanel();
		p1.setBackground(Color.white);
		p2 = new JPanel();
		p2.setBackground(Color.cyan);
		p3 = new JPanel();
		p3.setBackground(Color.cyan);
		p4 = new JPanel();
		p4.setBackground(Color.white);
		p5 = new JPanel();
		p5.setBackground(Color.cyan);
	
		
		GridLayout g = new GridLayout(2,1,5,5);
		p1.setLayout(g);
		
		
		bpaxinsert = new JButton("Book a Flight");
		bdisplay = new JButton("Display Booked Flights");
		bflightinsert = new JButton("Add a new Flight");
		bsearch = new JButton("Search for a booking");
		bupdate = new JButton("Update/Change Booking");
		bdelete = new JButton("Cancel a Flight");
		
		bpaxinsert.setFont(fbut);
		bdisplay.setFont(fbut);
		bflightinsert.setFont(fbut);
		bsearch.setFont(fbut);
		bupdate.setFont(fbut);
		bdelete.setFont(fbut);
		
		bpaxinsert.setForeground(Color.BLUE);
		bdisplay.setForeground(Color.BLUE);
		bflightinsert.setForeground(Color.BLUE);
		bsearch.setForeground(Color.BLUE);
		bupdate.setForeground(Color.BLUE);
		bdelete.setForeground(Color.BLUE);
		
		
		GridLayout gbu = new GridLayout(3,3,10,10);
		p4.setLayout(gbu);
		p4.add(bpaxinsert);
		p4.add(bflightinsert);
		p4.add(bdisplay);
		p4.add(bsearch);
		p4.add(bupdate);
		p4.add(bdelete);
		
		
		bpaxinsert.addActionListener(this);
		bdisplay.addActionListener(this);
		bflightinsert.addActionListener(this);
		bsearch.addActionListener(this);
		bupdate.addActionListener(this);
		bdelete.addActionListener(this);
		
		
		
		
		
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == bflightinsert){
			setVisible(false);
			new InsertFlight();
		}
		if(e.getSource() == bpaxinsert){
			setVisible(false);
			new InsertPassangers();
		}
		if(e.getSource() == bupdate){
			setVisible(false);
			new UpdatePax();
		}
		if(e.getActionCommand().equals("Cancel a Flight")){
			setVisible(false);
			new DeletePax();
		}
		if(e.getSource() == bdisplay){
			setVisible(false);
			new DisplayAll();
		}
		if(e.getSource() == bsearch){
			setVisible(false);
			new SearchPax();
		}
	}
	
	public static void main(String args[]){
		new Index();
	}

}
