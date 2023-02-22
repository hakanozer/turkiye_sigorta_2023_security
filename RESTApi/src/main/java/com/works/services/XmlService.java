package com.works.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class XmlService {

    public void xml() {
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String stData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse(stData, Parser.xmlParser());
            Elements elements = doc.getElementsByTag("Currency");
            for(Element item : elements) {
                String CurrencyName = item.getElementsByTag("CurrencyName").text();
                String ForexBuying = item.getElementsByTag("ForexBuying").text();
                String ForexSelling = item.getElementsByTag("ForexSelling").text();
                System.out.println(CurrencyName + " " + ForexBuying + " " + ForexSelling );
            }
        }catch (Exception ex) {
            System.err.println("xml Error: " + ex);
        }
    }

}
