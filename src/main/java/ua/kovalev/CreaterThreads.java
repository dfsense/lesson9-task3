package ua.kovalev;

import java.util.List;

public class CreaterThreads implements Runnable{
    private ServiceAnalysisText serviceAnalysisText;
    private List<Character> listCharacters;
    private List<Thread> listThreads;
    private List<Runnable> listRunnables;

    public CreaterThreads(ServiceAnalysisText serviceAnalysisText, List<Character> listCharacters) {
        this.serviceAnalysisText = serviceAnalysisText;
        this.listCharacters = listCharacters;
    }

    @Override
    public void run() {

    }
}
