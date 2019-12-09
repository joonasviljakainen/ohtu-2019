package statistics.matcher;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private List<Matcher> matchers;

    public QueryBuilder () {
        this.matchers = new ArrayList<>();
    }

    public QueryBuilder all() {
        this.matchers.add(new All());
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }

    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }

    public Matcher build() {
        if (matchers.size() == 0) {
            this.all();
        }
        Matcher[] matcherArray = new Matcher[matchers.size()];
        for (int i = 0; i < matchers.size(); i++) {
            matcherArray[i] = matchers.get(i);
        }
        matchers.clear();
        return new And(matcherArray);
    }
}