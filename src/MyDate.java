    public class MyDate implements Cloneable, Comparable<MyDate> {
    private int year;
    private int month;
    private int day;

    MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // get and set methods
    public int getYear() {
        return year;
    }

    public void setYear(int yr) {
        year = yr;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int mon) {
        month = mon;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int dy) {
        day = dy;
    }


    public String toString() {
        return year + "/" + month + "/" + day;
    }

    public void print() {
        System.out.print(" " + year + "/" + month + "/" + day + " ");
        //or System.out.printf("%-2s/%-2s/%-4s%n",month,day,year);
    }

    public Boolean isExpired(MyDate expiryDate) {
        if (year < expiryDate.year) {
            return false;
        } else if (year == expiryDate.year) {
            if (month < expiryDate.month) {
                return false;
            } else if (month == expiryDate.month) {
                if (day < expiryDate.day) {
                    return false;
                }
            }
        }
        return true;
    }

    //    Copy Constructor
    public MyDate(MyDate date) {
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
    }

    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }

    public int compareTo(MyDate date){
        if (isExpired(date)){
            return 1;
        }
        return -1;
    }
}
