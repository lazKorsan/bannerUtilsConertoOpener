package utilities;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class BannerUtils {
    private static final int BANNER_WIDTH = 600;
    private static final int BANNER_HEIGHT = 300;

    /**
     * Test adımı için açılır pencere gösterir (driver bağımsız).
     * @param title Pencere başlığı
     * @param message Mesaj (test adımı veya progress)
     * @param isModal true ise modal (engelleyici) pencere
     * @param backgroundColor Pencerenin arka plan rengi
     * @param durationMs Pencerenin ekranda kalma süresi (milisaniye, 0 ise kapanmaz)
     */
    public static void showStepBanner(String title, String message, boolean isModal, Color backgroundColor, int durationMs) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setModal(isModal);

        // Mesaj için JLabel
        JLabel label = new JLabel("<html><h2>" + message + "</h2></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Arka plan rengini ayarla
        dialog.getContentPane().setBackground(backgroundColor);
        label.setOpaque(true); // Label için opaklık
        label.setBackground(backgroundColor); // Aynı renk label için de geçerli

        dialog.add(label);

        // Boyut ve konum ayarla
        dialog.setSize(BANNER_WIDTH, BANNER_HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(
                (screenSize.width - BANNER_WIDTH) / 2,
                (screenSize.height - BANNER_HEIGHT) / 2
        );

        // Otomatik kapanma için timer (durationMs > 0 ise)
        if (durationMs > 0) {
            Timer timer = new Timer(durationMs, e -> dialog.dispose());
            timer.setRepeats(false); // Tek seferlik
            timer.start();
        }

        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Basit info popup (JOptionPane ile).
     * @param title Başlık
     * @param message Mesaj
     * @param backgroundColor Arka plan rengi
     * @param durationMs Kapanma süresi (milisaniye, 0 ise kapanmaz)
     */
    public static void showInfoPopup(String title, String message, Color backgroundColor, int durationMs) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setModal(false); // Non-modal, API test akışını kesmemek için

        JLabel label = new JLabel("<html><h2>" + message + "</h2></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(backgroundColor);
        dialog.getContentPane().setBackground(backgroundColor);

        dialog.add(label);
        dialog.setSize(BANNER_WIDTH, BANNER_HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(
                (screenSize.width - BANNER_WIDTH) / 2,
                (screenSize.height - BANNER_HEIGHT) / 2
        );

        if (durationMs > 0) {
            Timer timer = new Timer(durationMs, e -> dialog.dispose());
            timer.setRepeats(false);
            timer.start();
        }

        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Test progress'i için log-like banner.
     */
    public static void showProgressStep(String stepName, String status, Color backgroundColor, int durationMs) {
        String message = String.format("Adım: %s\nDurum: %s", stepName, status);
        showStepBanner("API Test Progress", message, false, backgroundColor, durationMs);
    }
}