import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.lang.*;

public class streamss {
    public static void main(String []args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evenno = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(evenno);

        List<String>  list = Arrays.asList("Java","python","javascript");
        Optional<String> count = list.stream().max(Comparator.comparing(String::length));
        count.ifPresent(str1->System.out.println("longest string:"+str1));

        List<String>  list1 = Arrays.asList("Java","python","Java","javascript");
        List<String> comp = list1.stream().distinct().collect(Collectors.toList());
        System.out.println(comp);
    }
}
