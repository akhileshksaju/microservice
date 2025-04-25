import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class a {

  public static void main(String[] args) {
    String b= "akhilesh";
    String[] names = {"apple","banana","apricot"};

    String[] name = b.split("");

    Map<String,Long> map = Arrays.stream(names).flatMap(n ->Arrays.stream(n.split("")))
                              .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
   System.out.println(Arrays.stream(name).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));

   System.out.println(map.toString());

  }

}
