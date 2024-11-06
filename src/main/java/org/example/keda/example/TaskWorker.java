package org.example.keda.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("worker")
public class TaskWorker implements CommandLineRunner{

  private final RedisTemplate<String, String> redisTemplate;

  public TaskWorker(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  @Override
  public void run(String... args) throws Exception {
    String task = redisTemplate.opsForList().rightPop("task-queue");
    if(task != null) {
      //Process long task
      System.out.println("Processing task: " + task);
    }   
    
  }
}
