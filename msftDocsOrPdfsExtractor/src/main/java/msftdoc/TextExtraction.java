package msftdoc;

import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hdgf.extractor.VisioTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.extractor.ExtractorFactory;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.xmlbeans.XmlException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TextExtraction {

    public static List<String> extractText(String inputFile) throws IOException, OpenXML4JException, XmlException {
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            return extractText(fis);
        }
    }

    public static List<String> extractText(InputStream fis) throws IOException, OpenXML4JException, XmlException {
        POIFSFileSystem fileSystem = new POIFSFileSystem(fis);

        // Firstly, get an extractor for the Workbook
        POIOLE2TextExtractor oleTextExtractor = ExtractorFactory.createExtractor(fileSystem);

        // Then a List of extractors for any embedded Excel, Word, PowerPoint
        // or Visio objects embedded into it.
        POITextExtractor[] embeddedExtractors = ExtractorFactory.getEmbeddedDocsTextExtractors(oleTextExtractor);

        List<String> results = new ArrayList<>();
        for (POITextExtractor textExtractor : embeddedExtractors) {
            // If the embedded object was an Excel spreadsheet.
            if (textExtractor instanceof ExcelExtractor) {
                ExcelExtractor excelExtractor = (ExcelExtractor) textExtractor;
                results.add(excelExtractor.getText());
            }
            // A Word Document
            else if (textExtractor instanceof WordExtractor) {
                results.add(textExtractor.getText());
            }
            // PowerPoint Presentation.
            else if (textExtractor instanceof SlideShowExtractor) {
                SlideShowExtractor powerPointExtractor = (SlideShowExtractor) textExtractor;
                results.add(powerPointExtractor.getText());
            }
            // Visio Drawing
            else if (textExtractor instanceof VisioTextExtractor) {
                VisioTextExtractor visioTextExtractor = (VisioTextExtractor) textExtractor;
                results.add(visioTextExtractor.getText());
            }
        }
        return results;
    }

}
