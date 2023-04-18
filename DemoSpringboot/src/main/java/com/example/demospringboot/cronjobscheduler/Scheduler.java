package com.example.demospringboot.cronjobscheduler;

import com.example.demospringboot.cronjobscheduler.interfaces.ChatRepository;
import com.example.demospringboot.cronjobscheduler.interfaces.ScheduleTimeConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Scheduler {
//    @Autowired
//    private final ChatRepository chatRepository;

    @Autowired
    ScheduleTimeConfigRepository scheduleTimeConfigRepository;

//    @Scheduled(fixedDelay = 1 * 1000 * 60)
//    public void postTime() {
//        chatRepository.save(new Chat("Bot", "Bây giờ là: " + java.time.LocalDateTime.now()));
//    }
//    @Scheduled(cron = "0 0 12 * * ?")
    public void postHaveLunch() {
//        chatRepository.save(new Chat("Bot", "Lets have lunch"));
        System.out.println("Bot: ++++++++++++ cron" + LocalDateTime.now());
    }

    public String getCronFromDatabase() {
        String cronjob = scheduleTimeConfigRepository.findAll().get(scheduleTimeConfigRepository.findAll().size()-1).getValue();
        System.out.println(cronjob + LocalDateTime.now());
        return cronjob;
    }
}



