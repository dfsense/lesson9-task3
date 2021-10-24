package ua.kovalev;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceAnalysisText {
    private List<Character> listFindedCharcters;
    private List<Character>listCharacters;
    private List<AnalysisRunnable> listRunnables;
    private List<Thread> listThreads;
    private ControllerThreads controllerThreads;
    private Thread threadControllerThreads;

    public ServiceAnalysisText() {
        super();
    }

    public ServiceAnalysisText(String text) {
        super();
        listFindedCharcters = new ArrayList<>();
        listCharacters = new ArrayList<>();
        listRunnables = new ArrayList<>();
        listThreads = new ArrayList<>();
        controllerThreads = new ControllerThreads();

        // отдельно запускаю поток который будет управлять количеством одновременно работающих потоков
        threadControllerThreads = new Thread(controllerThreads);
        threadControllerThreads.start();

        convertCharsToCharavters(text.toCharArray());
    }

    public void goAnalysis() {
        for (int i = 0; i < listCharacters.size(); i++) {
            if (Character.isLetter(listCharacters.get(i)) && !listFindedCharcters.contains(listCharacters.get(i))){
                controllerThreads.setThread(createThread(listCharacters.get(i)));
                listFindedCharcters.add(listCharacters.get(i));
            }
        }
        for (Thread thread : listThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(listRunnables, ((o1, o2) -> o1.getCharacter()-o2.getCharacter()));

        for (AnalysisRunnable runnable : listRunnables) {
            System.out.println(String.format("%s : %d", runnable.getCharacter(), runnable.getResult()));
        }

        controllerThreads.setStop(true);

    }



    public Thread createThread(char ch){
        AnalysisRunnable runnable = new AnalysisRunnable(controllerThreads, ch, listCharacters);
        listRunnables.add(runnable);

        Thread thread = new Thread(runnable);
        listThreads.add(thread);

        return thread;
    }

    public void convertCharsToCharavters(char[] chars){
        for (char c : chars) {
            listCharacters.add(c);
        }
    }
}

