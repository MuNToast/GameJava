public class Level {
    private int level;
    private double difficulty;

    public Level(int level, double difficulty){
        this.level = 0;
        this.difficulty = difficulty;

    } 

    public void increaseLevel(){
        level++;
    }

    public void increaseDifficulty(){
        difficulty += 0.05;
    }

    public int getLevel(){
        return level;
    }

    public double getDifficulty(){
        return difficulty;
    }
}