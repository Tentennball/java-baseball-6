package baseball;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
class Game {
    private final List<Integer> computer;
    private final List<Integer> userInput;
    Game() {
        computer = new ArrayList<>();
        userInput = new ArrayList<>();
    }
    public void run() {
        try {
            do {
                play();
            } while (restart());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    public void play() {
        createNum();
        while (true) {
            if (input()) {
                if (isCorrect()) {
                    break;
                }
            } else {
                throw new IllegalArgumentException("잘못된 입력으로 인해 게임을 종료합니다.");
            }
        }
    }
    public static boolean restart() {
        System.out.println("게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String reNum = Console.readLine();
        if(reNum.equals("1")){ return true;}
        else if(reNum.equals("2")){return false;}
        else throw new IllegalArgumentException("잘못된 입력으로 인해 게임을 종료합니다.");
    }
    public boolean isCorrect() {
        int strikeNum = 0;
        int ballNum = 0;
        int index = 0;
        for (int comInt : computer) {
            if (userInput.contains(comInt)) {
                if (userInput.get(index).equals(computer.get(index))) {
                    strikeNum++;
                } else {
                    ballNum++;
                }
            }
            index++;
        }
        if (strikeNum == 0 && ballNum == 0) {
            System.out.println("낫싱");
            return false;
        } else if (strikeNum == 3) {
            System.out.println("3스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다!");
            return true;
        } else {
            System.out.println(ballNum + "볼 " + strikeNum + "스트라이크");
            return false;
        }
    }
    public void createNum() {
        computer.clear();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        System.out.println(computer);
    }
    public boolean input() {
        System.out.print("숫자를 입력하세요.");
        String inputStr = Console.readLine();
        int num;
            if (inputStr.length() == 3) {
                if (userInput.size() < 3) {
                    for (int i = 0; i < inputStr.length(); i++) {
                        num = Integer.parseInt(inputStr.substring(i, i + 1));
                        if (num == 0)
                            throw new IllegalArgumentException("잘못된 입력으로 인해 게임을 종료합니다.");
                        userInput.add(num);
                    }
                } else {
                    for (int i = 0; i < inputStr.length(); i++) {
                        num = Integer.parseInt(inputStr.substring(i, i + 1));
                        if (num == 0)
                            throw new IllegalArgumentException("잘못된 입력으로 인해 게임을 종료합니다.");
                        userInput.set(i, num);
                    }
                }
                return true;
            } else {
                throw new IllegalArgumentException("잘못된 입력으로 인해 게임을 종료합니다.");
            }
    }
}
public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        Game bsGame = new Game();
        bsGame.run();
    }
}