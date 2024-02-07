import java.util.*;
public class Main {
    public static void main(String[] args) {
        double[][] d1 = {{},{1,2}};



        for (double[] i : d1) {
            System.out.println(Arrays.stream(i).summaryStatistics());
        }
    }
}

