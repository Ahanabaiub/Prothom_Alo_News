package com.prothomAloNews.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.prothomAloNews.Entity.News;
import com.prothomAloNews.Repository.NewsRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProthomAloService {

    @Autowired
    NewsRepo newsRepo;

    public void findNews()  {

        Set<News> news = new HashSet<>();

        news = collectNews("https://www.prothomalo.com/");

        newsRepo.saveAll(news);

    }

    private Set<News> collectNews(String url){


        Document document = null;
        Set<News> newsLinks = new HashSet<>();
        try {

            document = Jsoup.connect(url).get();

            Elements elements =  document.getElementsByTag("a");
            System.out.println(elements.toArray().length);

            for(Element e : elements){
                if(e.attr("href").contains("https://www.prothomalo.com/") && e.attr("href").length()>27) {
                    //System.out.println(e.attr("href"));

                    newsLinks.add(new News(e.attr("href"),new Date()));


                        System.out.println(e.attr("href"));



                }
            }
            System.out.println("unique.........");

            for (News s : newsLinks){
                System.out.println(s.getNewsLink());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsLinks;

    }




}
