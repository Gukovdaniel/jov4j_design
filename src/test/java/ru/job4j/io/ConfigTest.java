package ru.job4j.io;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Daniil");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "data/appCom.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Alex");
    }

    @Test
    void whenPairWithErr() {
        String path = "data/appErr.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(()-> config.value("name")).isInstanceOf(IllegalArgumentException.class);
    }
}