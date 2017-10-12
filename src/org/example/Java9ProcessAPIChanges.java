package org.example;


import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


/**
 * Thanks for watching this episode! If you want to, drop me a line to info@marcobehler.com.
 */
public class Java9ProcessAPIChanges {

    public static void main(String[] a) throws IOException, InterruptedException, ExecutionException {
        List<Long> javaPids = ProcessHandle.allProcesses()
                .filter(cmd -> cmd.info().command().orElse("").contains("java"))
                .map(ProcessHandle::pid)
                .collect(Collectors.toList()); // yay! kill them!

        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
        Process process = processBuilder.start();
        printProcessInfo(process.toHandle());

        process.onExit().thenRun(() -> System.out.println("I guess we are done, hombre!")).get();
    }

    private static void printProcessInfo(ProcessHandle processHandle) {
        ProcessHandle.Info info = processHandle.info();

        // pid is on the process handle...
        System.out.printf("Process id: " + processHandle.pid() + "\n");

        // the rest on the process info....
        System.out.println("Command: " + info.command().orElse(""));
        System.out.println("Arguments:" + Arrays.toString(info.arguments().orElse(new String[]{})));
        System.out.println("Command line: " + info.commandLine().orElse(""));
        System.out.println("Start time: " + info.startInstant().orElse(Instant.now()).toString());
        System.out.println("Run time duration: " + info.totalCpuDuration().orElse(Duration.ofMillis(0)).toMillis());
        System.out.println("Owner: " + info.user().orElse(""));
    }
}
