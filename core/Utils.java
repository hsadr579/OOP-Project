package core;

import java.util.Random;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Utils {
    public static int getRandomNumber(int m, int n) {
        if (m > n) {
            throw new IllegalArgumentException("m should be less than or equal to n");
        }
        Random random = new Random();
        return random.nextInt((n - m) + 1) + m;
    }

    public static String generateRandomString(int length) {
        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();

        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            randomString.append(characterSet.charAt(randomIndex));
        }

        return randomString.toString();
    }

    public static boolean passwordIsValid(String password)
    {
        String pass = "(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9]).{8,}";
        return password.matches(pass);
    }

    public static boolean usernameIsValid(String username)
    {
        return username.matches("[A-Za-z0-9]+");
    }
    
    public static boolean emailIsValid(String email) {
        return email.matches("\\w+@\\w+\\.com");
    }
    public static String convertStringToAsciiArt(String text) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("SansSerif", Font.PLAIN, 24);
        g2d.setFont(font);
        int width = g2d.getFontMetrics().stringWidth(text);
        int height = g2d.getFontMetrics().getHeight();
        g2d.dispose();
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        g2d.drawString(text, 0, g2d.getFontMetrics().getAscent());
        g2d.dispose();

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(img.getRGB(x, y) == 0xFFFFFFFF ? "@" : " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
