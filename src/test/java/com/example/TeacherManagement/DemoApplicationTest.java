package com.example.TeacherManagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class DemoApplicationTest {
    @Test
    void testPassword() {
        String encrtypedPassWord = new BCryptPasswordEncoder().encode("123");
        System.out.println(encrtypedPassWord);
    }
}
