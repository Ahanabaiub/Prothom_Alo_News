package com.prothomAloNews.Service;

import com.prothomAloNews.Repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProthomAloService {

    @Autowired
    NewsRepo newsRepo;

    public void findNews(){

        System.out.println("Hello");


    }

}
