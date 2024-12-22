package com.example.gmtcrawlingserver.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
            // URL로 이동
            String url = "https://sfa.bkplay.kr/" +
                    "tournament/area/list.do?" +
                    "searchStartDate=&searchEndDate=&provinceOrgId=&cityOrgId=&status=&pageRowCnt=10&sortType=&isFirstSearch=false&pageNo=1";
            // 페이지 로드 대기
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(url);
            List<WebElement> datas = driver.findElements(By.cssSelector("body > div.wrap > div.container > div.inner"));



            if (datas.isEmpty()) {
                System.out.println("요소를 찾을 수 없습니다.");
            } else {
                for (WebElement data : datas) {

                    
                    List<WebElement> infos = data.findElements(By.cssSelector("div.sub-competition > ul.competition-list.type2 > li > div > div.txt-cont "));
                    for (WebElement element : infos) {
                        String text = element.getText();
                        System.out.println("텍스트: " + text);
                    }
                    
                    

                    // redirectUrl 가져오기
                    List<WebElement> linkElements = data.findElements(By.cssSelector("div.sub-competition > ul.competition-list.type2 > li > div > a "));
                    for (WebElement element : linkElements) {
                        String href = element.getAttribute("href");
                        System.out.println("a 태그 링크: " + href);
                    }

                   // 이미지 src 가져오기
                    List<WebElement> imageElements = data.findElements(By.cssSelector("div.poster > img"));
                    for (WebElement image : imageElements) {
                        String imageSrc = image.getAttribute("src");
                        String title = image.getAttribute("alt");
                        System.out.println("이미지 src: " + imageSrc);
                        System.out.println("title : " + title);
                    }

                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit(); // WebDriver 종료
        }

    }



}
