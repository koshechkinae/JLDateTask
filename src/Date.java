public class Date {
    private final int day;
    private final int month;
    private final int year;

    public Date() {
        int daysInMillis = (int) (System.currentTimeMillis()/1000/60/60/24);
        int currentYear = 1970; //System.currentTimeMillis() начинает отсчет с 01.01.1970
        while (daysInMillis >= 365) {
            if (isLeapYear(currentYear)) {
                daysInMillis -= 366;
            } else {
                daysInMillis -= 365;
            }
            currentYear += 1;
        }
        year = currentYear;
        int currentMonth = 1;
        while (daysInMillis >= daysInMonth(currentMonth,currentYear)) {
            daysInMillis -= daysInMonth(currentMonth,currentYear);
            currentMonth += 1;
        }
        month = currentMonth;
        day = daysInMillis + 1;
    }

    public Date(int day, int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Месяц введен некорректно (ожидается число с 1 по 12). Вы ввели: "+month);
        }
        if (day < 1 || day > daysInMonth(month,year)) {
            throw new IllegalArgumentException("День введен некорректно (ожидается значение с 1 по "+daysInMonth(month,year)+". Вы ввели: "+day);
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isLeapYear() {
        return isLeapYear(year);
    }
    public int daysInMonth() {
        return daysInMonth(month,year);
    }

   //Вычислить день недели по дате
    public String DayOfWeek() {
        int dayCount = 0;
        int weekDay;
        int i;
        for (i = 1970; i < year; i++) {  //Определим количество дней в прошедших годах до года указанной даты
            if (isLeapYear(i)) {
                dayCount += 366;
            } else {
                dayCount += 365;
            }
        }
        for (i = 1; i < month; i++) {     //Добавим к полученному количеству сумму дней в прошедших месяцах до месяца указанной даты
            dayCount += daysInMonth(i,year);
        }
        dayCount = dayCount + day - 1;      //Добавим количество прошедших дней месяца до указанной даты
        weekDay = dayCount % 7;         //Остаток от деления не 7 дней даст номер дня недели

        return switch (weekDay) {
            case 0 -> "Четверг";
            case 1 -> "Пятница";
            case 2 -> "Суббота";
            case 3 -> "Вокресенье";
            case 4 -> "Понедельник";
            case 5 -> "Вторник";
            case 6 -> "Среда";
            default -> throw new IllegalStateException("Название дня недели "+weekDay+" не определено");
        };
    }

    public String formatDate (String mask) {
        mask = mask.replace("YYYY",String.valueOf(year));
        mask = month / 10 >= 1 ? mask.replace("MM",String.valueOf(month)) : mask.replace("MM","0"+month);
        mask = day / 10 >= 1 ? mask.replace("DD",String.valueOf(day)) : mask.replace("DD","0"+day);
        return mask;

    }
    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else return year % 400 == 0;
    }

    private int daysInMonth(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> isLeapYear(year) ? 29 : 28;
            case 4, 6, 9, 11 -> 30;
            default -> throw new IllegalStateException("Количество дней для месяца "+month+" не определено");
        };
    }
}
