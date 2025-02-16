package classes.number;

import java.math.BigInteger;

public class BigNumberApp {
  public static void main(String[] args) {

    BigInteger a = new BigInteger("10000000000000000000");
    BigInteger b = new BigInteger("99999999999999999999");

    BigInteger result1 = a.add(b);
    BigInteger result2 = a.subtract(b);
    BigInteger result3 = a.multiply(b);
    BigInteger result4 = a.divide(b);

    System.out.println(result1);
    System.out.println(result2);
    System.out.println(result3);
    System.out.println(result4);

  }
}
