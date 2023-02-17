import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.sql.*;
import java.util.*;

import com.google.firebase.*;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.*;

public class Test extends JFrame implements ActionListener , AdjustmentListener
{   
    JPanel p1;
    JButton b1;
    JTextField t1,t2,t3,t5,th,tm;
    JLabel l1,l2,l3,l4,l5;
    Scrollbar sb,h,m;
    Firestore db;
     
    public Test()throws Exception
    {
        FBInit fbin=new FBInit();

        db=FirestoreClient.getFirestore();
        //Delete Test Info 
        ApiFuture<WriteResult> writeResult = db.collection("Info").document("1").delete();
        //Delete Student Records
         ApiFuture<QuerySnapshot> future1 = db.collection("Student").get();
            java.util.List<QueryDocumentSnapshot> documents = future1.get().getDocuments();    
            for (QueryDocumentSnapshot document : documents) {
                // ApiFuture<WriteResult> writeResult = db.collection("Ques").document(""+k).delete();
                // k++;

                document.getReference().delete();
            }

        setSize(1200,900);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(3);
        setLocation(280,100);

        Font f1=new Font("Rubik",Font.BOLD,40);
        
            l1 = new  JLabel("Title");
            l2 = new  JLabel("Teacher Name");
            l3 = new  JLabel("No of Que");
            l4 = new  JLabel("Marks per Que");
            l5 = new  JLabel("Time");
            t1= new JTextField(80);
    

            t2= new JTextField(40);
            t3= new JTextField(40);
            th= new JTextField(2);
            tm= new JTextField(2);
            t5= new JTextField(3);

// Design
        
            Font f2=new Font("Rubik",Font.BOLD,20);

            l1.setForeground(Color.WHITE);
            l2.setForeground(Color.WHITE);
            l3.setForeground(Color.WHITE);
            l4.setForeground(Color.WHITE);
            l5.setForeground(Color.WHITE);
           
            l1.setFont(f2);
            l2.setFont(f2);
            l3.setFont(f2);
            l4.setFont(f2);
            l5.setFont(f2);

            t1.setFont(f2);
            t2.setFont(f2);
            t3.setFont(f2);
            th.setFont(f2);
            tm.setFont(f2);
            t5.setFont(f2);
          
//\Design

            th.setEditable(false);
            tm.setEditable(false);
            t5.setEditable(false);
            t5.setText("1");

            th.setText("0");
            tm.setText("0");

            Font f4=new Font("Rubik",Font.BOLD,18);
            b1= new JButton("Next"){
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
            b1.setBackground(Color.decode("#83abeb"));
            b1.setFont(f4);
        

            sb=new Scrollbar(Scrollbar.VERTICAL,0,1,1,11);
            h=new Scrollbar(Scrollbar.VERTICAL,0,1,0,13);
            m=new Scrollbar(Scrollbar.VERTICAL,0,1,0,60);
            sb.setForeground(Color.decode("#83abeb"));
            
            p1=new JPanel();
            p1.setLayout(null);
            p1.setSize(1200,900);
            l1.setBounds(200,100,200,50);
            l2.setBounds(200,200,200,50);  
            l3.setBounds(200,300,200,50);
            l4.setBounds(200,400,200,50);
            l5.setBounds(200,500,200,50);
   
            t1.setBounds(500,100,400,50);
            t2.setBounds(500,200,200,50);
            t3.setBounds(500,300,200,50);
            t5.setBounds(550,400,70,45);
            sb.setBounds(620,400,40,43);
            th.setBounds(500,500,70,45);
            tm.setBounds(650,500,70,45);
            h.setBounds(570,500,40,43);
            m.setBounds(720,500,40,43);

            p1.add(l1);  p1.add(t1);
            p1.add(l2);  p1.add(t2);
            p1.add(l3);  p1.add(t3);
            p1.add(l4);  p1.add(t5);
             p1.add(sb);
            p1.add(l5);  p1.add(th);    p1.add(h);    p1.add(tm);     p1.add(m);

            p1.add(b1);
        
        //Design     
        p1.setBackground(Color.decode("#29292b"));

           b1.setBounds(430,700,300,50);
           add(p1);
     
           
           b1.addActionListener(this);
           sb.addAdjustmentListener(this);
           h.addAdjustmentListener(this);
           m.addAdjustmentListener(this);
    }
    public void adjustmentValueChanged(AdjustmentEvent ad)
    {
            t5.setText(""+sb.getValue());

            th.setText(""+h.getValue());
            tm.setText(""+m.getValue());

    }
    public void actionPerformed(ActionEvent ae)
    {
        int min;
        min=Integer.parseInt(th.getText())*60+Integer.parseInt(tm.getText());
        boolean q1= false;
        if(Integer.parseInt(t3.getText())<=1)
        {
            q1=true;
        }
        if(ae.getSource()==b1)
        {
            if(!(t1.getText().trim().equals("")  || t2.getText().trim().equals("") || t3.getText().trim().equals("")) && q1==false)
            {
                b1.setEnabled(false);
            try {
               
                HashMap <String , String> hm=new HashMap<>();
                hm.put("Title",""+t1.getText());
                hm.put("Teacher",""+t2.getText());
                hm.put("NofQ",""+t3.getText());
                hm.put("Marks",""+sb.getValue());
                hm.put("Time",""+min);

            
                ApiFuture <WriteResult>  af=db.collection("Info").document("Test Info").set(hm);
            
                Questions q=new Questions(Integer.parseInt(t3.getText()));
            
            }catch(Exception e)
            {
                System.out.println("Exception Occured While inserting data into database : "+e);
            }
               
               setVisible(false);
            }
            else
            {
            

               if(q1)
               JOptionPane.showMessageDialog(this,"No of Questions must be greater than 1...!");
               else
               JOptionPane.showMessageDialog(this,"Please fill all fields correctly...!");

               b1.setEnabled(true);
            }
        }
    }

   public static void main(String args[]) throws Exception
   {
       Test t=new Test();
   }
   
}
class Questions extends JFrame implements ActionListener, ItemListener
{
      JPanel p2;
      JTextField t1,t2,t3,t4,t5;
      JButton b1,b2;
      JLabel l1,l2,l3;
      JCheckBox o1,o2,o3,o4;
      ButtonGroup bg;
      ArrayList<QModel> array;
      int q;
      int qno=1;
      String correct;
      Firestore db;
    int temp;
    boolean save=false;
     public Questions(int q)
     {

        db=FirestoreClient.getFirestore();

            this.q=q;
            array= new ArrayList<QModel>();
            setSize(1200,900);
            setVisible(true);
            setLayout(null);
            setDefaultCloseOperation(3);
            setLocation(280,100);

            p2=new JPanel();
            p2.setLayout(null);
            p2.setSize(1200,900);
            p2.setVisible(true);
            
            p2.setBackground(Color.decode("#29292b"));

            t1 = new JTextField(400);
            t2 = new JTextField(70);
            t3 = new JTextField(70);
            t4 = new JTextField(70);
            t5 = new JTextField(70);

            l1 = new JLabel("Question No. "+qno);
            l2 = new JLabel("Question :"); 
            l3 = new JLabel("Options :"); 

            b1 = new JButton("Previous"){
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

            b2 = new JButton("Next"){
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

            bg = new ButtonGroup();


           Font f2=new Font("Rubik",Font.BOLD,20);
           Font f3=new Font("Rubik",Font.BOLD,40);
           Font f4=new Font("Rubik",Font.BOLD,18);

            l1.setForeground(Color.decode("#83abeb"));
            l2.setForeground(Color.WHITE);
            l3.setForeground(Color.WHITE);
            b1.setBackground(Color.decode("#83abeb"));
            b2.setBackground(Color.decode("#83abeb"));
            b1.setFont(f4);
            b2.setFont(f4);

            



            l1.setFont(f3);
            l2.setFont(f2);
            l3.setFont(f2);
           
            t1.setFont(f2);
            t2.setFont(f2);
            t3.setFont(f2);
            t4.setFont(f2);
            t5.setFont(f2);

            o1 = new JCheckBox();
            o2 = new JCheckBox(); 
            o3 = new JCheckBox();
            o4 = new JCheckBox();
            o1.setSelected(true);
            
            o1.addItemListener(this);
            o2.addItemListener(this);
            o3.addItemListener(this);        
            o4.addItemListener(this);    

             l1.setBounds(480,50,300,150);
             l2.setBounds(100,300,150,50);  t1.setBounds(270,300,800,50);
             l3.setBounds(100,400,100,50);      
             o1.setBounds(300,500,17,15);   t2.setBounds(350,500,500,40);
             o2.setBounds(300,550,17,15);   t3.setBounds(350,550,500,40);
             o3.setBounds(300,600,17,15);   t4.setBounds(350,600,500,40);
             o4.setBounds(300,650,17,15);   t5.setBounds(350,650,500,40);
             b1.setBounds(100,750,250,50);  b2.setBounds(800,750,250,50);

            add(p2);

            p2.add(t1);
            p2.add(t2);
            p2.add(t3);
            p2.add(t4);
            p2.add(t5);

            p2.add(l1);
            p2.add(l2);
            p2.add(l3);

            p2.add(b1);
            p2.add(b2);

            bg.add(o1);
            bg.add(o2);
            bg.add(o3);
            bg.add(o4);

            p2.add(o1);
            p2.add(o2);
            p2.add(o3);
            p2.add(o4);

            b1.addActionListener(this);
            b2.addActionListener(this);

        }
        public void itemStateChanged(ItemEvent ie)
        {
          if(ie.getSource()==o1)
          {
              correct= t2.getText();
          }
          else if(ie.getSource()==o2)
          {
              correct = t3.getText();
          }
          else if(ie.getSource()==o3)
          {
              correct = t4.getText();
          }
          else
          {
              correct = t5.getText();
          }
          
        }
        public void actionPerformed(ActionEvent ae)
        {
            
            if(qno==(q-1))
            {
                b2.setText("Release");
            }
            else
            { 
                b2.setText("Next");
            }

            if(ae.getActionCommand().equals("Previous") && qno!=1)
            {
                qno--;
                save=true;
                l1.setText("Quetion No. "+qno);
                QModel model= array.get(qno-1);
                t1.setText(model.getQuestion());
                t2.setText(model.getOp1());
                t3.setText(model.getOp2());
                t4.setText(model.getOp3());
                t5.setText(model.getOp4());
  
                String correct= model.getCorrect();
                if(t2.getText().equals(correct))
                {
                    o1.setSelected(true);
                }
                else if(t3.getText().equals(correct))
                {
                    o2.setSelected(true);
                }
                else if(t4.getText().equals(correct))
                {
                    o3.setSelected(true);
                }
                else
                {
                    o4.setSelected(true);
                }
                b2.setText("Next");
            }
            else if((!(t1.getText().equals("")) && !(t2.getText().equals("")) && !(t3.getText().equals(""))
            && !(t4.getText().equals(""))  && !(t5.getText().equals(""))))
            {
               
            if(ae.getActionCommand().equals("Next") && qno<q)
            {    
                if(o1.isSelected())
                {
                    correct=t2.getText();
                }
                qno++;
                l1.setText("Quetion No. "+qno);
                

                if(qno>=temp)
                {
                    if(!save)
                    {
                    QModel qw= new QModel(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),correct);
                    array.add(qw);
                    }
                    temp=qno;
                    save=false;
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    o1.setSelected(true);
                    o2.setSelected(false);
                    o3.setSelected(false);
                    o4.setSelected(false);     
                }
                else{
                    QModel model= array.get(qno-1);
                    t1.setText(model.getQuestion());
                    t2.setText(model.getOp1());
                    t3.setText(model.getOp2());
                    t4.setText(model.getOp3());
                    t5.setText(model.getOp4());
                }
            }
            else if(ae.getActionCommand().equals("Release"))
            {
                if(o1.isSelected())
                {
                    correct=t2.getText();
                }
                int res = JOptionPane.showOptionDialog(this, " Do you want to release test","Yes ? No",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);

                        if(res==JOptionPane.YES_OPTION)
                        {
                            QModel qw= new QModel(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),correct);
                array.add(qw);
                
                            b2.setText("");
                            try {
                                saveInDatabase(); 
                            } catch (Exception e) {
                                //TODO: handle exception
                            }
                            JOptionPane.showMessageDialog(this,"Test Created Successfully...");
                            setVisible(false);
                            System.exit(0);
                        }
                        if(res== JOptionPane.NO_OPTION)
                        { 
                            b2.setText("Release");
                        }
              
            }  
            
          }
          
          else
          {
            JOptionPane.showMessageDialog(this,"Please fill all fields properly..!");
          }    
          
        }

        public void saveInDatabase() throws Exception
        {
            //int k=1;
            ApiFuture<QuerySnapshot> future1 = db.collection("Ques").get();
            java.util.List<QueryDocumentSnapshot> documents = future1.get().getDocuments();    
            for (QueryDocumentSnapshot document : documents) {
                // ApiFuture<WriteResult> writeResult = db.collection("Ques").document(""+k).delete();
                // k++;

                document.getReference().delete();
            }



                for(int i=0;i<array.size();i++)
                {
                    QModel om= array.get(i);
                    try{
                    
                    ApiFuture <WriteResult> ap=db.collection("Ques").document(""+(i+1)).set(om);
                                
                }
                catch(Exception e)
                {
                    System.out.println("Exception Occured While inserting data into database : "+e);
                }
            }
                        
           
       }
}
