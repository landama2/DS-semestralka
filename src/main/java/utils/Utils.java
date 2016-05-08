package utils;

import javax.swing.*;

/**
 * Created by urban on 5/8/2016.
 */
public class Utils {
    public static String extractString (JTextField s) {
        String extractedString = s.getText().trim();
        if (extractedString.equals("")) {
            return null;
        } else {
            return extractedString;
        }
    }
}
