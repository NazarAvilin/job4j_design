package ru.job4j.collections.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MemStoreTest {

    @Test
    public void whenAddElementsThanFindById() {
        MemStore<Base> mem = new MemStore<>();
        Base pc = new Base("PC");
        Base lt = new Base("Laptop");
        Base tb = new Base("Tablet");
        mem.add(pc);
        mem.add(lt);
        mem.add(tb);
        assertThat(mem.findById("PC"), is(pc));
    }

    @Test
    public void whenAddTwoRolesAndReplaceFirstThenFindById() {
        MemStore<Base> mem = new MemStore<>();
        Base base1 = new Base("123");
        Base base2 = new Base("456");
        Base base3 = new Base("789");
        mem.add(base1);
        mem.add(base2);
        mem.replace("123", base3);
        assertThat(mem.findById("789"), is(base3));
        assertThat(mem.findById("456"), is(base2));
        assertNull(mem.findById("123"));
    }

    @Test
    public void whenAddRoleAndDeleteThenNullResultOfFindById() {
        MemStore<Base> mem = new MemStore<>();
        mem.add(new Base("123"));
        mem.delete("123");
        assertNull(mem.findById("123"));
    }

}