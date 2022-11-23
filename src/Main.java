import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Date date = getCorrectDate(scan);
        printDateInfo(date);
        formatDateOutput(scan,date);
        scan.close();
    }
    public static Date getCorrectDate(Scanner scan) {
        String inputDate;
        Date parsedDate = new Date();
        boolean inputDateIncorrect = true;
        while (inputDateIncorrect) {
            System.out.println("Введите дату в формате DD.MM.YYYY. Текущая дата: "+parsedDate.formatDate("DD.MM.YYYY"));
            inputDate = scan.nextLine();
            if (inputDate.length() == 10 && inputDate.indexOf('.') == 2 && inputDate.indexOf('.', 3) == 5) {
                try {
                    int day = Integer.parseInt(inputDate.substring(0, 2));
                    int month = Integer.parseInt(inputDate.substring(3, 5));
                    int year = Integer.parseInt(inputDate.substring(6, 10));
                    parsedDate = new Date(day, month, year);
                    inputDateIncorrect = false;
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }
            } else {
                System.out.println("Некорректый формат даты (ожидается формат DD.MM.YYYY)");
            }
        }
        return parsedDate;

    }
    public static void printDateInfo(Date date) {
        if (date.isLeapYear()) {
            System.out.println("Год високосный");
        } else {
            System.out.println("Год не високосный");
        }
        System.out.println("Количество дней в указанном месяце: " + date.daysInMonth());
        System.out.println("День недели: " + date.DayOfWeek());
    }

    public static void formatDateOutput(Scanner scan, Date date) {
        String mask;
        System.out.println("Задайте формат вывода даты (YYYY - год, MM - месяц, DD - день):");
        mask = scan.nextLine();
        if (!mask.isEmpty()) {
            System.out.println(date.formatDate(mask));
        } else {
            System.out.println("Маска не задана, выводится формат по умолчанию\n"+date.formatDate("YYYY-MM-DD"));
        }
    }
}