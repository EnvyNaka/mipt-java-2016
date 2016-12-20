package ru.mipt.java2016.homework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class Main {

    public static void main(String[] args) throws IOException{
        Map<String, Long> collect = Files.lines(Paths.get("input.txt"))
                .map(line -> line.split("[\\s]+"))
                .flatMap(Arrays::stream)
                .collect(groupingBy(e -> e, counting()))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> (o1.getValue() > o2.getValue() ? 0 : 1))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (o1, o2) -> (o1 > o2 ? o1 : o2), LinkedHashMap::new));

        collect.forEach((key, value) -> {System.out.println("wrod: \"" + key + "\" times: " + value);});
        Map<String, Integer> uniqueWordsAndCount = new HashMap<>();
        Scanner sc2 = new Scanner(new File("input.txt"));
        while (sc2.hasNextLine()) {
            Scanner s2 = new Scanner(sc2.nextLine());
            while (s2.hasNext()) {
                String s = s2.next();
                if (uniqueWordsAndCount.containsKey(s)) {
                    uniqueWordsAndCount.put(s, uniqueWordsAndCount.get(s) + 1);
                } else {
                    uniqueWordsAndCount.put(s, 1);
                }
            }
        }
        System.out.println(uniqueWordsAndCount);
    }
}