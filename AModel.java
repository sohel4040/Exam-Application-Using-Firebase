public class AModel
{
    String question;
    String selected;
    public AModel(String question,String selected)
    {
        this.question=question;
        this.selected=selected;
    }
    public void setQuetion(String Quetion)
    {
        this.question=question;
    }
    public void setSelected(String selected)
    {
        this.selected=selected;
    }
    String getQuestion()
    {
        return question;
    }
    String getSelected()
    {
        return selected;
    }
}