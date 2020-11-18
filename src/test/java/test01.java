/**
 * @author HUI
 * @create 2020-11-18 10:52
 */
public class test01 {
    public static void main(String[] args) {
        String x="String";
        String y="String";
        String z=new String("String");
        System.out.println(x==y);
        System.out.println(x==z);
        System.out.println(x.hashCode());
        System.out.println(y.hashCode());
        System.out.println(z.hashCode());
        System.out.println(x.equals(y));
        System.out.println(x.equals(z));
    }
}
