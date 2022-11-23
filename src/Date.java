public class Date {
    private final int day;
    private final int month;
    private final int year;

    public Date() {
        long daysInMillis = System.currentTimeMillis()/1000/60/60/24;
        int currentYear = 1970;
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
        day = (int) daysInMillis + 1;
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
        int day = this.day;
        int month = this.month;
        int year = this.year;
        if (month > 2) {
            month -= 2;
        } else {
            month += 10;
            year--;
        }
        int weekDay = Math.floorMod(day + (13 * month - 1) / 5 + year + Math.floorDiv(year, 4)
                + (Math.floorDiv(year, 400) - Math.floorDiv(year, 100)), 7);
        return switch (weekDay) {
            case 0 -> "Вокресенье";
            case 1 -> "Понедельник";
            case 2 -> "Вторник";
            case 3 -> "Среда";
            case 4 -> "Четверг";
            case 5 -> "Пятница";
            case 6 -> "Суббота";
            default -> throw new IllegalStateException("Название дня недели "+weekDay+" не определено");
        };
    }

    public String formatDate(String mask) {
        String year = String.valueOf(this.year);
        String month = this.month / 10 >= 1 ? String.valueOf(this.month) : "0" + this.month;
        String day = this.day / 10 >= 1 ? String.valueOf(this.day) : "0" + this.day;
        return mask.replace("YYYY",year).replace("YY",year.substring(2,4)).replace("MM",month).replace("DD",day);

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
