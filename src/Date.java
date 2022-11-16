public class Date {
    private final int day;
    private final int month;
    private final int year;
    private final int initialYear = 1990;

    public Date() {
        day = 1;
        month = 1;
        year = initialYear;
    }

    public Date(int day, int month, int year) throws IllegalArgumentException{
        if (year>=initialYear) {
            this.year = year;
            if (month>=1 & month<=12) {
                this.month = month;
                if (day>=1 & day<=daysInMonth(month, year)) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
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

    //Определить, вискосный ли год
    public boolean isLeapYear() {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else return year % 400 == 0;
    }
    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else return year % 400 == 0;
    }
   //Определить количество дней в месяце
    public int daysInMonth() {
        switch (month){
            case 1,3,5,7,8,10,12: return 31;
            case 2:
                if (isLeapYear()) {
                    return 29;
                } else {
                    return 28;
                }
            case 4,6,9,11: return 30;
        }
        return month;
    }
    private int daysInMonth(int month, int year) {
        switch (month){
            case 1,3,5,7,8,10,12: return 31;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4,6,9,11: return 30;
        }
        return month;
    }
   //Вычислить день недели по дате
    public String DayOfWeek() {
        int dayCount = 0;
        int weekDay;
        int i;
        for (i = initialYear; i < year; i++) {  //Определим количество дней в прошедших годах до года указанной даты
            if (isLeapYear(i)) {
                dayCount += 366;
            } else {
                dayCount += 365;
            }
        }
        for (i = 1; i<month; i++) {     //Добавим к полученному количеству сумму дней в прошедших месяцах до месяца указанной даты
            dayCount += daysInMonth(i,year);
        }
        dayCount = dayCount + day - 1;      //Добавим количество прошедших дней месяца до указанной даты
        weekDay = dayCount % 7;         //Остаток от деления не 7 дней даст номер дня недели

        return switch (weekDay) {
            case 0 -> "Понедельник";
            case 1 -> "Вторник";
            case 2 -> "Среда";
            case 3 -> "Четверг";
            case 4 -> "Пятница";
            case 5 -> "Суббота";
            case 6 -> "Вокресенье";
            default -> throw new IllegalStateException("Unexpected value: " + weekDay);
        };
    }

    public String formatDate (String mask) {
        String year = "" + this.year;
        String month = "" + this.month;
        if (month.length()==1) {
            month = '0' + month;
        }
        String day = "" + this.day;
        if (day.length()==1) {
            day = '0'+ day;
        }
        String formatDate = mask;
        if (mask.contains("YYYY")){
            formatDate = formatDate.replace("YYYY",year);
        } else if (mask.contains("YY")) {
            formatDate = formatDate.replace("YY",year.substring(2,4));
        }
        if (mask.contains("MM")){
            formatDate = formatDate.replace("MM",month);
        }
        if (mask.contains("DD")){
            formatDate = formatDate.replace("DD",day);
        }
        return formatDate;
    }


}
