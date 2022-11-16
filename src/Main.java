import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String scDate;
        Date inDate = new Date();
        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        while (flag) {
            System.out.println("Введите дату в формате DD.MM.YYYY");
            scDate = scan.nextLine();
            if (!scDate.isEmpty() & scDate.length() == 10 & scDate.indexOf('.') == 2 & scDate.indexOf('.', 3) == 5) {
                try {
                    int day = Integer.parseInt(scDate.substring(0, 2));
                    int month = Integer.parseInt(scDate.substring(3, 5));
                    int year = Integer.parseInt(scDate.substring(6, 10));
                    inDate = new Date(day, month, year);
                    flag = false;
                } catch (IllegalArgumentException e){
                    System.out.println("Некорректный ввод дня/месяца/года");
                }
            } else {
                System.out.println("Некорректный ввод");
            }
        }
        //Определим, високосный ли год
        if (inDate.isLeapYear()) {
            System.out.println("Год високосный");
        } else {
            System.out.println("Год не високосный");
        }
        //Определим количество дней в указанном месяце
        System.out.println("Количество дней в указанном месяце: " + inDate.daysInMonth());
        //Определим день недели
        System.out.println("День недели: " + inDate.DayOfWeek());
        //Выведем дату по форматной маске
        System.out.println("Задайте формат вывода даты (YYYY или YY - год, MM - месяц, DD - день):");
        scDate = scan.nextLine();
        if (!scDate.isEmpty()){
            System.out.println(inDate.formatDate(scDate));
        } else {
            System.out.println("Маска не задана, выводится формат по умолчанию\n"+inDate.formatDate("YYYY-MM-DD"));
        }
        scan.close();
    }
}