package Engine;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Data implements Serializable {
    private String map;
    private String level;
    private String date;
    private long duration; // 耗时，单位为毫秒

    public Data(String map, String level, long duration) {
        this.map = map;
        this.level = level;
        this.duration = duration;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "地图: " + map + ", 等级: " + level + ", 耗时: " + (duration / 1000.0) + "秒, 日期: " + date;
    }

    public String getRecord() {
        return map + "," + level + "," + duration + "," + date; // 用于保存到文件
    }
}