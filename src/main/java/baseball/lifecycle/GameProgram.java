package baseball.lifecycle;

import baseball.game.Game;
import baseball.io.Input;
import baseball.io.Output;

public abstract class GameProgram extends AbstractProgram {

    private final String START_COMMAND = "1";
    private final String FINISH_COMMAND = "2";

    private final Game game;

    public GameProgram(Game game, Input input, Output output) {
        super(input, output);
        this.game = game;
    }

    @Override
    public void boot() {
        game.start();
        displayMenu();
    }

    @Override
    public void terminate() {
        writeOutput("게임을 완전히 종료합니다.");
        System.exit(0);
    }

    private void displayMenu() {
        writeOutput(String.format(
                "게임을 새로 시작하려면 %s, 종료하려면 %s를 선택하세요.",
                START_COMMAND,
                FINISH_COMMAND
        ));
        declineProcess();
    }

    private void declineProcess(){
        String command = readInput();
        if (startGameIfStartCommand(command)) {
            return;
        }
        if (finishGameIfFinishCommand(command)) {
            return;
        }
        wrongInputDetectedAlert();
        declineProcess();
    }

    private boolean startGameIfStartCommand(String read) {
        if(START_COMMAND.equals(read)){
            game.start();
            displayMenu();
            return true;
        }
        return false;
    }

    private boolean finishGameIfFinishCommand(String read) {
        if(FINISH_COMMAND.equals(read)){
            terminate();
            return true;
        }
        return false;
    }

    private void wrongInputDetectedAlert(){
        writeOutput("잘못된 입력입니다.");
    }

}
