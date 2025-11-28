import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingVisualizer extends JPanel {

    private int[] array;
    private int arraySize = 80;
    private int barWidth = 10;
    private int delay = 20; // speed of animation (ms)

    // Aesthetic baby blue theme
    private Color babyBlue = new Color(180, 220, 255);
    private Color softBlue = new Color(120, 170, 230);

    public SortingVisualizer() {
        array = new int[arraySize];
        generateArray();
        setPreferredSize(new Dimension(arraySize * barWidth, 400));
        setBackground(new Color(225, 240, 255)); // light baby blue
    }

    // Generate random array
    public void generateArray() {
        Random rand = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = rand.nextInt(350) + 20; 
        }
        repaint();
    }

    // Draw rounded bars
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < array.length; i++) {

            // gradient-style shading
            g2.setColor((i % 2 == 0) ? babyBlue : softBlue);

            int x = i * barWidth;
            int y = getHeight() - array[i];
            int width = barWidth - 2;
            int height = array[i];

            // Rounded rectangle bars 💙
            g2.fillRoundRect(x, y, width, height, 10, 10);
        }
    }

    // Bubble Sort animation
    public void bubbleSort() throws InterruptedException {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

                repaint();
                Thread.sleep(delay);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Sorting Visualizer 💙");
        SortingVisualizer visualizer = new SortingVisualizer();

        frame.add(visualizer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Thread.sleep(800); 
        visualizer.bubbleSort();
    }
}
