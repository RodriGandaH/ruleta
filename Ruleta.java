import java.util.*;
public class Ruleta {
    private Random random;

    public Ruleta() {
        this.random = new Random();
    }

    public int girar() {
        return random.nextInt(37); 
    }
}