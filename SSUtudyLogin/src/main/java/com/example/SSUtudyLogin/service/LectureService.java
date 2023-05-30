package com.example.SSUtudyLogin.service;

import com.example.SSUtudyLogin.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.SSUtudyLogin.DTO.LectureResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureService {
    @Transactional
   public LectureResponseDTO findLecture(){
       LectureResponseDTO lectureResponseDTO = new LectureResponseDTO();
        //크롬 드라이버 설정
        WebDriver driver = WebDriverUtil.getChromeDriver();
        List<WebElement> webElementList = new ArrayList<>();
        String url = "https://ecc.ssu.ac.kr/sap/bc/webdynpro/sap/zcmw2100?sap-language=KO#";
        String query = "//input[@id='WDFA'";

        if (!ObjectUtils.isEmpty(driver)) {
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

           // webElementList = driver.findElements(By.cssSelector(query));
        }

        // '자연과학대학' 텍스트를 포함하는 요소 찾기

        WebElement webElement = driver.findElement(By.id("WDFA-first"));
        List<WebElement> plants= webElement.findElements(By.className("lsListbox__value"));



        for (WebElement element: plants) {
            String text = element.getAttribute("textContent");

            lectureResponseDTO.getTextList().add(text);
           //#WDFA-aria
            //#WDFB-ariaItemValues
        }
        lectureResponseDTO.getTextList().add(webElement.getAttribute("textContent"));


        return lectureResponseDTO;

   }
}
