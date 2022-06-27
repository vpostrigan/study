package spark_in_action2021.streaming.lib;

import java.io.File;

/**
 * Series of utilities for streaming
 *
 * @author jgp
 */
public class StreamingUtils {

    private static StreamingUtils instance = null;

    private static StreamingUtils getInstance() {
        if (instance == null) {
            instance = new StreamingUtils();
        }
        return instance;
    }

    public static String getInputSubDirectory1() {
        return getInstance().inputSubDirectory1;
    }

    public static String getInputSubDirectory2() {
        return getInstance().inputSubDirectory2;
    }

    // //

    private String inputDirectory;
    private String inputDirectory2;
    private String inputSubDirectory1;
    private String inputSubDirectory2;

    private StreamingUtils() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            this.inputDirectory = "C:\\TEMP\\";
        } else {
            this.inputDirectory = System.getProperty("java.io.tmpdir") + File.separator;
        }
        this.inputDirectory += "streaming" + File.separator + "in" + File.separator;

        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            this.inputDirectory2 = "C:\\TEMP\\";
        } else {
            this.inputDirectory2 = System.getProperty("java.io.tmpdir") + File.separator;
        }
        this.inputDirectory2 += "streaming2" + File.separator + "in" + File.separator;


        this.inputSubDirectory1 += this.inputDirectory + File.separator + "s1" + File.separator;
        this.inputSubDirectory2 += this.inputDirectory + File.separator + "s2" + File.separator;

        createInputDirectory(this.inputDirectory);
        createInputDirectory(this.inputSubDirectory1);
        createInputDirectory(this.inputSubDirectory2);
    }

    public static boolean createInputDirectory() {
        return createInputDirectory(getInputDirectory());
    }

    private static boolean createInputDirectory(String directory) {
        File d = new File(directory);
        return d.mkdirs();
    }

    public static String getInputDirectory() {
        return getInstance().inputDirectory;
    }

    public static String getInputDirectory2() {
        return getInstance().inputDirectory2;
    }

}
