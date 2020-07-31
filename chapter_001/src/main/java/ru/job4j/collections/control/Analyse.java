package ru.job4j.collections.control;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Analyse {
    public Info diff(List<Analyse.User> previous, List<Analyse.User> current) {
        int added = 0;
        int changed = 0;
        int deleted;

        if (previous.size() == 0) {
            return new Info(current.size(), 0, 0);
        }

        if (current.size() == 0) {
            return new Info(0, 0, previous.size());
        }

        Map<Integer, User> prev = previous.stream().collect(
                Collectors.toMap(User::getId, Function.identity()));

        for (User user : current) {
            if (!prev.containsKey(user.getId())) {
                added++;
            } else {
                User temp = prev.get(user.getId());
                if (!user.getName().equals(temp.getName())) {
                    changed++;
                }
            }
        }
        deleted = previous.size() - (current.size() - added);
        return new Info(added, changed, deleted);
    }

    public static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
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
            return Objects.equals(id, user.id)
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        private final int added;
        private final int changed;
        private final int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
