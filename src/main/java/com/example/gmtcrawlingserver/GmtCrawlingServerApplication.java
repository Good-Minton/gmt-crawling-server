package com.example.gmtcrawlingserver;

import com.example.gmtcrawlingserver.service.CompetitionInformationScrapper;
import com.example.gmtcrawlingserver.service.JsoupService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GmtCrawlingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmtCrawlingServerApplication.class, args);
        CompetitionInformationScrapper competitionInformationScrapper
                = new CompetitionInformationScrapper();
        competitionInformationScrapper.scrapKoreaBadmintonAssociation();

//        JsoupService jsoupService = new JsoupService();
//        jsoupService.process();

        System.out.println("END");
    }

}
