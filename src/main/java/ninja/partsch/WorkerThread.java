package ninja.partsch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

public class WorkerThread extends Thread {

    private Transformation transformation;
    private BlockingQueue<String> queue;

    public WorkerThread(Transformation transformation, BlockingQueue<String> queue) {
        this.transformation = transformation;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String data = queue.take();
                String transformed = transformation.apply(data);
                CompletableFuture.runAsync(() -> Storage.save(transformed));
            }
        } catch (Exception e) {
            System.err.println("WorkerThread died!");
            e.printStackTrace();
        }
    }
}
