package ninja.partsch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    private static int keithleyCounter = 0, teensyCounter = 0;

    public static void main(String...args) throws InterruptedException {

        DeviceInterface keithley = () -> {
            Thread.sleep(5_000);
            return String.format("Hello, from Keithley (No: %d)", ++keithleyCounter);
        };

        DeviceInterface teensy = () -> {
            Thread.sleep(2_500);
            return String.format("Hello, form Teensy (No: %d)", ++teensyCounter);
        };

        Transformation keithleyTransofmation = (data) -> "Transformed(" + data + ")";
        Transformation teensyTransformation = String::toUpperCase;

        BlockingQueue<String> keithleyQueue = new LinkedBlockingQueue<>();
        BlockingQueue<String> teensyQueue = new LinkedBlockingQueue<>();

        DeviceThread keithleyThread = new DeviceThread(keithley, keithleyQueue);
        keithleyThread.start();

        new DeviceThread(teensy, teensyQueue).start();

        new WorkerThread(keithleyTransofmation, keithleyQueue).start();
        new WorkerThread(teensyTransformation, teensyQueue).start();

        keithleyThread.join();
    }

}
