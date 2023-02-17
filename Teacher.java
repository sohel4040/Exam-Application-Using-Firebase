import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import com.google.firebase.*;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class Teacher extends JFrame implements ActionListener
{   
    JPanel p;
    JButton b1,b2;
    String pass="gpmiraj@0131_co";
   
    public Teacher()  throws Exception
    {
        setSize(1200,900);
        setVisible(true);
        setDefaultCloseOperation(3);
        setLocation(280,100);
        String ipass=JOptionPane.showInputDialog(this,"Enter password of admin");      
        if(!(ipass.equals(pass)))
        {
            JOptionPane.showMessageDialog(this,"Wrong password","Warning",JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        Font f1=new Font("Rubik",Font.BOLD,40);

        p=new JPanel();
        p.setSize(1200,900);
        p.setBackground(Color.decode("#29292b"));
        // p.setBackground(Color.green);
        b1 = new JButton("Create Test"){
                public void paintComponent(Graphics g)
                {
                    g.setColor(getBackground());
                    g.fillRoundRect(0,0,getWidth(),getHeight(),50,50);
                    super.paintComponent(g);
                }
                protected void  paintBorder(Graphics g)
                {
                    g.setColor(Color.BLACK);
                    g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,50,50);

                } 
            };
            b1.setOpaque(false);
            b1.setFocusable(false);
            b1.setContentAreaFilled(false);
            b2 = new JButton("View Result"){
                public void paintComponent(Graphics g)
                {
                    g.setColor(getBackground());
                    g.fillRoundRect(0,0,getWidth(),getHeight(),50,50);
                    super.paintComponent(g);
                }
                protected void  paintBorder(Graphics g)
                {
                    g.setColor(Color.BLACK);
                    g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,50,50);

                }
                
            };
            b2.setOpaque(false);
            b2.setFocusable(false);
            b2.setContentAreaFilled(false);
        b1.setBounds(400,300,400,100);
        b2.setBounds(400,550,400,100);

        b1.setBackground(Color.decode("#83abeb"));
        
        b2.setBackground(Color.decode("#83abeb"));
        // b2.setBackground(Color.GRAY);
        b1.setFont(f1);
        b2.setFont(f1); 

        p.setLayout(null);
        p.add(b1);
        p.add(b2);
        
        add(p);
        b1.addActionListener(this);
        b2.addActionListener(this);


    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            try {
                Test te = new Test();
                setVisible(false);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if(ae.getSource()==b2)
        {
            
            try {
                Results te = new Results();
                setVisible(false);
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
    }
    public static void main(String args[])  throws Exception
    {
        Teacher t=new Teacher();
    }
}
class Results extends JFrame
{
    JTable jt;
    JScrollPane jsp;
    Firestore db;
    String data[][]=new String[100][100];
    int i;
    public Results()throws Exception
    {
        setSize(1200,900);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(3);
        setLocation(280,100);
        setBackground(Color.decode("#29292b"));
        FBInit fbin=new FBInit();

        db=FirestoreClient.getFirestore();

        String [] column={"Roll No.","Name of Student","Marks"};
					
        
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("Student").get();
        // future.get() blocks on response
        java.util.List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        i=0;
        for (QueryDocumentSnapshot document : documents) {
            SModel s=document.toObject(SModel.class);
            data[i][0]=s.getRoll();
            data[i][1]=s.getName();
            data[i][2]=s.getMarks();
            i++;
        }
        
        jt=new JTable(data,column);
        JScrollPane jp=new JScrollPane(jt);
		jt.setShowVerticalLines(true);
		jp.setBounds(100,100,1000,700);
		add(jp);
    }
}
