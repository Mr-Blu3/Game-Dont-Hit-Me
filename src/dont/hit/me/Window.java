package dont.hit.me;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pontu on 2017-09-16.
 */
public class Window extends Canvas {

    private static final long serialVersUID = -2284879212465893870L;

    public Window(int width, int height, String title, Main main) {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(main);
        frame.setVisible(true);
        main.start();
    }
}
