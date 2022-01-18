package com.prothomAloNews.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.prothomAloNews.Repository.NewsRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ProthomAloService {

    @Autowired
    NewsRepo newsRepo;

    public void findNews()  {

        Document document = null;
        try {

            document = Jsoup.connect("https://www.prothomalo.com/").get();
//
//            System.out.println("Title: "+document.html());
//
//            Elements elements =  document.getElementsByClass("newsHeadline-m__title-link__1puEG");
//            System.out.println(elements.toArray().length);
//
//            for(Element e : elements){
//                System.out.println(e.attr("href"));
//            }
//            System.out.println(".......numbered Story.........");
//
//            elements =  document.getElementsByTag("a");
//            System.out.println(elements.toArray().length);
//            for(Element e : elements){
//                if(e.attr("href").contains("https://www.prothomalo.com/")) {
//                    //System.out.println(e.attr("href"));
//
//
//
//                    if(e.attr("href").matches(".*/bangladesh.*")){
//
//                        System.out.println(e.attr("href"));
//
//                    }
//                }
//            }

//            elements =  document.getElementsByClass("div.fourStoryCards-m__headline-wrapper__2AJeG");
//            System.out.println(elements.toArray().length);
//            for(Element e : elements){
//                System.out.println(e.attr("href"));
//            }
//
//            // elements =  document.getElementsByClass("numbered-story-headline.numberedStoryHeadline-m__wrapper__1i1wo");
//            elements =  document.select("body > div.container:nth-child(2) > div#container:nth-child(9) > div.home-m__home-wrapper__3tj-S:nth-child(2) > div.home-m__collection-container__3Isby > div > div > div:nth-child(2) > div.four-collection-12stories-1ad.fourCollection12Stories1Ad-m__base__QlGfT > div.fourCollection12Stories1Ad-m__collection-container__28uQg > div.bn-base.organism1-m__base__caj-b:nth-child(1) > div.numbered-story-headline-wrapper:nth-child(2) > a.numbered-story-headline.numberedStoryHeadline-m__wrapper__1i1wo:nth-child(1) > div.content.numbered-story-headline.numberedStoryHeadline-m__content__2Lmzu > h3.headline.headline-type-16.story-headline.numberedStoryHeadline-m__bn-story-headline__3g7DE.headline-m__headline__3vaq9.headline-m__headline-type-16__13Nx-:nth-child(2)");
//            System.out.println(elements.toArray().length);
//
//            for(Element e : elements){
//                System.out.println(e.text());
//            }
//
//            elements = document.select("#header > div > div > div > div > div.navbar-wrapper.staticHeader-m__navbar__1A8w1");
//            System.out.println(elements);

            //System.out.println(document);

//            PrintWriter out = new PrintWriter("output.txt");
//            out.print(document.toString());
//            out.close();

            document = Jsoup.connect("https://m.imdb.com/chart/top/").get();

            System.out.println(document.select(("#chart-content > div:nth-child(1)")));

            System.out.println();

            System.out.println(document.select(("#chart-content > div:nth-child(1) > div:nth-child(1) > div > span > a")));


        } catch (IOException e) {
            e.printStackTrace();
        }



//        WebClient client = new WebClient();
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
//
//
//        try {
//            HtmlPage page = client.getPage("https://www.prothomalo.com/");
//            System.out.println(page.asXml());
//            //System.out.println(page.getByXPath("//*[@id=\"container\"]/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/a[1]"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

}
