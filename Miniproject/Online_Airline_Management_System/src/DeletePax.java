import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class DeletePax extends JFrame  implements ActionListener{

	String labelstr[]={"ONEWORLD AIRLINES","The world on wings!",
			"CANCEL YOUR TICKET HERE","CUSTOMER ID:",
			"FLIGHT NUMBER:","MOBILE NUMBER:"};
	JPanel p1,p2,p3,p4,p5;
	int i;
	JTextField t[];
	static String[] info=new String[3];
	JLabel lform[],con;
	JButton delete,home;

	public DeletePax(){
		super("BOOK YOUR FLIGHT");
		setSize(950,670);
		setVisible(true);


		//********CENTER LAYOUT********
		p2.setLayout(null);
		p2.setLayout(gl);

		lform=new JLabel[6];


		//BUTTON
		delete=new JButton("Cancel Booking");
		home=new JButton("Home");
		delete.setFont(fh1);
		home.setFont(fh1);
		JPanel rb=new JPanel();
		rb.setBackground(Color.white);
		GridLayout gb=new GridLayout(1,2,20,20);
		rb.setLayout(gb);
		rb.add(home);
		rb.add(delete);

		JPanel rdob=new JPanel();
		rdob.setBackground(Color.white);
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		for(i=0;i<3;i++){
			t[i].setBorder(border);
		}

		p2.add(lform[3]);
		p2.add(t[0]);
		p2.add(lform[4]);
		p2.add(t[1]);
		p2.add(lform[5]);
		p2.add(t[2]);
		p2.add(home);
		p2.add(delete);

		//******TOP LAYOUT*********
		p1.setLayout(gh);		
		p1.add(lform[0]);
		lform[0].setFont(fh);
		lform[0].setForeground(Color.yellow);
		lform[1].setForeground(Color.yellow);
		lform[2].setForeground(Color.WHITE);
		lform[1].setFont(fl);
		p1.add(lform[1]);
		lform[2].setFont(fl);
		p1.add(lform[2]);

		//Bottom layout
		con=new JLabel("For any queries Contact: oneworld@airline.com");
		con.setFont(fl);
		p3.add(con);
		home.addActionListener(this);
		delete.addActionListener(this);
	}

	@SuppressWarnings("resource")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==delete){

				MongoClient mongoClient = null;
				mongoClient = new MongoClient( "localhost",27017);
				@SuppressWarnings("deprecation")
				DB db = mongoClient.getDB("airline");
				DBCollection coll = db.getCollection("pax");

				BasicDBObject document = new BasicDBObject();
				document.put("cust_id",info[0]);
				document.put("flight_no", info[1]);
				document.put("mob_no", info[2]);
				DBCursor cursor = coll.find(document);
				if(cursor.hasNext()){
					coll.remove(document);
					JOptionPane.showMessageDialog(this,"BOOKING CANCELED SUCCESSFULLY!!");
					this.dispose();
		
			}
		}
		if(e.getSource()==home){
			this.dispose();
			new Index();
		}
	}

	public static void main(String str[]) {
		new DeletePax();
	}
}
