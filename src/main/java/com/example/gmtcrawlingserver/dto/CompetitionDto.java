package com.example.gmtcrawlingserver.dto;

import lombok.Data;

@Data
public class CompetitionDto {

    private String title;              // 대회 제목
    private String redirectLink;       // 대한 배드민턴 협회 경기 상세정보 링크
    private String acceptancePeriod;   // 접수 기간
    private String status;             // 상태 : 종료, 접수중, 진핸중
    private String posterImage;        // 대회 포스터
    private String subject;            // 주관
    private String place;              // 장소
}
