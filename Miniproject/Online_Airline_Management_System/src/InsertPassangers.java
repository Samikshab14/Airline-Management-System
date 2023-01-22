import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertPassangers extends JFrame  implements ItemListener, ActionListener{

	String labelstr[]={"ONEWORLD AIRLINES","The world on wings!",
			"BOOK YOUR FLIGHT HERE","CUSTOMER ID:",
			"FULL NAME:","MOBILE NUMBER:","ADDRESS:",
			"FLIGHT NUMBER:","STATUS:","FLIGHT DATE","AMOUNT:"};
	String city[]={"101","102","103","104","105"};
	String dm[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
	CheckboxGroup c=new CheckboxGroup();
	Checkbox c1=new Checkbox("BUSINESS",c,false);
	Checkbox c2=new Checkbox("ECONOMY",c,false);

	public InsertPassangers(){
		super("BOOK YOUR FLIGHT");
		setSize(950,670);
		setVisible(true);

		//********CENTER LAYOUT********
		p2.setLayout(null);
		p2.setLayout(gl);

		lform=new JLabel[11];
		for(i=0;i<lform.length;i++){
			if(i<3){
				lform[i]=new JLabel(labelstr[i],JLabel.CENTER);
			}
			else{			
				lform[i]=new JLabel(labelstr[i]);
			}			
			if(i>2){
				lform[i].setFont(fh1);
			}		
		}

		//mongo code
		MongoClient mongoClient = null;
		DBCursor cursor = null;
		try{    
			mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "airline" );
			DBCollection coll = db.getCollection("flight");
			flightnoarray = new ArrayList<String>();
			cursor = coll.find();
			while(cursor.hasNext()) {
				DBObject obj = cursor.next();

				flightnoarray.add((String)obj.get("flight_no"));

			}

			cursor.close(); 		
			mongoClient.close();
		}
		catch(Exception ex){

		}         // TODO add your handling code here:
		String[] flarray = Arrays.copyOf(flightnoarray.toArray(), flightnoarray.size(), String[].class);

		//text
		t=new JTextField[4];
		for(i=0;i<4;i++){
			t[i]=new JTextField(10);
			t[i].setFont(fh1);
		}

		arr = new JLabel("ARRIVALS");
		arr.setFont(fh1);
		
		dep = new JLabel("DEPARTURE");
		arrlabel = new JLabel("NaN");
		deplabel = new JLabel("NaN");
		arrlabel.setFont(fh1);
		dep.setFont(fh1);
		deplabel.setFont(fh1);

		d=new JComboBox<>(d1);
		m=new JComboBox<>(dm);
		y=new JComboBox<>(dy);

		d.setFont(fh1);
		m.setFont(fh1);
		y.setFont(fh1);
		flightno.setFont(fh1);

		JPanel rdob=new JPanel();
		rdob.setBackground(Color.white);
		GridLayout gdob=new GridLayout(1,3);
		rdob.setLayout(gdob);
		rdob.add(d);
		rdob.add(m);
		rdob.add(y);

		Border border = BorderFactory.createLineBorder(Color.black, 1);
		add.setBorder(border);
		for(i=0;i<4;i++){
			t[i].setBorder(border);
		}

		c1.setFont(fh1);
		c2.setFont(fh1);

		JPanel rd=new JPanel();
		rd.setBackground(Color.white);
		GridLayout gpnl=new GridLayout(1,2);
		rd.setLayout(gpnl);

		rd.add(c1);
		rd.add(c2);

		//BUTTON

		register=new JButton("SUBMIT");
		home=new JButton("Home");
		reset=new JButton("Reset");
		register.setFont(fh1);
		home.setFont(fh1);
		reset.setFont(fh1);

		JPanel rb1=new JPanel();
		rb1.setBackground(Color.white);
		GridLayout gb1=new GridLayout(1,3,10,50);
		rb1.setLayout(gb1);
		rb1.add(register);

		p2.add(lform[3]);
		p2.add(t[0]);
		p2.add(lform[4]);
		p2.add(t[1]);
		p2.add(lform[5]);
		p2.add(t[2]);
		p2.add(lform[6]);
		p2.add(add);
		p2.add(lform[7]);
		p2.add(flightno);
		p2.add(dep);
		p2.add(deplabel);
		p2.add(arr);
		p2.add(arrlabel);
		p2.add(lform[8]);

		p2.add(rd);
		p2.add(lform[9]);
		p2.add(rdob);
		p2.add(lform[10]);
		p2.add(t[3]);
		p2.add(rb);
		p2.add(rb1);

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
		p3.setLayout(null);
		FlowLayout fo=new FlowLayout();
		p3.setLayout(fo);
		con=new JLabel("For any queries Contact: oneworld@airline.com");
		con.setFont(fl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==register){
		
					MongoClient mongoClient = null;
					mongoClient = new MongoClient( "localhost",27017);
					DB db = mongoClient.getDB("airline");

					DBCollection coll = db.getCollection("pax");
					System.out.println("Collection Accessed");

					BasicDBObject doc = new BasicDBObject("cust_id", info[0]).append("name",info[1]).append("mob_no",info[2]).append("address",info[3]).append("flight_no",info[4]).append("status",info[5]).append("flight_date",info[6]+" "+info[7]+" "+info[8]).append("amount",info[9]);
					if(t[1].getText().equals("")){
						JOptionPane.showMessageDialog(this,"PLEASE ENTER CUSTOMER ID","ERROR",JOptionPane.ERROR_MESSAGE);      
					}     
					coll.insert(doc);
					JOptionPane.showMessageDialog(this,"RECORD INSERTED SUCCESSFULLY!!!");
					this.dispose();
					new Index();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(this,"INSERTION FAILED!!!","MESSAGE",JOptionPane.ERROR_MESSAGE);  

				} 
				this.dispose();
				new Index();
				break;
			}
		}
		if(e.getSource()==reset){
			for(i=0;i<4;i++){
				t[i].setText("");
			}
			add.setText("");
		}
		if(e.getSource()==home){
			this.dispose();
			new Index();
		}
	}
	public void itemStateChanged(ItemEvent e){
		// if the state combobox is changed
		if (e.getSource() == flightno){
			//			System.out.println(flightno.getSelectedItem());
			try {
				MongoClient mongoClient = null;
				mongoClient = new MongoClient( "localhost",27017);
				DB db = mongoClient.getDB("airline");

				DBCollection coll = db.getCollection("flight");
				System.out.println("Collection established in changeitem");
				BasicDBObject search11 = new BasicDBObject();
				search11.put("flight_no",flightno.getSelectedItem());
				System.out.println(flightno.getSelectedItem());
					System.out.println("wdymm");
					DBObject search111=cursor11.next();
					System.out.println((String)search111.get("flight_arrival"));
					System.out.println((String)search111.get("flight_dept"));
					arrlabel.setText((String)search111.get("flight_arrival"));
					deplabel.setText((String)search111.get("flight_dept"));
			}
			catch(Exception e1){
				
			}
		}
	}

	public static void main(String str[]) {
		new InsertPassangers();
	}
}
