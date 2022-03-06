import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class mainTry {
    public static void main(String[] args) throws InterruptedException {
        dataPoint one = new dataPoint(0.5,1.4);
        dataPoint two = new dataPoint(2.3,1.9);
        dataPoint three = new dataPoint(2.9,3.2);
        ArrayList<dataPoint> list = new ArrayList<>();
        list.add(three);
        list.add(new dataPoint(0.2,1.5));
        list.add(new dataPoint(0.6,2.0));
        list.add(new dataPoint(1.4,1.0));
        list.add(new dataPoint(2.4,2.5));
        list.add(one);
        list.add(two);
        list.add(new dataPoint(2.8,2.5));
        list.add(new dataPoint(1.6,3.0));
        list.add(new dataPoint(1.8,2.9));
        list.add(new dataPoint(2.0,2.5));
        list.add(new dataPoint(1.2,2.5));
        list.add(new dataPoint(1.3,2.6));
        list.add(new dataPoint(2.6,3.0));
        list.add(new dataPoint(1.5,2.9));
        list.add(new dataPoint(4.0,1.3));
        list.add(new dataPoint(0.7,1.5));
        list.add(new dataPoint(0.8,0.5));
        list.add(new dataPoint(0.8,0.6));
        list.add(new dataPoint(0.9,3));
        list.add(new dataPoint(1.3,2.6));
        list.add(new dataPoint(0,2.5));
        list.add(new dataPoint(0.5,3.5));


//        Random ran= new Random();
//        for(int i=0;i<20;i++){
//            double x= ran.nextInt(10);
//            double y= ran.nextInt(10);
//            list.add(new dataPoint(x,y));
//        }
        GradientDecent foo = new GradientDecent(list,0,1,0.01);
        System.out.println(foo.getSumOfSquaredResidualFunction());
        System.out.println(foo.getPartialInterceptFunction());
        System.out.println(foo.getPartialSlopFunction());
        System.out.println(Arrays.toString(foo.solve(0.01)));
    }
}
