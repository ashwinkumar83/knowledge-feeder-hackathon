package com.ashzone.hackathon.knowledgefeeder.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentScraperService {
    @Autowired
    private Environment env;

    // We can enable the remote chrome driver if the scraping machine is in a remote server
    public Map<String,String> addDocuments(final String keyword) {
        Map<String,String> docMap = new HashMap<>();
        final String IEEE_URL =  env.getProperty("ieee.explore.url");

        //System.setProperty("webdriver.chrome.driver", "E:\\softwares\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get(IEEE_URL+keyword);
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> ! d.findElements(By.xpath("//xpl-results-item/div[1]/div[1]/div[2]/h3/a")).isEmpty() );
        List<WebElement> rows = driver
                .findElements(By.xpath("//xpl-results-item/div[1]/div[1]/div[2]/h3/a"));
        for (WebElement link : rows) {
            //  System.out.println(link.getText() + " - " + link.getAttribute("href"));
            //System.out.println(tr.getText());
            docMap.put(link.getText(),link.getAttribute("href"));
        }

        driver.quit();
        System.out.println(docMap) ;
        return docMap;
    }
}
