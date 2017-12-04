package ninja.partsch;

public class Storage {

    public static synchronized void save(String data) {
        System.out.printf("Save: '%s'%n", data);
    }

}
