import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_17_2024
{
    static long[] registers;
    static int[] instructions;

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2024/input/17.txt"));
        parse(input);

        double start = System.nanoTime();

        String partOne = partOne();
        long partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static String partOne()
    {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < instructions.length; i+=2)
        {
            long comboOperand = instructions[i + 1] <= 3 ? instructions[i + 1] : instructions[i + 1] == 4 ? registers[0] : instructions[i + 1] == 5 ? registers[1] : registers[2];

            switch(instructions[i])
            {
                case 0: //adv
                    registers[0] = registers[0] / (int) Math.pow(2, comboOperand);
                    break;
                case 1: //bxl
                    registers[1] = registers[1] ^ instructions[i + 1];
                    break;
                case 2: //bst
                    registers[1] = comboOperand % 8;
                    break;
                case 3: //jnz
                    if(registers[0] != 0) i = instructions[i + 1] - 2;
                    break;
                case 4:
                    registers[1] = registers[1] ^ registers[2];
                    break;
                case 5:
                    if(!result.isEmpty())
                        result.append(",");
                    result.append(comboOperand % 8);
                    break;
                case 6: //bdv
                    registers[1] = registers[0] / (int) Math.pow(2, comboOperand);
                    break;
                case 7: //cdv
                    registers[2] = registers[0] / (int) Math.pow(2, comboOperand);
                    break;
            }
        }

        return result.toString();
    }

    static long partTwo()
    {
        long result = 0;

        StringBuilder check;
        String check2 = toString(instructions);

        long test = 233731868592124L;

        for(long x = test; true; x++)1
        {
            System.out.println(x);
            registers[0] = x;
            registers[1] = 0;
            registers[2] = 0;

            check = new StringBuilder();

            for (int i = 0; i < instructions.length; i += 2) {
                long comboOperand = instructions[i + 1] <= 3 ? instructions[i + 1] : instructions[i + 1] == 4 ? registers[0] : instructions[i + 1] == 5 ? registers[1] : registers[2];

                switch (instructions[i]) {
                    case 0: //adv
                        registers[0] = registers[0] / (int) Math.pow(2, comboOperand);
                        break;
                    case 1: //bxl
                        registers[1] = registers[1] ^ instructions[i + 1];
                        break;
                    case 2: //bst
                        registers[1] = comboOperand % 8;
                        break;
                    case 3: //jnz
                        if (registers[0] != 0) i = instructions[i + 1] - 2;
                        break;
                    case 4:
                        registers[1] = registers[1] ^ registers[2];
                        break;
                    case 5:
                        if (!check.isEmpty())
                            check.append(",");
                        check.append(comboOperand % 8);
                        break;
                    case 6: //bdv
                        registers[1] = registers[0] / (int) Math.pow(2, comboOperand);
                        break;
                    case 7: //cdv
                        registers[2] = registers[0] / (int) Math.pow(2, comboOperand);
                        break;
                }
            }

            System.out.println(check);

            if(check.toString().equals(check2))
            {
                result = x;
                break;
            }
        }

        return result;
    }

    static void parse(Scanner input)
    {
        registers = new long[3];

        registers[0] = Long.parseLong(input.nextLine().substring(12));
        registers[1] = Long.parseLong(input.nextLine().substring(12));
        registers[2] = Long.parseLong(input.nextLine().substring(12));

        input.nextLine();

        instructions = Arrays.stream(input.nextLine().substring(9).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    static String toString(int[] arr)
    {
        StringBuilder result = new StringBuilder();

        for(int a : arr)
        {
            if (!result.isEmpty())
                result.append(",");
            result.append(a);
        }

        return result.toString();
    }
}
