package ninja.partsch;

import java.util.concurrent.BlockingQueue;

public class DeviceThread extends Thread {

    private DeviceInterface device;
    private BlockingQueue<String> queue;

    public DeviceThread(DeviceInterface device, BlockingQueue<String> commandqueue, BlockingQueue<String> dataqueue) {
        this.device = device;
        this.dataqueue = dataqueue;
        this.commandqueue = commandqueue;
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
