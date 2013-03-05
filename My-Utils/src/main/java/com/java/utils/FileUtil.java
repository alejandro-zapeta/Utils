package com.java.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azapeta
 */
public class FileUtil {

    public static File createTemporalyFile(String str) {
        File f = null;
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            f = File.createTempFile("quotation", ".json");
            f.deleteOnExit();
            BufferedWriter out = new BufferedWriter(new FileWriter(f));
            out.write(str);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
}
