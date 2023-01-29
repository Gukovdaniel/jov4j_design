package assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("");
    }

    @Test
    void checkValidateContains() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> new NameLoad.Driver())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=");
    }

    @Test
    void checkValidateStartWith() {
    }

    @Test
    void checkValidateIndexOf() {
    }
}