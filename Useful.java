import java.util.Random;

public class Useful {
    public static int getRandomNumber(int m, int n) {
        if (m > n) {
            throw new IllegalArgumentException("m should be less than or equal to n");
        }
        Random random = new Random();
        return random.nextInt((n - m) + 1) + m;
    }
}
