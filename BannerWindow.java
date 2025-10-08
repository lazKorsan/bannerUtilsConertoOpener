package utilities;

import javax.swing.*;
import java.awt.*;

/**
 * 🪟 Modern, testten bağımsız, ekran ortasında görsel banner.
 * Test sırasında çağrıldığında GUI thread'i ayrı çalışır, testi durdurmaz.
 */
public class BannerWindow {

    /**
     * Genel çağrı methodu.
     *
     * @param title       Üst başlık
     * @param message     İçerik metni
     * @param bgColor     Arka plan rengi
     * @param durationMs  Ekranda kalma süresi (ms). 0 => kalıcı
     */
    public static void show(String title, String message, Color bgColor, int durationMs) {
        // Test akışını etkilememesi için ayrı thread
        new Thread(() -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    JWindow window = new JWindow();
                    window.setAlwaysOnTop(true);
                    window.setFocusableWindowState(false);
                    window.setBackground(new Color(0, 0, 0, 0));

                    JPanel panel = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            Graphics2D g2 = (Graphics2D) g;
                            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            GradientPaint gp = new GradientPaint(0, 0, bgColor,
                                    getWidth(), getHeight(), bgColor.darker());
                            g2.setPaint(gp);
                            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                            super.paintComponent(g);
                        }
                    };
                    panel.setOpaque(false);
                    panel.setLayout(new BorderLayout(10, 10));

                    JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
                    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
                    titleLabel.setForeground(Color.WHITE);

                    JLabel messageLabel = new JLabel(
                            "<html><div style='text-align:center;'>" + message + "</div></html>",
                            SwingConstants.CENTER);
                    messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                    messageLabel.setForeground(new Color(240, 240, 240));

                    panel.add(titleLabel, BorderLayout.NORTH);
                    panel.add(messageLabel, BorderLayout.CENTER);
                    panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

                    window.add(panel);
                    window.setSize(520, 180);

                    // Ortala
                    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                    window.setLocation((screen.width - window.getWidth()) / 2,
                            (screen.height - window.getHeight()) / 2);

                    // Fade-in
                    new Thread(() -> fade(window, true)).start();

                    window.setVisible(true);

                    if (durationMs > 0) {
                        new Timer(durationMs, e -> new Thread(() -> fade(window, false)).start()).start();
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Banner gösterimi hatası: " + e.getMessage());
                }
            });
        }).start();
    }

    private static void fade(JWindow window, boolean fadeIn) {
        try {
            if (fadeIn) {
                for (float i = 0; i <= 1.0; i += 0.05) {
                    window.setOpacity(i);
                    Thread.sleep(25);
                }
            } else {
                for (float i = 1.0f; i >= 0; i -= 0.05f) {
                    window.setOpacity(i);
                    Thread.sleep(25);
                }
                window.dispose();
            }
        } catch (Exception ignored) {}
    }

    // ===== Kısa yardımcı metodlar (test içinde kolay çağırmak için) =====

    public static void info(String msg) {
        show("Bilgi", msg, new Color(52, 152, 219), 4000);
    }

    public static void step(String msg) {
        show("Adım", msg, new Color(26, 188, 156), 3500);
    }

    public static void success(String msg) {
        show("Başarılı", msg, new Color(39, 174, 96), 4000);
    }

    public static void warning(String msg) {
        show("Uyarı", msg, new Color(241, 196, 15), 4000);
    }

    public static void error(String msg) {
        show("Hata", msg, new Color(231, 76, 60), 4000);
    }
}
