package utils;

public class ConsoleStyle {

    public static void printTitle(String title) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🌴 " + title);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public static void printSection(String label) {
        System.out.println("\n📌 " + label + ":");
    }

    public static void printSuccess(String message) {
        System.out.println("✅ " + message);
    }

    public static void printWarning(String message) {
        System.out.println("⚠️ " + message);
    }

    public static void printError(String message) {
        System.out.println("❌ " + message);
    }

    public static void printDivider() {
        System.out.println("──────────────────────────────");
    }
}
