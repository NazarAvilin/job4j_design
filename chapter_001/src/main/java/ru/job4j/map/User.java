package ru.job4j.map;

import java.util.*;

import static java.util.Objects.hash;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;



    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\'' + System.lineSeparator()
                + ", children=" + children + System.lineSeparator()
                + ", birthday=" + birthday
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
               && name.equals(user.name)
               && birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return hash(name, children, birthday);
    }


    public static void main(String[] args) {
        User first = new User("Nazar", 1, new GregorianCalendar(1988, Calendar.FEBRUARY, 23));
        User second = new User("Nazar", 1, new GregorianCalendar(1988, Calendar.FEBRUARY, 23));
        Map<User, Object> hashMap = new HashMap<>();
        hashMap.put(first, "First");
        hashMap.put(second, "Second");
        for (var el : hashMap.keySet()) {
            System.out.println(el.name);
        }
        System.out.println(Objects.equals(first, second));
        System.out.println(first.equals(second));
        System.out.println("-------------");
        HashMap map = new HashMap();
        Object o1 = "Nazar";
        Object o2 = "Nazar";
        map.put(o1, "First");
        map.put(o2, "second");
        for (var el : map.keySet()) {
            System.out.println(el);
        }
        System.out.println(Objects.equals(o1, o2));
        System.out.println(o1.equals(o2));

//        System.out.println(first.equals(second));
//        System.out.println(hash(first) == hash(second));
//        for (var el : hashMap.entrySet()) {
//            System.out.println(el);
//        }

//        Object obj = new Object();
//        obj = "Nazar";
//        System.out.println(obj.hashCode());
//        System.out.println();
//        Object[] o = new Object[]{"Nazar"};
//        System.out.println(hashCod(o));

    }
}
