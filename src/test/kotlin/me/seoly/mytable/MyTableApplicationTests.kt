package me.seoly.mytable

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local")
class MyTableApplicationTests {

    @Test
    @DisplayName("sample test")
    fun sampleTest() {
        assertThat(1 + 1).isEqualTo(2)
    }

}
