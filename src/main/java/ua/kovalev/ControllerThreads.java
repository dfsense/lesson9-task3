package ua.kovalev;

import java.util.ArrayList;
import java.util.List;

public class ControllerThreads implements Runnable {
    private int maxCountsThreads;
    private int countWorkingThread;
    private List<Thread> threads = new ArrayList<>();
    private boolean stop = false;

    public ControllerThreads() {
        maxCountsThreads = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public void run() {
        while (!stop) {
            for (;countWorkingThread > maxCountsThreads;){
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (threads.isEmpty()) {
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                countWorkingThread++;
                Thread thread = threads.get(0);
                threads.remove(0);
                thread.start();
            }

        }
    }

    synchronized public void setThread(Thread thread) {
        threads.add(thread);
        notifyAll();
    }

    synchronized public void endThread(){
        countWorkingThread--;
        notifyAll();
    }

    synchronized public void setStop(boolean stop){
        this.stop = stop;
        notifyAll();
    }
}
