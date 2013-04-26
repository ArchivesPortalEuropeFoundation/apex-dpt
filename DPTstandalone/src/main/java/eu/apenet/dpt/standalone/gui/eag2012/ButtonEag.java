package eu.apenet.dpt.standalone.gui.eag2012;

import javax.swing.*;
import java.awt.*;

/**
 * User: Yoann Moranville
 * Date: 05/12/2012
 *
 * @author Yoann Moranville
 */
public class ButtonEag extends JButton {
    public ButtonEag(String title, boolean translationBtn) {
        super(title);
        if(!translationBtn) {
            setBackground(new Color(97, 201, 237));
            setBorder(BorderFactory.createLineBorder(new Color(0, 162, 222), 3));
        } else {
            setBackground(new Color(120, 230, 250));
            setBorder(BorderFactory.createLineBorder(new Color(0, 190, 240), 3));
        }
        setPreferredSize(new Dimension(200, 25));
        setMinimumSize(new Dimension(200, 25));
        setToolTipText(title);
    }

    public ButtonEag(String title) {
        this(title, false);
    }
}
