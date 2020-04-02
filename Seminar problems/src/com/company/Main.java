package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Ana", "are", "multe", "mere", "si", "pere");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);

        //1. Loop down the words and print each on a separate line,
        // with two spaces in front of each word. Don’t use map.
        words.forEach(word -> System.out.println(" " + word));

        //2. Repeat the previous problem, but without the two spaces in front.
        // This is trivial if you use the same approach as in P1,
        // so the point is to use a method reference here, as opposed to an explicit lambda as in P1.
        words.forEach(System.out :: println);

        //3. Produce the same lists as above, but this time use streams and the builtin “map” method.
        List<String> excitingWords = words.stream().map(w -> w + "!").collect(Collectors.toList());
        System.out.println("exciting " + excitingWords);
        List<String> eyeWords = words.stream().map(w -> w.replace("i", "eye")).collect(Collectors.toList());
        System.out.println("eyewords " + eyeWords);
        List<String> upperCaseWords = words.stream().map(String:: toUpperCase).collect(Collectors.toList());
        System.out.println("uppercasewords " + upperCaseWords);

        //4. Produce the same lists as above, but this time use “filter”.
        List<String> shortWords = words.stream().filter(s -> s.length() < 4).collect(Collectors.toList());
        System.out.println("shortwords " + shortWords);
        List<String> wordsWithB = words.stream().filter(s -> s.contains("b")).collect(Collectors.toList());
        System.out.println("words with b  " + wordsWithB);
        List<String> evenLengthWords = words.stream().filter(s -> (s.length() % 2 == 0)).collect(Collectors.toList());
        System.out.println("evenlength " + evenLengthWords);

        //5. Turn the strings into uppercase,
        // keep only the ones that are shorter than 4 characters,
        // of what is remaining, keep only the ones that contain “E”,
        // and print the first result.
        // Repeat the process, except checking for a “Q” instead of an “E”.
        // When checking for the “Q”, try to avoid repeating all the code from when you checked for an “E”.
        String res=
                words.stream().map(String::toUpperCase)
                        .filter(s -> s.length() < 4)
                        .filter(s -> s.contains("e"))
                        .findFirst().orElse("default Value");
        System.out.println("res " + res);

        //6.Produce a single String that is the result of concatenating
        // the uppercase versions of all of the Strings.
        // Use a single reduce operation, without using map.
        System.out.println(words.stream().reduce(" ", (s1, s2) -> s1 + s2.toUpperCase()));

        //7. Produce the same String as above,
        // but this time via a map operation that turns the words into uppercase,
        // followed by a reduce operation that concatenates them.
        System.out.println(words.stream().map(String::toUpperCase).reduce(" ", (s1, s2) -> s1 + s2));

        //8. Produce a String that is all the words concatenated together,
        // but with commas in between. E.g., the result should be "hi,hello,...".
        // Note that there is no comma at the beginning,
        // before “hi”, and also no comma at the end, after the last word.
        // Major hint: there are two versions of reduce discussed in the notes.
        System.out.println(words.stream().reduce((s1, s2) -> s1 + ", " + s2).orElse("default"));

        //9. Find the total number of characters (i.e., sum of the lengths) of the strings in the List.
        System.out.println(words.stream().map(s->s.length()).reduce(0, (s1, s2) -> s1 + s2));

        //10. Find the number of words that contain an “h”.
        System.out.println(words.stream().filter(s-> s.contains("h")).count());

        //a)keep only the numbers which are multiple of 3 or 2

        //b)transform each remaining number into a string that consist of prefix a, its successor and suffix B

        //c)concatenate all the strings

        String list = numbers.stream()
                        .filter(s -> (s % 5 == 0) || (s % 2 == 0)) // [4, 8, 12, 16]
                        .map(item -> "N" + String.valueOf(item + 1) + "R")
                        .reduce(" ", (s1, s2) -> s1 + " " + s2);

        System.out.println(list);
        //System.out.println(words.stream().map(String::toUpperCase).reduce(" ", (s1, s2) -> s1 + s2));
    }
}
