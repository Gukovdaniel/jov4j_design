package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.PREDICATE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> el;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
        el = new ArrayList<>(Arrays.asList(1));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemovePredicateLessTwo() {
        Predicate<Integer> remIf = f -> f < 2;
        ListUtils.removeIf(input, remIf);
        assertThat(input).contains(3);
    }

    @Test
    void whenReplacePredicateBigThanTwo() {
        Predicate<Integer> remIf = f -> f > 2;
        ListUtils.replaceIf(input, remIf, 7);
        assertThat(input).contains(7);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.removeAll(input, el);
        assertThat(input).hasSize(1);
    }
}