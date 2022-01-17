package com.prothomAloNews.Repository;

import com.prothomAloNews.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepo extends JpaRepository<News, Long> {

}
