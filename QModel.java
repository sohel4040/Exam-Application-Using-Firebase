public class QModel
{
    public String question;
    public String op1,op2,op3,op4;
    public String correct;
    public QModel()
    {

    }
    public QModel(String question,String op1,String op2,String op3,String op4,String correct)
    {
        this.question= question;
        this.op1= op1;
        this.op2= op2;
        this.op3= op3;
        this.op4= op4;
        this.correct= correct;
    }
    public void setQuestion(String question)
    {
        this.question= question;
    }
    public void setOp1(String op1)
    {
        this.op1= op1;
    }
    public void setOp2(String op2)
    {
        this.op2= op2;
    }
    public void setOp3(String op3)
    {
        this.op3= op3;
    }
    public void setOp4(String op4)
    {
        this.op4= op4;
    }
    public void setCorrect(String correct)
    {
        this.correct= correct;
    }

    public String getQuestion()
    {
        return question;
    }
    public String getOp1()
    {
        return op1;
    }
    public String getOp2()
    {
        return op2;
    }
    public String getOp3()
    {
        return op3;
    }
    public String getOp4()
    {
        return op4;
    }
    public String getCorrect()
    {
        return correct;
    }


}