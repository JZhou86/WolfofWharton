package hwk2.cis350.upenn.edu.wolfofwharton;

/**
 * Created by Jeffrey on 2/22/2018.
 */

import java.util.Date;


public class DailyInfoActivity {

    //daily info of a stock upon retrieving httprequest()
    private final Date dateTime;
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double adjustedClose;
    private final double volume;
    private final double dividendAmount;
    private final double splitCoefficient;

    //constructor for each stock
    public DailyInfoActivity(Date dateTime, double open,
                             double high,
                             double low,
                             double close,
                             double adjustedClose,
                             double volume,
                             double dividendAmount,
                             double splitCoefficient) {

        //initialize all relevant stock info
        this.dateTime = dateTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjustedClose = adjustedClose;
        this.volume = volume;
        this.dividendAmount = dividendAmount;
        this.splitCoefficient = splitCoefficient;
    }

    //SECOND CONSTRUCTOR
    public DailyInfoActivity(Date dateTime,
                             double open,
                             double high,
                             double low,
                             double close,
                             double adjustedClose,
                             double volume,
                             double dividendAmount) {

        //information to be displayed in StockInfoScreenActivity

        this.dateTime = dateTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjustedClose = adjustedClose;
        this.volume = volume;
        this.dividendAmount = dividendAmount;
        this.splitCoefficient = 0;
    }

    //THIRD CONSTRUCTOR
    public DailyInfoActivity(Date dateTime,
                             double open,
                             double high,
                             double low,
                             double close,
                             double volume) {

        this.dateTime = dateTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjustedClose = 0;
        this.volume = volume;
        this.dividendAmount = 0;
        this.splitCoefficient = 0;
    }

    //GETTERS AND SETTERS TO ACCESS/CHANGE VARIOUS INFO

    public Date getDateTime() {
        return dateTime;
    }

    public double getOpen() {

        return open;

    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getAdjustedClose() {

        return adjustedClose;

    }
    public double getVolume() {

        return volume;

    }
    public double getDividendAmount() {

        return dividendAmount;
    }

    public double getSplitCoefficient() {

        return splitCoefficient;

    }

}
