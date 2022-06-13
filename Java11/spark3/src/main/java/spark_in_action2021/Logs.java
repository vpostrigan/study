package spark_in_action2021;

public class Logs {
    private final StringBuilder sb = new StringBuilder();

    public void outPrintln(String s) {
        System.out.println(s);
        if (sb.length() > 0) {
            sb.append('\n');
        }
        sb.append(s);
    }

    public StringBuilder getSb() {
        return sb;
    }

}
