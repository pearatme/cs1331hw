package test;

import java.util.*;

class Thing implements Comparable {
    String thing;

    Thing(String thing) {
        this.thing = thing;
    }

    void doubleThing() {
        thing = thing + ", " + thing;
    }

    String thingString() {
        return thing;
    }

    void print() {
        System.out.println(thing);
    }

    @Override
    public int compareTo(Object o) {
        Thing other = (Thing) o;
        return thing.length() - other.thing.length();
    }
}

public class FunctionalStuff {
    public static void main(String[] args) {
        List<Thing> things = new ArrayList<Thing>();
        things.add(new Thing("dog"));
        things.add(new Thing("alpaca"));
        things.add(new Thing("bird"));
        things.add(new Thing("cat"));

        things.forEach(Thing::print);

        System.out.println("");

        Comparator<Thing> thingComparator = Comparator.comparing(Thing::thingString);
        things.sort(thingComparator);
        things.forEach(Thing::print);

        System.out.println("");

        things.forEach(Thing::doubleThing);
        things.forEach(Thing::print);

        System.out.println("");

        Comparator<Thing> lengthComparator = Comparator.comparing(thing -> thing);
        things.sort(lengthComparator);
        things.forEach(Thing::print);

        System.out.println("");

        things.sort(thingComparator);
        Collections.sort(things);
        things.forEach(Thing::print);


    }
}
