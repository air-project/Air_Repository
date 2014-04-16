package com.yh.other;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;



public class ProcessUtils {

    /**
     * 运行一个外部命令，返回状态.若超过指定的超时时间，抛出TimeoutException
     * 
     */
    public static ProcessStatus execute(final long timeout, final String... command)
            throws IOException, InterruptedException, TimeoutException {

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        Worker worker = new Worker(process);
        worker.start();
        ProcessStatus ps = worker.getProcessStatus();
        try {
            worker.join(timeout);
            if (ps.exitCode == ProcessStatus.CODE_STARTED) {
                // not finished
                worker.interrupt();
                throw new TimeoutException();
            } else {
                return ps;
            }
        } catch (InterruptedException e) {
            // canceled by other thread.
            worker.interrupt();
            throw e;
        } finally {
            process.destroy();
        }
    }

    private static class Worker extends Thread {
        private final Process process;
        private ProcessStatus ps;

        private Worker(Process process) {
            this.process = process;
            this.ps = new ProcessStatus();
        }

        public void run() {
            try {
                InputStream is = process.getInputStream();
                ps.output = is.toString();
                ps.exitCode = process.waitFor();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public ProcessStatus getProcessStatus() {
            return this.ps;
        }
    }
    
    public static class ProcessStatus {
        public static final int CODE_STARTED = -257;
        public volatile int exitCode;
        public volatile String output;
    }

}
