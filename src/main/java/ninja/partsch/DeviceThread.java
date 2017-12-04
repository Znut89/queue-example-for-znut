package ninja.partsch;

import java.util.concurrent.BlockingQueue;

public class DeviceThread extends Thread {

    private DeviceInterface device;
    private BlockingQueue<String> queue;

    public DeviceThread(DeviceInterface device, BlockingQueue<String> queue) {
        this.device = device;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String data = device.readNext();
                queue.put(data);
            }
        } catch (InterruptedException e) {
            System.err.println("DeviceThread died!");
            e.printStackTrace();
        }
    }

}
