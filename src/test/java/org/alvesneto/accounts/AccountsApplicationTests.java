package org.alvesneto.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
class AccountsApplicationTests {

    @Test
    void contextLoads() {
    }

}
