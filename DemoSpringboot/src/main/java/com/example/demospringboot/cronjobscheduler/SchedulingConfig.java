package com.example.demospringboot.cronjobscheduler;

import com.example.demospringboot.cronjobscheduler.service.ScheduleTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;


import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Configuration
//@EnableScheduling
@Slf4j
public class SchedulingConfig implements SchedulingConfigurer {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleTimeService scheduleTimeService;

    private String cronjob;

    private ScheduledFuture scheduledFuture;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(1);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolScheduler");
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
        scheduleFuture(taskScheduler());
    }

    public void refreshCronSchedule() {
        scheduledFuture.cancel(true);
        scheduleFuture(taskScheduler());
    }

    private void scheduleFuture(TaskScheduler taskScheduler) {
        scheduledFuture = taskScheduler().schedule(() -> scheduler.postHaveLunch(), new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                cronjob = scheduler.getCronFromDatabase();
                return new CronTrigger(cronjob).nextExecutionTime(triggerContext);
            }
        });
    }
}
