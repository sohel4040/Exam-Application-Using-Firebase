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

public class Student extends JFrame implements ActionListener 
{   
    JPanel p1;
    JButton b1,b2,b3,b4;
    JTextField t2,t3;
    JLabel l1,l2,l3;
    String tnm="",time="",mpq="",title="",noq="";
    int marks=1;
    Firestore db;

    public Student() throws Exception
    {
        FBInit fbin=new FBInit();

        db=FirestoreClient.getFirestore();

        setSize(1200,900);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(3);
        setLocation(320,100);
        
        fetchData();

        Font f1=new Font("Rubik",Font.BOLD,30);

            l1 = new  JLabel(title);
            l1.setFont(f1);
            l1.setForeground(Color.decode("#83abeb"));
            l2 = new  JLabel("Name of Student");
            l3 = new  JLabel("Roll No");
    
            t2= new JTextField(40);
            t3= new JTextField(40);

             
           Font f2=new Font("Rubik",Font.BOLD,20);
           Font f3=new Font("Rubik",Font.BOLD,40);
           Font f4=new Font("Rubik",Font.BOLD,18);

            l2.setFont(f2);
            l3.setFont(f2);
            l1.setFont(f3);
            t2.setFont(f2);
            t3.setFont(f2);

            b1= new JButton("Start Test"){

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
            b2= new JButton("TestInfo"){
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
            b2.setBackground(Color.decode("#83abeb"));


            b3=new JButton("?"){
                public void paintComponent(Graphics g)
                {
                    g.setColor(getBackground());
                    g.fillRoundRect(0,0,getWidth(),getHeight(),100,100);
                    super.paintComponent(g);
                }
                protected void  paintBorder(Graphics g)
                {
                    g.setColor(Color.BLACK);
                    g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,100,100);

                }
                
            };
            b3.setOpaque(false);
            b3.setFocusable(false);
            b3.setContentAreaFilled(false);
            b3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    
                }
            });
            b2.setBackground(Color.decode("#83abeb"));

            b4=new JButton("i"){
                public void paintComponent(Graphics g)
                {
                    g.setColor(getBackground());
                    g.fillRoundRect(0,0,getWidth()-1,getHeight()-1,100,100);
                    super.paintComponent(g);
                }
                protected void  paintBorder(Graphics g)
                {
                    g.setColor(Color.BLACK);
                    g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,100,100);
                }    
            };
            b2.setBackground(Color.decode("#83abeb"));
            b4.setOpaque(false);
            b4.setFocusable(false);
            b4.setContentAreaFilled(false);

            b1.setFont(f4);
            b2.setFont(f4);
            b3.setFont(f4);
            b4.setFont(f4);





            p1=new JPanel();
            p1.setLayout(null);
            p1.setSize(1200,900);
            p1.setBackground(Color.decode("#29292b"));
            l1.setBounds(400,130,800,50);
            l2.setBounds(230,300,200,50);  
            l3.setBounds(230,450,200,50);
        
           
            l2.setForeground(Color.WHITE);
            l3.setForeground(Color.WHITE);

            t2.setBounds(520,300,400,50);
            t3.setBounds(520,450,400,50);
            

            p1.add(l1);  
            p1.add(l2);  p1.add(t2);
            p1.add(l3);  p1.add(t3); 
           
          

            b1.setBounds(700,620,300,50);
            b2.setBounds(150,630,300,50);
            b3.setBounds(950,750,50,50);
            b4.setBounds(1050,750,50,50);

            p1.add(b1);
            p1.add(b2);
            p1.add(b3);
            p1.add(b4);
            add(p1);
          
           b1.addActionListener(this);
           b2.addActionListener(this);
           b3.addActionListener(this);
           b4.addActionListener(this);
    }
    public void fetchData()
    {
        
        try{

             DocumentReference docRef = db.collection("Info").document("Test Info");
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot doc = future.get();
            if (doc.exists()) {
            HashMap<String,String> hm1=(HashMap)doc.getData();

            title=hm1.get("Title");
            tnm=hm1.get("Teacher");
            noq=hm1.get("NofQ");
            mpq=hm1.get("Marks");
            time=hm1.get("Time");
            }

            marks=Integer.parseInt(noq)*Integer.parseInt(mpq);
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void saveData()
    {
        try {

            HashMap <String , String> hm=new HashMap<>();
            hm.put("Name",t2.getText());
            hm.put("Roll",t3.getText());
            hm.put("Marks"," ");

            ApiFuture <WriteResult>  af=db.collection("Student").document(t3.getText()).set(hm);

        }catch(Exception e)
        {
            System.out.println("Exception Occured While inserting data into database in stud info: "+e);
        }
        try {
            QuestionStd std= new QuestionStd(t2.getText(),t3.getText());
        } catch (Exception e) {
           System.out.println(e);
        }
        
    }
    public void actionPerformed(ActionEvent ae)
    {

        if(ae.getSource()==b1)
        {
            b1.setEnabled(false);
            if(!(t2.getText().trim().equals("") || t3.getText().trim().equals("")))
            {
                saveData();
                setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(this,"All fields are required..!  :(");
                b1.setEnabled(true);
            }
        }
        else if(ae.getSource()==b2)
        {
        JOptionPane.showMessageDialog(this,"Test Info\n Title: "+title+"\n Teacher Name: "+tnm+"\n Number Of Questions: "+noq+"\n Total Marks: "+marks+"\n Marks Per Question: "+mpq+"\n Time: "+time+" min\n All the Best...!");
        }
        else if(ae.getSource()==b3)
        {
            JOptionPane.showMessageDialog(this,"\t1. You must submit test within time otherwise your test will  automatically closed.\n\t2. You should not switch between other app while attending exam otherwise it is considered as malpractice.\n\t3. If you switch other applications you will get warnings and after reaching specific limit you will blocked from server.\n\t4. You are being proctered by invigilator that means invigilator is able to view your all the recieved warnings.\n\t5. Your test score will be submitted to teacher immediately after submitting test.\n","Instructions",JOptionPane.INFORMATION_MESSAGE); 
        }
        else if(ae.getSource()==b4)
        {
             JOptionPane.showMessageDialog(this,"\tDeveloped by - \n\t\t\tSohel Bargir \n\t\t\tSatyajeet Gaikwad \n\t\t\tVijay Mali \n\n Contact Us -\n\t\t\t\t Email : examappgpm@gmail.com\n\t\t\t\t Mobile : 8080542518 / 9518582604 / 9657796937","About Us",JOptionPane.INFORMATION_MESSAGE);
        }
    } 
    public static void main(String []args) throws Exception
    {
        new Student();
    }

}
//Class for displaying Questions to Student

class QuestionStd extends JFrame implements ActionListener, ItemListener
{
      JPanel p2;
      JLabel l1,l2,l3;
      JButton b1,b2;
      JCheckBox o1,o2,o3,o4;
      ButtonGroup bg;
      ArrayList<QModel> array;
      ArrayList<AModel> array1;
      int qno=1;
      String correct,roll,name;
      int time,mpq=0,marks=0;
      Firestore db;
      int temp=1;
      boolean save=false;
      int warn=0;
      

     public QuestionStd(String nm,String r) throws Exception
     {
    
            addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                if (e.getOppositeWindow()==null) {
                if(warn<=10)
                {
                    JOptionPane.showMessageDialog(QuestionStd.this,"Malcious Behaviour detected...!","Warning",JOptionPane.WARNING_MESSAGE); 
                }
                }
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                warn++;
                if(warn==10)
                {
                    JOptionPane.showMessageDialog(QuestionStd.this,"You have reached the maximum limit of warnings..!","Warning",JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }  
            }
        });
           
            array= new ArrayList<QModel>();
            array1= new ArrayList<AModel>();
            roll=r;
            name=nm;
            db=FirestoreClient.getFirestore();
 
            //For fetch All Quetions from Ques
            ApiFuture<QuerySnapshot> future1 = db.collection("Ques").get();
            java.util.List<QueryDocumentSnapshot> documents = future1.get().getDocuments();    
            for (QueryDocumentSnapshot document : documents) {
            QModel om=document.toObject(QModel.class);
            array.add(om);
            }

            //For Marks and Time from Info
            DocumentReference docRef = db.collection("Info").document("Test Info");
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot doc = future.get();
            if (doc.exists()) {
            HashMap<String,String> hm1=(HashMap)doc.getData();
            //Marks per Que            
            mpq=Integer.parseInt(hm1.get("Marks"));
            //Time         
            time=Integer.parseInt(hm1.get("Time"));
            }
            l3 = new JLabel();

            //Timer
            Thread t=new Thread(){
                int hrs=time/60;
                int min=(time%60)-1;
                int sec=60;
                
                public void run()
                {
                    while(true)
                    {
                        sec--;
                        l3.setText("Time Remaining :-  "+hrs+":"+min+":"+sec);
                        if(hrs==0 && min<5)
                        {
                            l3.setForeground(Color.red);
                            if(sec%2==0)
                            l3.setVisible(true);
                            else
                            l3.setVisible(false);
                        }
                        if(sec==0)
                        {
                            if(hrs==0 && min==0)
                            {
                                JOptionPane.showMessageDialog(QuestionStd.this,"Time Over..!");
                                System.exit(0);
                            }
                            else if(min==0)
                            {
                                hrs--;
                                min=59;
                                sec=60;
                            }
                            else
                            {
                                min--;
                                sec=60;
                            }

                           
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            
                        }
                    }
                }
            };

            t.start();
            setSize(1200,900);
            setVisible(true);
            setLayout(null);
            setDefaultCloseOperation(3);
            setLocation(320,100);

            p2=new JPanel();
            p2.setLayout(null);
            p2.setSize(1200,900);
            p2.setVisible(true);
            p2.setBackground(Color.decode("#29292b"));
            add(p2);

            l1 = new JLabel();
            l2 = new JLabel();

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
            b1.setBackground(Color.decode("#83abeb"));
            b2.setBackground(Color.decode("#83abeb"));

            bg = new ButtonGroup();

            o1 = new JCheckBox();
            o2 = new JCheckBox(); 
            o3 = new JCheckBox();
            o4 = new JCheckBox();
            o1.setSelected(true);
            
            o1.addItemListener(this);
            o2.addItemListener(this);
            o3.addItemListener(this);        
            o4.addItemListener(this);    

            l3.setBounds(900,50,400,50);
            l1.setBounds(500,100,800,50);
            l2.setBounds(150,200,1000,70);
            o1.setBounds(300,300,500,50);   
            o2.setBounds(300,350,500,50);  
            o3.setBounds(300,400,500,50);   
            o4.setBounds(300,450,500,50);   
            b1.setBounds(100,750,300,50);  b2.setBounds(800,750,300,50);

            add(p2);

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

           Font f2=new Font("Rubik",Font.BOLD,20);
           Font f3=new Font("Rubik",Font.BOLD,40);
           Font f4=new Font("Rubik",Font.BOLD,16);
           Font f5=new Font("Rubik",Font.BOLD,23);


             l1.setFont(f3);
             l2.setFont(f5);
             l3.setFont(f4);
            l1.setForeground(Color.decode("#83abeb"));
            l2.setForeground(Color.WHITE);
            l3.setForeground(Color.WHITE);
           
                o1.setFont(f2);
                o2.setFont(f2);
                o3.setFont(f2);
                o4.setFont(f2);
                b1.setFont(f2);
                b2.setFont(f2);

                 o1.setBackground(Color.decode("#29292b"));
                 o2.setBackground(Color.decode("#29292b"));
                 o3.setBackground(Color.decode("#29292b"));
                 o4.setBackground(Color.decode("#29292b"));
                 o1.setForeground(Color.WHITE);
                 o2.setForeground(Color.WHITE);
                 o3.setForeground(Color.WHITE);
                 o4.setForeground(Color.WHITE);

            QModel om =array.get(0);
            l1.setText("Que No "+qno);
            l2.setText(om.getQuestion());
            o1.setText(om.getOp1());
            o2.setText(om.getOp2());
            o3.setText(om.getOp3());
            o4.setText(om.getOp4()); 
            b1.addActionListener(this);
            b2.addActionListener(this);
        }
        public void itemStateChanged(ItemEvent ie)
        {
            JCheckBox jb= (JCheckBox) ie.getItem();
            correct = jb.getLabel();
        }
        public void actionPerformed(ActionEvent ae)
        {   
            if(ae.getActionCommand().equals("Next") && qno<array.size())
            {
                if(o1.isSelected())
                {
                    correct=o1.getLabel();
                }
                qno++;

                 
                
                if(qno>=temp)
                {
                    if(!save)
                    {
                        AModel am=new AModel(l2.getText(),correct);
                        array1.add(am);
                        o1.setSelected(true); 
                        
                    }
                    temp=qno;
                }
                QModel om =array.get(qno-1);
                    l1.setText("Que No "+qno);
                    l2.setText(om.getQuestion());
                    o1.setText(om.getOp1());
                    o2.setText(om.getOp2());
                    o3.setText(om.getOp3());
                    o4.setText(om.getOp4()); 
                if(qno<temp){
                    

                AModel am=array1.get(qno-1);
                if(o1.getLabel().equals(am.getSelected()))
                {
                    o1.setSelected(true);
                }
                else if(o2.getLabel().equals(am.getSelected()))
                {
                    o2.setSelected(true);
                }
                else if(o3.getLabel().equals(am.getSelected()))
                {
                    o3.setSelected(true);
                }
                else if(o4.getLabel().equals(am.getSelected()))
                {
                    o4.setSelected(true);
                }
            }
            

                if(qno==(array.size()) && ae.getActionCommand().equals("Next"))
                {
                b2.setText("Submit");
                }
                else
                { 
                b2.setText("Next");
                }
            }
            else if(ae.getActionCommand().equals("Previous") && qno!=1)
            {
                
                qno--;  
                save=true;
                QModel om =array.get(qno-1);
                l1.setText("Que No "+qno);
                b2.setText("Next");
                l2.setText(om.getQuestion());
                o1.setText(om.getOp1());
                o2.setText(om.getOp2());
                o3.setText(om.getOp3());
                o4.setText(om.getOp4());    

                AModel am=array1.get(qno-1);
                if(o1.getLabel().equals(am.getSelected()))
                {
                    o1.setSelected(true);
                }
                else if(o2.getLabel().equals(am.getSelected()))
                {
                    o2.setSelected(true);
                }
                else if(o3.getLabel().equals(am.getSelected()))
                {
                    o3.setSelected(true);
                }
                else if(o4.getLabel().equals(am.getSelected()))
                {
                    o4.setSelected(true);
                }
  
            }
            else if(ae.getActionCommand().equals("Submit") && qno==array.size())
            {
                
                if(o1.isSelected())
                {
                    correct=o1.getLabel();
                }
                AModel am= new AModel(l2.getText(),correct);
                array1.add(am);
                int res = JOptionPane.showOptionDialog(this, " Do you want to submit test","Yes ? No",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);

                    
                        if(res==JOptionPane.YES_OPTION)
                        {
                            try {
                                assignMarks(); 

                                HashMap <String , String> hm=new HashMap<>();
                                 hm.put("Name",name);
                                 hm.put("Roll",roll);
                                 hm.put("Marks",""+marks);
                    
                                ApiFuture <WriteResult>  af=db.collection("Student").document(roll).set(hm);

                            } catch (Exception e) {
                                //TODO: handle exception
                            }
                            JOptionPane.showMessageDialog(this,"Test Submitted Successfully...");
                            setVisible(false);
                            System.exit(0);
                        }
                        if(res== JOptionPane.NO_OPTION)
                        { 
                            b2.setText("Submit");
                        }
            }  

        }
        public void assignMarks() throws Exception
        {
                    for(int i=0;i<array.size();i++)
                    {
    
                        AModel am= array1.get(i);
                        QModel om= array.get(i);
                        if(om.getCorrect().equals(am.getSelected()))
                        {
                            marks+=mpq;
                        }
                    }

        }

}