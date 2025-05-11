import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.UIDefaults.LazyInputMap;

public class a {




  private final int maxNumber =10;
  private int n=1;

  public static void main(String[] args) {
    String b= "akhilesh";
    String[] names = {"apple","banana","apricot"};

    String[] name = b.split("");

    Map<String,Long> map = Arrays.stream(names).flatMap(n ->Arrays.stream(n.split("")))
                              .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
  //  System.out.println(Arrays.stream(name).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));

  //  System.out.println(map.toString());

  String s = "i am learning streams API in java";

  // Optional<String> lon=Arrays.stream(s.split(" ")).max(Comparator.comparing(String::length));
  // System.out.println(lon);
  // List<String> li = Arrays.asList(s.split(" "));

  // String a = li.stream().max(Comparator.comparing(String::length)).get();

  // System.out.println(a);

  String a1 = "dabcadefg";

  // Arrays.stream(a1.split("")).distinct().forEach(System.out::println);

  // Arrays.stream(a1.split("")).distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);

  // 
  
  // String s1 = "Mississauga";

  // System.out.println(Arrays.stream(s1.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));

    int[] arr ={1,6,7,8,1,1,8,8,7};

    // System.out.println(Arrays.stream(arr).distinct().sum());

    String n = "Hello World";

    // System.out.println(Arrays.stream(n.split("")).filter(x->n.indexOf(x)==n.lastIndexOf(x)).findFirst().get());

  //  char charMap=s.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())).entrySet().stream().filter(x->x.getValue()==1).findFirst().get().getKey();

  //  System.out.println(charMap);

//   System.out.println(n.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
//                                   .entrySet()
//                                   .stream()
//                                   .filter(x->x.getValue()>1)
//                                   .map(x->x.getKey())
//                                   .findFirst()
//                                   .get()) ;

//    int[] values = {2,3,4,10,11,14,15,20,21,23,22,25,5,6,19,30,33,32,35,40,44,43};
//    List<Integer> list = Arrays.stream(values).boxed().collect(Collectors.toList());
//   Map<Integer,List<Integer>>  maps=list.stream()
//   .collect(
//     Collectors.groupingBy(
//       x->x/10*10,LinkedHashMap::new,Collectors
//       .collectingAndThen(
//         Collectors.toList(),
//          i->{i.sort(Comparator.naturalOrder());
//   return i;}
//   )));
// maps.forEach((k,v)->v.sort(Comparator.naturalOrder()));
//   System.out.println(maps);

// String[] strinAr = {"abc" , "123","345","rty"};

//     Arrays.stream(strinAr).filter(x->x.matches("[0-9]+")).map(Integer::valueOf).forEach(System.out::println);

// int[] values = {2,3,4,10,11,};

// Arrays.stream(null)

int[] arr1 = {1, 3, 4, 7, 10, 12};
        // int target = 15;

        // findClosestPair(arr1, target);

        // find all alternative product in an array
        // int ans =  IntStream.range(0, arr1.length).filter(x->x%2==0).map(x->arr1[x]).reduce(1, (g,h)->g*h);
        // System.out.println(ans);

        // IntStream.range(0, arr1.length/2).map(x->arr1[x]*arr1[arr1.length-x-1]).forEach(System.out::println); 
        
        int[] zan={0,5,0,0,3,0,4,0};

        // System.out.println(Arrays.stream(zan).boxed().collect(Collectors.partitioningBy(x->x==0)).values().stream().flatMap(x->x.stream()).collect(Collectors.toList()));

      //  System.out.println( Arrays.stream(zan).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
      //                             .values().stream().noneMatch(x->x>1));

        // IntStream.range(1, 7).map(x->x*x).forEach(System.out::println);
 
      List<Integer> list = List.of(1,1,2,3,4,5,3,6,7,2,6,4,9,5);

      // list.stream().filter(x->x%2!=0).distinct().forEach(System.out::println);

      // list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
      //                                 .entrySet()
      //                                 .stream()
      //                                 .filter(x->x.getKey()%2!=0)
      //                                 .filter(x->x.getValue()==1)
      //                                 .map(x->x.getKey())
      //                                 .forEach(System.out::println);

      // System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
      // .entrySet()
      // .stream()
      // .filter(x->x.getKey()%2!=0).collect(Collectors.toList()));

      // List<Integer> list3 = List.of(10,2,3,4,5);
      // List<Integer> list4 = List.of(5,6,7,8,9);

      // System.out.println(Stream.concat(list3.stream(), list4.stream()).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

      // var k=9;

      //   System.out.println(list3.stream().sorted().skip(k-1).findFirst().orElseThrow(()->new NoSuchElementException("out of bound")));

      List<String> listStrings = Arrays.asList("ab1c2c3","1dfg3d4","4fd45g","234","456");

      // listStrings.stream().map(x->x.replaceAll("^a", "")).forEach(System.out::println);

      // listStrings.stream().filter(x->x.matches("\\d")).forEach(System.out::println);

      // listStrings.stream().filter(x->x.matches("\\w+")).map(x->x.toUpperCase()).forEach(System.out::println);
      // listStrings.stream().filter(x->x.matches("[a-z]")).forEach(System.out::println);

      // listStrings.stream().map(x->x.replaceAll("\\w", "")).mapToInt(x->Integer.parseInt(x)).forEach(System.out::println);
   
      List<String> emails = Arrays.asList("aks@yahoo.com","akhilesh@yahoo.com","aks@gmail.com","akhilesh@gmail.com");

      // emails.stream().map(x->x.replaceAll("^.*@+", ""))
      //       .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
      //       .entrySet().stream().forEach(System.out::println);

      // Stream.iterate( new int[] {0,1},f -> new int[] {
      //   f[1],f[0]+f[1]
      // }).limit(10)
      // .map(x->x[0])
      // .forEach(System.out::println);

    // List<Employee> a = List.of(new Employee("aks",20),new Employee("akhil",30));
    // a.stream().map(x->x.getName().toUpperCase()).forEach(System.out::println);
    // List<String> strings = Arrays.asList("apple","apricot", "cherry", "blueberry", "avocado");

    // strings.stream().collect(Collectors.groupingBy(x->x.charAt(0),Collectors.counting())).entrySet().stream().forEach(System.out::println);

    // List<String> alphabets = Arrays.asList("a","b","c","d","");
    // System.out.println(alphabets.stream().map(String::toUpperCase).collect(Collectors.joining("")));

    a printerClass = new a();

    Runnable aRunnable=()->{
      printerClass.printANumber(Thread.currentThread().getName());
    };

    Thread thread1= new Thread(aRunnable,"Thread 1");
    Thread thread2 = new Thread(aRunnable,"Thread 2");
    System.out.println("===========Calling Thread============");
    thread1.start();
    thread2.start();
    


  }

  static void findClosestPair(int[] arr, int target) {

    int left=0,right=arr.length-1;
    int clossestSum = Integer.MAX_VALUE;
    int resultLeft=0,resultRight=0;
    int sum=0;

    while(left<right){

      sum=arr[left]+arr[right];
      if(Math.abs(target-sum)<Math.abs(target-clossestSum))
      {
        clossestSum=sum;
        resultLeft=left;
        resultRight=right;

      }
      if(sum>target)
      left++;
       else 
       right--;
    }

    System.out.println(""+arr[resultLeft]+"+"+arr[resultRight]+"="+clossestSum);

  }

  public synchronized void printANumber(String theadName){
    
    while(true){

      System.out.println(theadName+":"+n);
      n++;
      notify();
      try {
        if(n<maxNumber)
        wait();
        else
        
        break;
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
    }
  }

}
