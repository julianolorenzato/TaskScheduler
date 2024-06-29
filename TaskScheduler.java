import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TaskScheduler {
    static public class Task {
        String name;
        int burstTime;

        public Task(String name, int burstTime) {
            this.name = name;
            this.burstTime = burstTime;
        }

        @Override
        public String toString() {
            return "(" + this.name + ": " + this.burstTime + ")";
        }
    }

    public static void main(String[] args) {
        String filename = args[0];
        int numProcessors = Integer.parseInt(args[1]);
        BufferedReader reader;

        List<Task> tasks = new ArrayList<Task>();

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                String[] splitted = line.split(" ");

                Task task = new Task(splitted[0], Integer.parseInt(splitted[1]));
                tasks.add(task);

                line = reader.readLine();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        // SJF
        List<Task>[] processors = new List[numProcessors];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new ArrayList<Task>();
        }

        tasks.sort((a, b) -> a.burstTime - b.burstTime);
        for (int i = 0; i < tasks.size(); i++) {
            int processorIndex = i % numProcessors;
            processors[processorIndex].add(tasks.get(i));
        }

        for (int i = 0; i < processors.length; i++) {
            int processorTime = 0;

            System.out.println("Processador_" + (i + 1));

            for (Task task : processors[i]) {
                System.out.println(task.name + ";" + processorTime + ";" + (processorTime += task.burstTime));
            }

            System.out.println();
        }

        // SJF oposto
        for (int i = 0; i < processors.length; i++) {
            processors[i].clear();
        }

        tasks.sort((a, b) -> b.burstTime - a.burstTime);
        for (int i = 0; i < tasks.size(); i++) {
            int processorIndex = i % numProcessors;
            processors[processorIndex].add(tasks.get(i));
        }

        for (int i = 0; i < processors.length; i++) {
            int processorTime = 0;

            System.out.println("Processador_" + (i + 1));

            for (Task task : processors[i]) {
                System.out.println(task.name + ";" + processorTime + ";" + (processorTime += task.burstTime));
            }

            System.out.println();
        }
    }
}
