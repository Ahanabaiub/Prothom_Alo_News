package com.prothomAloNews.Service;

import com.prothomAloNews.Entity.News;
import com.prothomAloNews.Repository.NewsRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.util.*;

@Component
public class ProthomAloService {

    @Autowired
    NewsRepo newsRepo;


    /*
        method for finding Prothom Alo news.
        some base url are provided based on that it will generate category link and find news on those link.
        Finally it will store all unique links to Database.
     */
    public void findNews()  {

        Set<News> news = new HashSet<>();
        Set<String> subLinks = new HashSet<>();

        news = collectNews("https://www.prothomalo.com");


        news.addAll(collectNews("https://www.prothomalo.com/collection/latest/"));

        news.addAll(collectNews("https://www.prothomalo.com/topic/%E0%A6%AC%E0%A6%BF%E0%A6%B6%E0%A7%87%E0%A6%B7-%E0%A6%B8%E0%A6%82%E0%A6%AC%E0%A6%BE%E0%A6%A6"));

        news.addAll(collectNews("https://www.prothomalo.com/topic/%E0%A6%95%E0%A6%B0%E0%A7%8B%E0%A6%A8%E0%A6%BE%E0%A6%AD%E0%A6%BE%E0%A6%87%E0%A6%B0%E0%A6%BE%E0%A6%B8"));

        Set<String> links;

        /*
            generate sub-links from previous links
         */
        for(News n : news){
            links = generateLink(n.getNewsLink(),new HashSet<>());
            subLinks.addAll(links);
        }


        /*
            searching news links on stored sub-links
         */
        for (String s : subLinks){
            news.addAll(collectNews(s));
        }

        System.out.println("..........News Links............");

        for(News n : news){
            System.out.println(n.getNewsLink());
        }


        newsRepo.saveAll(news);



    }

    /*
        search news links from given url
     */
    private Set<News> collectNews(String url){


        Document document = null;
        Set<News> newsLinks = new HashSet<>();
        try {

            // get dom from the url
            document = Jsoup.connect(url)
                    .header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:77.0) Gecko/20100101 Firefox/77.0")
                    .referrer("http://www.google.com")
                    .get();

            // filtering anchor tag from dom
            Elements elements =  document.getElementsByTag("a");


            for(Element e : elements){

                // check for a valid link
                if(e.attr("href").contains("https://www.prothomalo.com/") && e.attr("href").length()>27) {

                    String category=findNewsCategory(e.attr("href"));

                    newsLinks.add(new News(e.attr("href"),new Date(),category));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsLinks;

    }


    /*
        find news-link category
     */
    private String findNewsCategory(String url){
        String sub = url.substring(27);

        return sub.substring(0,sub.indexOf("/"));
    }

    /*
        recursively generate sub-link from a given link
     */
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
