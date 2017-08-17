package users;

import com.jfinal.config.Routes;
import crawler.BasicCrawlController;

public class CrawlerRoute extends Routes {
    public void config(){
        add("/setupCraw", BasicCrawlController.class);
    }

}
