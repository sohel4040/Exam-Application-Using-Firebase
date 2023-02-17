public class SModel
{
    private String Name="";
    private String Roll="";
    private String Marks="";

    public SModel()
    {

    }
    public SModel(String Name, String Roll,String Marks)
    {
        this.Name=Name;
        this.Roll=Roll;
        this.Marks=Marks;
    }
    public void setName(String Name)
    {
        this.Name=Name;
    }
    public void setRoll(String Roll)
    {
        this.Roll=Roll;
    }
    public void setMarks(String Marks)
    {
        this.Marks=Marks;
    }
    public String getName()
    {
        return Name;
    }
    public String getRoll()
    {
        return Roll;
    }
    public String getMarks()
    {
        return Marks;
    }
}