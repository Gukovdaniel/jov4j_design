package assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisTetrahedron() {
        Box box1 = new Box(4, 5);
        String name = box1.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box1 = new Box(8, 4);
        String name = box1.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void getNumberOfVerticesIsEight() {
        Box box1 = new Box(8, 3);
        int numberOfVertices = box1.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(8);
    }

    @Test
    void getNumberOfVerticesIsEven() {
        Box box1 = new Box(4, 2);
        int numberOfVertices = box1.getNumberOfVertices();
        assertThat(numberOfVertices).isEven();
    }

    @Test
    void isExist() {
        Box box = new Box(0, 7);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(1, 0);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void getAreaTetra() {
        Box box = new Box(4, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(6.93d, withPrecision(0.01d));
    }

    @Test
    void getAreaSphere() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(1256.63d, withPrecision(0.01d));
    }
}