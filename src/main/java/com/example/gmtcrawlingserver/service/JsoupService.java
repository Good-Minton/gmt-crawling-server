package com.example.gmtcrawlingserver.service;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class JsoupService {
    private static final String url = "https://sfa.bkplay.kr/tournament/area/list.do?pageNo=1&pageRowCnt=10&provinceOrgId=&cityOrgId=&status=JI&searchStartDate=&searchEndDate=&sortType=&isFirstSearch=false";

    public void process() {
        Connection conn = Jsoup.connect(url);
        //Jsoup 커넥션 생성

        Document document = null;
        try {
            document = conn.get();
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> list = getDataList(document);
        System.out.println("===============출력=============");
        System.out.println(list);
    }


    private List<String> getDataList(Document document) {
        List<String> list = new ArrayList<>();
        log.info(document);
        return list;
    }
}
