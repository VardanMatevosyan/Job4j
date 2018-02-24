package ru.matevosyan.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;

import java.io.IOException;

/**
 * ThroughPages class for iterate through pages.
 * created on 01.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class ThroughPages {
    private Elements elements;
    private int count;
    private String pageToConnect;
    private int size = 0;
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final Logger LOG = LoggerFactory.getLogger(ThroughPages.class.getName());
    private static final String CSS_SELECT_ALL_VACANCY = SETTINGS.getValue("css.selectorForOfferLinks");

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Constructor for ThroughPage class.
     * start count with 1 because of staying on the 1 page.
     */
    public ThroughPages() {
        this.pageToConnect = SETTINGS.getValue("sql.WebPage");
        this.count = 1;
    }

    /**
     * return true if has elements to return.
     * @return true or false.
     */
    public boolean hasNext() {
        boolean hasElement = false;

        if(size == 0) {
            this.elements = this.getAllVacancyInOnePage();
            LOG.debug("get all elements (vacancy) from page");
        }
        if(!(this.elements.isEmpty() && this.elements != null)) {
            hasElement = true;
        }
        if (this.elements.size() < 5) {
            hasElement = false;
        }
        return hasElement;
    }

    /**
     * Get the next element from elements.
     * @return element.
     */
    public Elements next() {

        if (this.count == 0) {
            try {
                Document document = Jsoup.connect(this.pageToConnect).get();
                this.elements = document.select(CSS_SELECT_ALL_VACANCY);
                if(!(this.elements.isEmpty())) {
                    return this.elements;
                }
                this.size = this.elements.size();
            } catch (IOException IOExp) {
                LOG.warn("WARN", IOExp);
            }
        } else {
            this.elements = this.getAllVacancyInOnePage();
            LOG.debug("get all elements (vacancy) from page");
        }
        return this.elements;
    }

    private Elements getAllVacancyInOnePage() {
        Elements elements = new Elements();
        try {
            this.pageToConnect = this.pageToConnect.substring(0, this.pageToConnect.length()) + "/" + count++;
            LOG.debug("Parsing page at this moment", this.pageToConnect);
            Document nextDocument = Jsoup.connect(this.pageToConnect).get();
            elements = nextDocument.select(CSS_SELECT_ALL_VACANCY);
            this.pageToConnect = this.pageToConnect.substring(0, this.pageToConnect.lastIndexOf("/"));
        } catch (IOException ioExp) {
          LOG.warn("Input or output problem in getting elements from page", ioExp);
        }
        return elements;
    }
}
