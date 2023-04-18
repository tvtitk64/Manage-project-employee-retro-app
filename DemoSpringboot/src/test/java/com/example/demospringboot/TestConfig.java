package com.example.demospringboot;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
public class TestConfig {
    @TestConfiguration
    public static class Config {
        @Bean
        public TestSum testSum() {
            return new TestSum();
        }
    }

    @Autowired
    private TestSum testSum;

    @Test
    public void setTestSum() {
        int a = 10;
        int b = 20;
        int sum = testSum.add(a, b);
        assertThat(sum).isEqualTo(30);

    }


}
