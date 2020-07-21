package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddTwoRolesThenFindById() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("123");
        Role role2 = new Role("456");
        roleStore.add(role1);
        roleStore.add(role2);
        assertThat(roleStore.findById("123"), is(role1));
        assertThat(roleStore.findById("456"), is(role2));
    }

    @Test
    public void whenAddTwoRolesAndReplaceFirstThenFindById() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("123");
        Role role2 = new Role("456");
        Role role3 = new Role("789");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.replace("123", role3);
        assertThat(roleStore.findById("789"), is(role3));
        assertThat(roleStore.findById("456"), is(role2));
        assertNull(roleStore.findById("123"));
    }

    @Test
    public void whenAddRoleAndDeleteThenNullResultOfFindById() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("123"));
        roleStore.delete("123");
        assertNull(roleStore.findById("123"));
    }
}