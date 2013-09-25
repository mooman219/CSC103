public class Quadratic {
    private double coefA;
    private double coefB;
    private double coefC;

    public Quadratic(double coefA, double coefB, double coefC) {
        this.coefA = coefA;
        this.coefB = coefB;
        this.coefC = coefC;
    }

    /**
     * @return The first coefficient of the equation.
     */
    public double getCoefA() {
        return coefA;
    }

    /**
     * @return The second coefficient of the equation.
     */
    public double getCoefB() {
        return coefB;
    }

    /**
     * @return The third coefficient of the equation.
     */
    public double getCoefC() {
        return coefC;
    }

    /**
     * @return Calculates and returns the discriminant of the equation.
     */
    public double getDiscriminant() {
        return Math.pow(coefB, 2) - 4 * coefA * coefC;
    }

    public double evalExpression(double x) {
        return (coefA * Math.pow(x, 2)) + (coefB * x) + coefC;
    }

    /**
     * @param quad1
     *            The first quadratic to be added.
     * @param quad2
     *            The second quadratic to be added.
     * @return A new quadratic whos coefficients are sum of the given
     *         quadratics.
     */
    public static Quadratic sum(Quadratic quad1, Quadratic quad2) {
        return new Quadratic(quad1.getCoefA() + quad2.getCoefA(), quad1.getCoefB() + quad2.getCoefB(), quad1.getCoefC() + quad2.getCoefC());
    }

    /**
     * @param quad1
     *            The quadratic to be scaled.
     * @param amount
     *            The amount to scale the quadratic by.
     * @return A new quadratic that is scaled by the amount.
     */
    public static Quadratic scale(Quadratic quad1, double amount) {
        return new Quadratic(quad1.getCoefA() * amount, quad1.getCoefB() * amount, quad1.getCoefC() * amount);
    }

    /**
     * @return The number of real roots in the equation.
     */
    public int getRootNum() {
        double discriminant = getDiscriminant();
        return discriminant > 0 ? 2 : discriminant == 0 ? 1 : 0;
    }

    /**
     * @return The first root of the equation if the discriminant is greater
     *         than or equal to 0, else returns null.
     */
    public double getRootOne() {
        return getDiscriminant() >= 0 ? (-coefB + Math.sqrt(getDiscriminant())) / (2 * coefA) : null;
    }

    /**
     * @return The first root of the equation if the discriminant is greater
     *         than 0, else returns null.
     */
    public double getRootTwo() {
        return getDiscriminant() > 0 ? (-coefB - Math.sqrt(getDiscriminant())) / (2 * coefA) : null;
    }

    @Override
    public String toString() {
        return coefA + "x*x + " + coefB + "x + " + coefC;
    }
    //Equals method 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Quadratic other = (Quadratic) obj;
        if (Double.doubleToLongBits(coefA) != Double.doubleToLongBits(other.coefA)) {
            return false;
        }
        if (Double.doubleToLongBits(coefB) != Double.doubleToLongBits(other.coefB)) {
            return false;
        }
        if (Double.doubleToLongBits(coefC) != Double.doubleToLongBits(other.coefC)) {
            return false;
        }
        return true;
    }

    //Clone method
    public Quadratic clone() {
        return new Quadratic(coefA, coefB, coefC);
    }
}
