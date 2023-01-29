package assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four");
        assertThat(list).hasSize(4)
                .containsAnyOf("second", "five")
                .contains("second", Index.atIndex(1))
                .startsWith("first", "second")
                .doesNotContain("list", Index.atIndex(1));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Daniel", "Alexey", "Stas", "Petr", "Elena");
        assertThat(set).filteredOn(e -> e.length() < 5).first()
                .isEqualTo("Stas");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("entrance", "staircase", "door", "hallway", "room", "workplace");
        assertThat(map).hasSize(6)
                .containsKey("workplace")
                .doesNotContainKey("street")
                .containsEntry("entrance", 0);
    }
}
