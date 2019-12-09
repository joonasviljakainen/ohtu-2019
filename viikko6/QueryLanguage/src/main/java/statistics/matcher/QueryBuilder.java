package statistics.matcher;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private List<Matcher> matchers;

    public QueryBuilder () {
        this.matchers = new ArrayList<>();
        this.matchers.add(new All());
    }

    public QueryBuilder all() {
        return this;
    }

    /*public QueryBuilder and() {
        return this;
    }*/

    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }

    /*public QueryBuilder not() {
        return this;
    }*/

    /*public QueryBuilder or() {
        return this;
    }*/

    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }

    public Matcher build() {
        Matcher[] matcherArray = new Matcher[matchers.size()];
        for (int i = 0; i < matchers.size(); i++) {
            matcherArray[i] = matchers.get(i);
        }
        //Matcher[] matcherArray = (Matcher[]) matchers.toArray();//ay().toArray(matchers);
        return new And(matcherArray);
    }
}