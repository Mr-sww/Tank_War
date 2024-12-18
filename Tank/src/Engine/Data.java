package Engine;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Data implements Serializable {
    private String map;
    private String level;
    private String date;
    private long duration; // ��ʱ����λΪ����

    public Data(String map, String level, long duration, String date) {
        this.map = map;
        this.level = level;
        this.duration = duration;
        this.date = date;
    }

    @Override
    public String toString() {
        return "��ͼ: " + map + ", �ȼ�: " + level + ", ��ʱ: " + (duration / 1000.0) + "��, ����: " + date;
    }

    public String getRecord() {
        return map + "," + level + "," + duration + "," + date; // ���ڱ��浽�ļ�
    }
}