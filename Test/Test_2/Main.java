package Test_2;

public class Main {
    public static void main() {
        Input input = new Input();
        input.input("Test_2/input.txt");
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        alphabetizer.sort();
        Output output = new Output(alphabetizer.getKwicList());
        output.output("Test_2/output.txt");

    }
}
