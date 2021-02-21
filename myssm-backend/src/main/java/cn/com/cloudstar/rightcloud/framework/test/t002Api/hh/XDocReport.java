package cn.com.cloudstar.rightcloud.framework.test.t002Api.hh;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class XDocReport {

    public static void wordConverterToPdf(String tmp, String target, String fontParam1, String fontParam2) {
        InputStream sourceStream = null;
        OutputStream targetStream = null;
        XWPFDocument doc = null;
        try {
            sourceStream = new FileInputStream(tmp);
            targetStream = new FileOutputStream(target);
            doc = new XWPFDocument(sourceStream);
            PdfOptions options = PdfOptions.create();
            options.fontProvider(new IFontProvider() {
                public Font getFont(String familyName, String encoding, float size, int style, Color color) {
                    try {
                        BaseFont bfChinese = BaseFont.createFont(fontParam1, fontParam2, BaseFont.NOT_EMBEDDED);
                        Font fontChinese = new Font(bfChinese, size, style, color);
                        if (familyName != null)
                            fontChinese.setFamily(familyName);
                        return fontChinese;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            PdfConverter.getInstance().convert(doc, targetStream, options);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(doc);
            IOUtils.closeQuietly(targetStream);
            IOUtils.closeQuietly(sourceStream);
        }
    }
}