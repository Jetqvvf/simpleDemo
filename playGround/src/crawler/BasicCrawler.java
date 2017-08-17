package crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.Header;

import java.io.File;
import java.io.FileWriter;
import java.util.Set;
import java.util.regex.Pattern;

public class BasicCrawler extends WebCrawler {
    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png|js)$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
            return false;
        }

        return true;
        //return href.startsWith("http://www.ics.uci.edu/");
    }

    @Override
    public void visit(Page page) {
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            String base = "/home/wang/IdeaProjects/playGround/database";
            FileWriter fw = null;
            try {
                fw = new FileWriter(base + page.getWebURL().getPath());
                System.out.print(base + page.getWebURL().getPath());
                fw.write(html);
                fw.close();
            }
            catch (Exception ex) {}
        }
    }
}