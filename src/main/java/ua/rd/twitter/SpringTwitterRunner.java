package ua.rd.twitter;

import java.util.Arrays;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.repository.TweetRepository;
import ua.rd.twitter.service.SimpleTweetService;
import ua.rd.twitter.service.TweetService;


public class SpringTwitterRunner {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext repoContext = 
                new ClassPathXmlApplicationContext(
                        new String[]{"repoContext.xml"}
                );
        
    ConfigurableEnvironment env = repoContext.getEnvironment();
    env.setActiveProfiles("test");
    repoContext.refresh();
        
        ConfigurableApplicationContext serviceContext = 
                new ClassPathXmlApplicationContext(
                        new String[]{"serviceContext.xml"},
                        repoContext
                );
        
        System.out.println("---Repo---");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));
        System.out.println("---Service---");
        System.out.println(Arrays.toString(serviceContext.getBeanDefinitionNames()));
//        
//        System.out.println(repoContext.getBeanFactory().getBeanDefinition("tempable"));
//        
        TweetService tweetService = 
                (TweetService) serviceContext.getBean("tweetService");
       
        tweetService.findAll().forEach(System.out::println);
               
        Tweet tweet = tweetService.createTweet("some text", null);
        System.out.println(tweet);
              
        repoContext.close();            
        serviceContext.close();
        
    }
}
