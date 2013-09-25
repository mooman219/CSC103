public class Quadratic {
    private double coefA;
    private double coefB;
    private double coefC;

    public Quadratic(double coefA, double coefB, double coefC) {
        this.coefA = coefA;
        this.coefB = coefB;
        this.coefC = coefC;
    }

    public double getCoefA() {
        return coefA;
    }

    public double getCoefB() {
        return coefB;
    }

    public double getCoefC() {
        return coefC;
    }

    public double evalExpression(double x) {
        return (coefA * Math.pow(x, 2)) + (coefB * x) + coefC;
    }

    public void sum() {

    }

    public void scale() {

    }

    public int getRootNum() {
        double discriminant = Math.pow(coefB, 2) - 4 * coefA * coefC;
        return discriminant > 0 ? 2 : discriminant == 0 ? 1 : 0;
    }

    public double getRootOne() {
        return 0;
    }

    public double getRootTwo() {
        return 0;
    }

}
