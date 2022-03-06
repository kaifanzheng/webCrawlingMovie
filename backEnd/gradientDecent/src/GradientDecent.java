import java.util.ArrayList;


public class GradientDecent {
    private ArrayList<dataPoint> points = new ArrayList<dataPoint>();
    private double initSlop;
    private double initIntercept;
    private double constant;
    private PlotTest plot;

    public GradientDecent(ArrayList<dataPoint> points,double initSlop,double initIntercept,double constant){
        this.points = points;
        this.initSlop = initSlop;
        this.initIntercept = initIntercept;
        this.constant = constant;

        this.plot = new PlotTest();
        plot.PlotPoints(this.points);
    }

    public String getSumOfSquaredResidualFunction(){
        String result = "sum of squared residual = \n";
        for(dataPoint ele:points){
            String sEqu = "("+String.valueOf(ele.y)+" - " +
                    "(Intercept + Slop * "+String.valueOf(ele.x)+"))^2 +\n";
            result = result + sEqu;
        }
        return result;
    }

    public String getPartialSlopFunction(){
        String result = "sum of Slop = \n";
        for(dataPoint ele:points){
            String x = String.valueOf(ele.x);
            String y = String.valueOf(ele.y);
            String sEqu ="-2 * "+x+ "("+y+" - " +
                    "(Intercept + Slop * "+x+")) +\n";
            result = result + sEqu;
        }
        return result;
    }

    public String getPartialInterceptFunction(){
        String result = "sum of Intercept = \n";
        for(dataPoint ele:points){
            String x = String.valueOf(ele.x);
            String y = String.valueOf(ele.y);
            String sEqu ="-2 * "+"("+y+" - " +
                    "(Intercept + Slop * "+x+")) +\n";
            result = result + sEqu;
        }
        return result;
    }
    //return the best slop and interception. 0-slop; 1-interception
    public double[] solve(double boundNum) throws InterruptedException {
        double[] result = new double[2];
        while(true){
            double interResidual =0;
            double slopResidual = 0;
            for(dataPoint ele:this.points){
                interResidual = interResidual + (-2*(ele.y-(initIntercept+initSlop*ele.x)));
                slopResidual = slopResidual + (-2*ele.x*(ele.y-(initIntercept+initSlop*ele.x)));
            }
            if(Math.abs(slopResidual)<=boundNum&&Math.abs(interResidual)<=boundNum){
                result[0] = initSlop;
                result[1] = initIntercept;
                break;
            }else{
                this.initSlop = this.initSlop - (slopResidual*this.constant);
                this.initIntercept = this.initIntercept - (interResidual*this.constant);
                System.out.println("Slop:"+initSlop+" inter: "+initIntercept+
                        " slop residual: "+slopResidual+" interResidual: "+interResidual);//test code
            }
            //Thread.sleep(100);//for debug propose;
        }
        this.plot.PlotLine(initIntercept,initSlop);
        return result;
    }

}
