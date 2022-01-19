package com.prothomAloNews.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
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
        Set<String> subLinks = new HashSet<>();

        //https://www.prothomalo.com/topic/%E0%A6%95%E0%A6%B0%E0%A7%8B%E0%A6%A8%E0%A6%BE%E0%A6%AD%E0%A6%BE%E0%A6%87%E0%A6%B0%E0%A6%BE%E0%A6%B8

         news = collectNews("https://www.prothomalo.com");


        //Set<String> categoryLinks = findCategoryLink(news);

        news.addAll(collectNews("https://www.prothomalo.com/collection/latest/"));

        news.addAll(collectNews("https://www.prothomalo.com/topic/%E0%A6%AC%E0%A6%BF%E0%A6%B6%E0%A7%87%E0%A6%B7-%E0%A6%B8%E0%A6%82%E0%A6%AC%E0%A6%BE%E0%A6%A6"));

        news.addAll(collectNews("https://www.prothomalo.com/topic/%E0%A6%95%E0%A6%B0%E0%A7%8B%E0%A6%A8%E0%A6%BE%E0%A6%AD%E0%A6%BE%E0%A6%87%E0%A6%B0%E0%A6%BE%E0%A6%B8"));


        Set<String> links;

        for(News n : news){
            links = generateLink(n.getNewsLink(),new HashSet<>());
            subLinks.addAll(links);
        }





        for (String s : subLinks){
            news.addAll(collectNews(s));
        }


        newsRepo.saveAll(news);



    }

    private Set<News> collectNews(String url){


        Document document = null;
        Set<News> newsLinks = new HashSet<>();
        try {

            document = Jsoup.connect(url).get();

            Elements elements =  document.getElementsByTag("a");
           

            for(Element e : elements){
                if(e.attr("href").contains("https://www.prothomalo.com/") && e.attr("href").length()>27) {


                    newsLinks.add(new News(e.attr("href"),new Date()));
                }
            }


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

        }


        return categoryLinks;
    }

    private Set<String> generateLink(String str, Set<String> links){

        if(str.length()<27)
        {
            return links;
        }

        String subString = str.substring(0,str.lastIndexOf("/"));

        if(subString.length()>26){
            links.add(subString);
        }

        return generateLink(str.substring(0,str.lastIndexOf("/")),links);

    }





}
