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

    public void test(){

//        Document document = null;
//        Set<News> newsLinks = new HashSet<>();
//        try {
//
//            document = Jsoup.connect("https://www.prothomalo.com/life/health").get();
//            System.out.println(document);
//
//            Elements elements =  document.getElementsByTag("a");
//            //System.out.println(elements.toArray().length);
//
//            for(Element e : elements){
//                if(e.attr("href").contains("https://www.prothomalo.com/") && e.attr("href").length()>27) {
//                    //System.out.println(e.attr("href"));
//
//                    newsLinks.add(new News(e.attr("href"),new Date()));
//
//
//                    //System.out.println(e.attr("href"));
//
//
//
//                }
//            }
//            System.out.println("unique.........");
//
//            for (News s : newsLinks){
//                System.out.println(s.getNewsLink());
//
//
//                //System.out.println(s.getNewsLink().substring(0,s.getNewsLink().lastIndexOf("/")));
//            }
////
////            System.out.println(newsLinks.size());
////
////
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }

        WebClient webClient = new WebClient();
        HtmlPage page;

        String url = "https://www.prothomalo.com/bangladesh";

        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        Set<String> l = new HashSet<>();
        try {
            page = webClient.getPage(url);
            DomNodeList<DomElement> nodes = page.getElementsByTagName("a");

            for (DomElement d : nodes){
                //System.out.println(d.getAttribute("href"));


                if(d.getAttribute("href").contains("https://www.prothomalo.com/") && d.getAttribute("href").length()>27) {
                    //System.out.println(e.attr("href"));

                    l.add(d.getAttribute("href"));


                    //System.out.println(e.attr("href"));



                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Test "+l.size());

        for (String s : l){
            System.out.println(s);
        }


    }

    public void findNews()  {

        Set<News> news = new HashSet<>();
        Set<String> subLinks = new HashSet<>();

        //https://www.prothomalo.com/topic/%E0%A6%95%E0%A6%B0%E0%A7%8B%E0%A6%A8%E0%A6%BE%E0%A6%AD%E0%A6%BE%E0%A6%87%E0%A6%B0%E0%A6%BE%E0%A6%B8

        news = collectNews("https://www.prothomalo.com/bangladesh");

//
//        //Set<String> categoryLinks = findCategoryLink(news);
//
//        news.addAll(collectNews("https://www.prothomalo.com/collection/latest/"));
//
//        news.addAll(collectNews("https://www.prothomalo.com/topic/%E0%A6%AC%E0%A6%BF%E0%A6%B6%E0%A7%87%E0%A6%B7-%E0%A6%B8%E0%A6%82%E0%A6%AC%E0%A6%BE%E0%A6%A6"));
//
//        news.addAll(collectNews("https://www.prothomalo.com/politics"));
//
//        news.addAll(collectNews("https://www.prothomalo.com/sports"));
//
//        news.addAll(collectNews("https://www.prothomalo.com/chakri"));
//
//        news.addAll(collectNews("https://www.prothomalo.com/lifestyle"));

        //news.addAll(collectNews("https://www.prothomalo.com/life/health"));




        //System.out.println("size "+news.size());

//        System.out.println("---------generate link-----");
//        subLinks = generateLink("https://www.prothomalo.com/business/world-business/",new HashSet<>());
//
//        for (String s : subLinks){
//            System.out.println(s);
//        }
//

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

    private Set<String> generateLink(String str, Set<String> links){

        if(str.length()<=28)
        {
            return links;
        }
        links.add(str.substring(0,str.lastIndexOf("/")));

        return generateLink(str.substring(0,str.lastIndexOf("/")),links);

    }




}
