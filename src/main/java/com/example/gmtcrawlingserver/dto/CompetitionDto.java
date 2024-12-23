package com.example.gmtcrawlingserver.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.StringTokenizer;


@ToString
public class CompetitionDto {
    @Setter
    @Getter
    private String title;              // 대회 제목

    @Setter
    @Getter
    private String redirectLink;       // 대한 배드민턴 협회 경기 상세정보 링크

    @Getter
    private String acceptancePeriod;   // 접수 기간

    @Setter
    @Getter
    private String status;             // 상태 : 종료, 접수중, 진핸중

    @Setter
    @Getter
    private String posterImageUrl;        // 대회 포스터

    @Getter
    private String subject;            // 주관

    @Getter
    private String place;              // 장소


    private String infoText;


    public void setInfoText(String infoText){
        this.infoText = infoText;

        StringTokenizer tokenizer = new StringTokenizer(infoText, "\n");

        while (tokenizer.hasMoreTokens()){
            String line = tokenizer.nextToken();
            String[] keyValue = line.split(":");
            String key = keyValue[0].trim(); // 키
            String value = keyValue[1].trim(); // 값

            if(key.equals("기간")){
                this.acceptancePeriod = value;
            } else if (key.equals("주관")) {
                this.subject = value;
            }else{
                this.place = value;
            }

        }
    }

}
