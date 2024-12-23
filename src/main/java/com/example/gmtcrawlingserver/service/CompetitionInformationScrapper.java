package com.example.gmtcrawlingserver.service;

import com.example.gmtcrawlingserver.dto.CompetitionDto;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public class CompetitionInformationScrapper {

//    static {
//        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
//    }

    public void scrapKoreaBadmintonAssociation() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 필수 옵션
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-popup-blocking");//팝업 무시
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        try {
            // DTO List
            List<CompetitionDto> dtoList = new ArrayList<>();

            // 페이지 번호 반복 (1부터 30까지)
            for (int pageNo = 1; pageNo <= 5; pageNo++) {
                // URL 생성
                String url = "https://sfa.bkplay.kr/" +
                        "tournament/area/list.do?" +
                        "searchStartDate=&searchEndDate=" +
                        "&provinceOrgId=&cityOrgId=&status=&pageRowCnt=10" +
                        "&sortType=&isFirstSearch=false&pageNo=" + pageNo;

                // 페이지 로드
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get(url);


                List<WebElement> datas = driver.findElements(By.cssSelector("body > div.wrap > div.container > div.inner > div.sub-competition > ul.competition-list.type2 > li"));

                if (datas.isEmpty()) {
                    System.out.println("페이지 " + pageNo + "에서 요소를 찾을 수 없습니다.");
                } else {
                    System.out.println("페이지 " + pageNo + "에서 찾은 요소 개수: " + datas.size());

                    for (WebElement data : datas) {

                        CompetitionDto competitionDto = new CompetitionDto();

                        // 정보 추출
                        WebElement info = data.findElement(By.cssSelector("div > div.txt-cont"));
                        String infoText = info.getText();
                        competitionDto.setInfoText(infoText);


                        // redirectUrl 가져오기
                        WebElement linkElement = data.findElement(By.cssSelector("div > a"));
                        String href = linkElement.getAttribute("href");
                        competitionDto.setRedirectLink(href);

                        // 대회 상태 갖고오기
                        WebElement statusElement = data.findElement(By.cssSelector("div.poster"));
                        competitionDto.setStatus(statusElement.getText());

                        // 이미지 src 가져오기, 대회명 가져오기
                        WebElement imageElement = data.findElement(By.cssSelector("img"));
                        String imageSrc = imageElement.getAttribute("src");
                        String title = imageElement.getAttribute("alt");
                        competitionDto.setPosterImageUrl(imageSrc);
                        competitionDto.setTitle(title);


                        dtoList.add(competitionDto);

                    }
                }
            }

            dtoList.forEach(System.out::println);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit(); // WebDriver 종료
        }

    }



}
