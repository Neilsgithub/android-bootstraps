package info.xiequan.androidbootstraps.constant;

/**
 * Created by Wilson
 */
public enum CacheTime {
    NONE(0), HALF_MINUTE(30), ONE_MINUTE(1 * 60), FIVE_MINUTE(5 * 60), TEN_MINUTE(10 * 60), FIFTEEN_MINUTE(15 * 60), HALF_HOUR(30 * 60), HOUR(60 * 60), DAY(24 * 60 * 60), WEEK(7 * 24 * 60 * 60);

    private int time;//seconds

    CacheTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

}
