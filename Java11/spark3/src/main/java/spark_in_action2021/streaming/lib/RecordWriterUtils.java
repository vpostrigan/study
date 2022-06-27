package spark_in_action2021.streaming.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Writes a record to an output.
 *
 * @author jgp
 */
public abstract class RecordWriterUtils {
    private static final Logger log = LoggerFactory.getLogger(RecordWriterUtils.class);

    public static void write(String filename, StringBuilder record) {
        write(filename, record, StreamingUtils.getInputDirectory());
    }

    /**
     * Write a record to a file.
     */
    public static void write(String filename, StringBuilder record, String directory) {
        if (!directory.endsWith(File.separator)) {
            directory += File.separator;
        }
        String fullFilename = directory + filename;

        log.info("Writing in: {}", fullFilename);

        try ( // true tells to append data.
              FileWriter fstream = new FileWriter(fullFilename, true);
              BufferedWriter out = new BufferedWriter(fstream);) {
            out.write(record.toString());
        } catch (IOException e) {
            log.error("Error while writing: {}", e.getMessage());
        }
    }

}
