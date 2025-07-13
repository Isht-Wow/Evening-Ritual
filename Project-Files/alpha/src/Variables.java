public class Variables{ 
    //----------------------------------Variables----------------------------------------//

    public boolean[][] Rounds = new boolean[4][];
    public byte[] bulletSizes;
    public byte currentRound = 0;
    public byte[] Live = new byte [4];
    public byte[] Blank = new byte [4];
    public String selectedMenu = "0";
    public String selectedDifficulty = "0"; 
    public byte thought = 0;
    public int[] bulletCounts = new int[4];
    public byte decisionThreshold;  
    public boolean accuracy;
}
