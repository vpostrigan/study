package extractor;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TextExtractionPoiTest {
    private static final Log log = LogFactory.getLog(TextExtractionPoiTest.class);

    @Test
    public void testGetText() throws Exception {
        try (InputStream is = this.getClass().getResourceAsStream("CMBARR_Civilian of the Quarter Instructions.doc")) {
            List<String> result = TextExtractionPoi.extractText(is);

            String expected = getResourceAsString(this.getClass(), "CMBARR_Civilian of the Quarter Instructions.doc", StandardCharsets.UTF_8.name());
            Assertions.assertEquals(expected, result);
        }
    }

    public static String getResourceAsString(Class<?> clazz, String fileName, String encoding) {
        try (InputStream is = clazz.getResourceAsStream(fileName);) {
            return IOUtils.toString(is, encoding);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
