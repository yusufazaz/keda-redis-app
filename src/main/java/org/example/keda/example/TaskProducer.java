package org.example.keda.example;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@Profile("producer")
public class TaskProducer {
  private final RedisTemplate<String, String> redisTemplate;

  public TaskProducer(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostMapping("/create")
  public String createTask(@RequestBody String task) {
    Long id = redisTemplate.opsForList().leftPush("task-queue", task);
    return "Task created! with id "+ id ;
  }

}
