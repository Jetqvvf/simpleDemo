package crawler;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.Integer.parseInt;

public class BasicCrawlController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger(BasicCrawlController.class);
    private static CrawlController controller;
    private static BasicCrawler basicCrawler = new BasicCrawler();
    private static boolean started = false;

    @ActionKey("/setupCraw")
    public void setupCraw() throws Exception {
        String seed = getPara("seed");
        int numberOfCrawlers = parseInt(getPara("tnum"));
        int maxPage = parseInt(getPara("maxpage"));
        int maxDepth = parseInt(getPara("maxdepth"));

        if(seed == null || seed.length() == 0 || numberOfCrawlers == 0) {
            throw new Exception();
        }

        String crawlStorageFolder = "/home/wang/IdeaProjects/playGround/database";
        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(1000);
        config.setMaxDepthOfCrawling(maxDepth);
        config.setMaxPagesToFetch(maxPage);
        config.setResumableCrawling(false);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed(seed);

        started = true;
        controller.start(BasicCrawler.class, numberOfCrawlers);
        JSONObject mm = new JSONObject();
        renderJson(mm);
    }

    @ActionKey("/getCrawlstat")
    public void getCrawlstat() throws Exception {
        if(started) {
            CrawlStat stat =  (CrawlStat) basicCrawler.getMyLocalData();
           // List<Object> crawlersLocalData = controller.getCrawlersLocalData();
            long totalLinks = 0;
            long totalTextSize = 0;
            int totalProcessedPages = 0;
            int totalDownloadedPages = 0;
          /*  for (Object localData : crawlersLocalData) {
                CrawlStat stat = (CrawlStat) localData;*/
                totalLinks += stat.getTotalLinks();
                totalTextSize += stat.getTotalTextSize();
                totalProcessedPages += stat.getTotalProcessedPages();
                totalDownloadedPages += stat.getTotalDownloadedPages();
          //  }

            JSONObject mm = new JSONObject();
            mm.put("totalProcessedPages", totalProcessedPages);
            mm.put("totalDownloadedPages", totalDownloadedPages);
            mm.put("totalLinks", totalLinks);
            mm.put("totalTextSize", totalTextSize);
            renderJson(mm);
        }
    }
}
