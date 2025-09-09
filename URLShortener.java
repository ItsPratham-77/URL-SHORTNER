import java.util.Random;
import java.util.Scanner;

public class URLShortener {
    private static final String BASE_URL = "http://short.ly/";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    private Random random = new Random();
    private URLShortenerDAO dao = new URLShortenerDAO();

    private String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return code.toString();
    }

    public String shortenURL(String longURL) {
        String code = generateCode();
        dao.saveURL(code, longURL);
        return BASE_URL + code;
    }

    public String getOriginalURL(String shortURL) {
        String code = shortURL.replace(BASE_URL, "");
        return dao.getLongURL(code);
    }

    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- URL Shortener (with JDBC) ---");
            System.out.println("1. Shorten URL");
            System.out.println("2. Get Original URL");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter long URL: ");
                    String longURL = sc.nextLine();
                    String shortURL = shortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                }
                case 2 -> {
                    System.out.print("Enter short URL: ");
                    String shortURL = sc.nextLine();
                    String longURL = shortener.getOriginalURL(shortURL);
                    if (longURL != null) {
                        System.out.println("Original URL: " + longURL);
                    } else {
                        System.out.println("URL not found!");
                    }
                }
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
