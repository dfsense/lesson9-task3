package ua.kovalev;

import java.util.List;

public class AnalysisRunnable implements Runnable {
    private ControllerThreads controllerThreads;
    private char character;
    private List<Character> listCharacters;
    private int result;

    public AnalysisRunnable() {
        super();
    }

    public AnalysisRunnable(ControllerThreads controllerThreads, char ch, List<Character> characters) {
        super();
        this.controllerThreads = controllerThreads;
        this.character = ch;
        listCharacters = characters;
    }

    @Override
    public void run() {
        for (Character ch : listCharacters) {
            if (ch == character)
                result++;
        }
        controllerThreads.endThread();
    }

    public ControllerThreads getControllerThreads() {
        return controllerThreads;
    }

    public void setControllerThreads(ControllerThreads controllerThreads) {
        this.controllerThreads = controllerThreads;
    }

    public List<Character> getListCharacters() {
        return listCharacters;
    }

    public void setListCharacters(List<Character> listCharacters) {
        this.listCharacters = listCharacters;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
