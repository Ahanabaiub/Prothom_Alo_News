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
import java.util.*;

@Component
public class ProthomAloService {

    @Autowired
    NewsRepo newsRepo;

    public void findNews()  {

        Set<News> news = new HashSet<>();

        news = collectNews("https://www.prothomalo.com/");

        //Set<String> categoryLinks = findCategoryLink(news);

        news.addAll(collectNews("https://www.prothomalo.com/collection/latest/"));

        news.addAll(collectNews("https://www.prothomalo.com/topic/%E0%A6%AC%E0%A6%BF%E0%A6%B6%E0%A7%87%E0%A6%B7-%E0%A6%B8%E0%A6%82%E0%A6%AC%E0%A6%BE%E0%A6%A6"));


        System.out.println("size "+news.size());

        System.out.println("---------generate link-----");
        List<String> lnks = generateLink("https://www.prothomalo.com/business/world-business/",new ArrayList<>());

        for (String s : lnks){
            System.out.println(s);
        }

        //......#########################


//        Document document = null;
//        Set<News> newsLinks = new HashSet<>();
//        try {
//
//            document = Jsoup.connect("https://www.prothomalo.com/collection/latest/").get();
//
//            //System.out.println(document);
//
//            Elements elements =  document.getElementsByTag("a");
//            System.out.println(elements.toArray().length);
//            for(Element e : elements){
//                if(e.attr("href").contains("https://www.prothomalo.com/") && e.attr("href").length()>27) {
//                    System.out.println(e.attr("href"));
//
//                }
//                //System.out.println(e.attr("href"));
//            }
//            System.out.println("unique.........");
//
//            for (News s : newsLinks){
//                System.out.println(s.getNewsLink());
//
//
//                //System.out.println(s.getNewsLink().substring(0,s.getNewsLink().lastIndexOf("/")));
//            }
//
//
//
//} catch (IOException e) {
//        e.printStackTrace();
//        }

        newsRepo.saveAll(news);

    }

    private Set<News> collectNews(String url){


        Document document = null;
        Set<News> newsLinks = new HashSet<>();
        try {

            document = Jsoup.connect(url).get();

            Elements elements =  document.getElementsByTag("a");
            //System.out.println(elements.toArray().length);

            for(Element e : elements){
                if(e.attr("href").contains("https://www.prothomalo.com/") && e.attr("href").length()>27) {
                    //System.out.println(e.attr("href"));

                    newsLinks.add(new News(e.attr("href"),new Date()));


                        //System.out.println(e.attr("href"));



                }
            }
            System.out.println("unique.........");

            for (News s : newsLinks){
                System.out.println(s.getNewsLink());


                //System.out.println(s.getNewsLink().substring(0,s.getNewsLink().lastIndexOf("/")));
            }

            System.out.println(newsLinks.size());



        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsLinks;

    }

    private Set<String> findCategoryLink(Set<News> links){
        Set<String> categoryLinks = new HashSet<>();

        for (News s : links)
        {
            categoryLinks.add(s.getNewsLink().substring(0,s.getNewsLink().lastIndexOf("/")));
            //s.getNewsLink().
        }


        return categoryLinks;
    }

    private List<String> generateLink(String str, List<String> links){

        if(str.length()<=28)
        {
            return links;
        }
        links.add(str.substring(0,str.lastIndexOf("/")));

        return generateLink(str.substring(0,str.lastIndexOf("/")),links);

    }




}
