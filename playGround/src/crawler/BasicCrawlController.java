package crawler;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Integer.parseInt;

public class BasicCrawlController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger(BasicCrawlController.class);

    @ActionKey("/setupCraw")
    public void setupCraw() throws Exception {
        //String seed = getPara("seed");
        String seed = "https://bbs.hupu.com/all-gambia";
        int numberOfCrawlers = 1;
        //int numberOfCrawlers = parseInt(getPara("tnum"));

        if(seed == null || seed.length() == 0 || numberOfCrawlers == 0) {
            throw new Exception();
        }

        String crawlStorageFolder = "/home/wang/IdeaProjects/playGround/database";
        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(1000);
        config.setMaxDepthOfCrawling(4);
        config.setMaxPagesToFetch(500);
        config.setResumableCrawling(false);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed(seed);

        controller.start(BasicCrawler.class, numberOfCrawlers);
    }

}
