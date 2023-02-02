package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsDaniel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Daniel"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Daniel");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Daniel"));
        Role result = store.findById("11");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsDaniel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Daniel"));
        store.add(new Role("4", "Petr"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Daniel");
    }

    @Test
    void whenReplaceThenMark() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Daniel"));
        store.replace("1", new Role("1", "Mark"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Mark");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Daniel"));
        store.replace("10", new Role("1", "Mark"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Daniel");
    }

    @Test
    void whenDeleteUserThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Daniel"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenUsernameIsDaniel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Daniel"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Daniel");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean rsl = store.replace("1", new Role("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean rsl = store.replace("10", new Role("1", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean rsl = store.delete("10");
        assertThat(rsl).isFalse();
    }
}