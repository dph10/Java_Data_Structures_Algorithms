/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

import arrays.DoubleLinkedList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 *
 * @author daniel.holden.reg
 */
public abstract class ConnectedComponents {

    private final int numSites;

    protected int numComponents;

    protected ConnectedComponents(final int numSites) {
        this.numSites = numSites;
        this.numComponents = this.numSites;
    }

    public int getNumSites() {
        return numSites;
    }

    public int getNumComponents() {
        return this.numComponents;
    }

    public abstract int getComponent(final int siteNumber);

    public boolean connected(final int p, final int q) {
        return getComponent(p) == getComponent(q);
    }

    protected abstract void connectSitesInternal(final int p, final int q);

    public boolean connectSites(final int p, final int q) {
        if (!connected(p, q)) {
            this.connectSitesInternal(p, q);
            this.numComponents--;
            return true;
        }
        return false;
    }
    
    
    
    public static ConnectedComponents buildFromFile(final File file,
            final Function<Integer,ConnectedComponents> supplier) throws IOException {

        final List<String> lines = Files.lines(file.toPath()).collect(Collectors.toList());

        final var itr = lines.iterator();
        if (!itr.hasNext()) {
            return null;
        }
        final ConnectedComponents connections = supplier.apply(Integer.parseInt(itr.next()));

        while (itr.hasNext()) {
            final String[] pairs = itr.next().split(" ");
            connections.connectSites(Integer.parseInt(pairs[0]), Integer.parseInt(pairs[1]));
        }

        //System.out.println(connections.numComponents + " components");
        return connections;

    }

    public static class SimpleList extends ConnectedComponents {

        protected final List<Integer> siteComponentList;

        public SimpleList(final int numSites) {
            super(numSites);
            siteComponentList = new ArrayList<>(numSites);

            for (int ii = 0; ii < numSites; ii++) {
                siteComponentList.add(ii);
            }
        }

        @Override
        protected void connectSitesInternal(final int p, final int q) {

            final int newComponent = getComponent(p);
            final int oldComponent = getComponent(q);

            for (int ii = 0; ii < this.getNumSites(); ii++) {
                if (this.siteComponentList.get(ii) == oldComponent) {
                    this.siteComponentList.set(ii, newComponent);
                }
            }
        }

        @Override
        public int getComponent(int siteNumber) {
            return siteComponentList.get(siteNumber);
        }

    }

    public static class HashSets extends SimpleList {

        protected final Map<Integer, Set<Integer>> hashMap = new LinkedHashMap<>();

        public HashSets(final int numSites) {
            super(numSites);
        }

        @Override
        protected void connectSitesInternal(final int p, final int q) {

            final int component1 = getComponent(p);
            final int component2 = getComponent(q);

            final var componentSet2 = this.hashMap.get(component2);
            var componentSet1 = this.hashMap.get(component1);

            if (componentSet1 == null) {
                if (componentSet2 != null) {
                    // set 2 is not null
                    componentSet2.add(p);
                    this.siteComponentList.set(p, component2);
                } else {
                    // both are null
                    componentSet1 = new LinkedHashSet<>();
                    componentSet1.add(p);
                    this.hashMap.put(component1, componentSet1);
                    componentSet1.add(q);
                    this.siteComponentList.set(q, component1);

                }
            } else if (componentSet2 == null || componentSet1.size() > componentSet2.size()) {
                if (componentSet2 != null) {
                    componentSet1.addAll(componentSet2);
                    this.hashMap.remove(component2);
                    for (final var site : componentSet2) {
                        this.siteComponentList.set(site, component1);
                    }
                } else {
                    // componentSet2 is null
                    componentSet1.add(q);
                    this.siteComponentList.set(q, component1);
                }
            } else {
                componentSet2.addAll(componentSet1);
                this.hashMap.remove(component1);
                for (final var site : componentSet1) {
                    this.siteComponentList.set(site, component2);
                }
            }
        }
    }

    public static class LinkedLists extends SimpleList {

        protected final Map<Integer, DoubleLinkedList<Integer>> hashMap = 
                new LinkedHashMap<>();

        public LinkedLists(final int numSize) {
            super(numSize);
        }

        @Override
        protected void connectSitesInternal(final int p, final int q) {

            final int component1 = getComponent(p);
            final int component2 = getComponent(q);

            final var componentSet2 = this.hashMap.get(component2);
            var componentSet1 = this.hashMap.get(component1);

            if (componentSet1 == null) {
                if (componentSet2 != null) {
                    // set 2 is not null
                    componentSet2.add(p);
                    this.siteComponentList.set(p, component2);
                } else {
                    // both are null
                    componentSet1 = new DoubleLinkedList<>();
                    componentSet1.add(p);
                    this.hashMap.put(component1, componentSet1);
                    componentSet1.add(q);
                    this.siteComponentList.set(q, component1);

                }
            } else if (componentSet2 == null || componentSet1.size() > componentSet2.size()) {
                if (componentSet2 != null) {
                    componentSet1.appendLinkedList(componentSet2);
                    this.hashMap.remove(component2);
                    for (final var site : componentSet2) {
                        this.siteComponentList.set(site, component1);
                    }
                } else {
                    // componentSet2 is null
                    componentSet1.add(q);
                    this.siteComponentList.set(q, component1);
                }
            } else {
                componentSet2.appendLinkedList(componentSet1);
                this.hashMap.remove(component1);
                for (final var site : componentSet1) {
                    this.siteComponentList.set(site, component2);
                }
            }
        }
    }

}
