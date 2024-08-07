package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Collections;

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

    public static String generatePassword(int length) {

        String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL;
    
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        for (int i = 3; i < length; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        for (int i = password.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(j));
            password.setCharAt(j, temp);
        }

        return password.toString();
    }
    
    public static boolean passwordIsValid(String password) {
        String pass = "(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9]).{8,}";
        return password.matches(pass);
    }

    public static boolean usernameIsValid(String username) {
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

    public static Integer getRandomNullIndex(Object[] array) {
        List<Integer> nullIndices = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                nullIndices.add(i);
            }
        }

        if (nullIndices.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(nullIndices.size());

        return nullIndices.get(randomIndex);
    }

    public static Integer getRandomNotNullIndex(Object[] array) {
        List<Integer> nullIndices = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                nullIndices.add(i);
            }
        }

        if (nullIndices.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(nullIndices.size());

        return nullIndices.get(randomIndex);
    }

    public static String[] getRandomElements(String[] array) {
        // Check if the array is null or has less than 20 elements
        if (array == null || array.length <= 20) {
            return array; // Return the original array if it has 20 or fewer elements
        }

        // Convert the array to a List for easier manipulation
        List<String> list = new ArrayList<>(List.of(array));

        // Shuffle the list to get random elements
        Collections.shuffle(list);

        // Select the first 20 elements from the shuffled list
        List<String> randomElements = list.subList(0, 20);

        // Convert the list back to an array
        return randomElements.toArray(new String[0]);
    }

    public static Integer findRandomIndex(Cell[] array, int m, int gap) {
        Random rand = new Random();
        int arrayLength = array.length;
        List<Integer> validIndices = new ArrayList<>();

        for (int i = 0; i <= arrayLength - m; i++) {
            boolean allNulls = true;

            for (int j = i; j < i + m; j++) {
                if (array[j] != null) {
                    allNulls = false;
                    break;
                }
            }

            if (allNulls) {
                validIndices.add(i);
            }
        }

        List<Integer> filteredIndices = new ArrayList<>();
        for (int index : validIndices) {
            boolean withinGap = false;
            for (int i = index + 1; i <= index + gap && i < arrayLength; i++) {
                if (array[i] != null) {
                    withinGap = true;
                    break;
                }
            }
            if (!withinGap) {
                filteredIndices.add(index);
            }
        }

        if (filteredIndices.isEmpty()) {
            return null;
        } else {
            return filteredIndices.get(rand.nextInt(filteredIndices.size()));
        }
    }
}
