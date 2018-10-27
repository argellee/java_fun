package date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * * SimpleDateFormat
 * <p>
 * * Date formats are not synchronized.
 * * It is recommended to create separate format instances for each thread.
 * * If multiple threads access a format concurrently, it must be synchronized
 * * externally.
 * <p>
 * SimpleDateFormat并发隐患及其解决
 * http://tech.lede.com/2017/04/28/rd/server/SimpleDateFormatConcurrentDanger/
 * <p>
 * 建议使用
 */
public class SimpleDateFormatTest implements Runnable {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String name;
    private String dateStr;

    public SimpleDateFormatTest(String name, String dateStr) {
        this.name = name;
        this.dateStr = dateStr;
    }


    @Override
    public void run() {
        Date date = null;
//        try{
//            synchronized (SimpleDateFormatTest.class){
//                date = simpleDateFormat.parse(dateStr);
//            }

//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        date = DateUtil.parseDate(dateStr, "yyyy-MM-dd");
        System.out.println(name + ":date: " + date);

    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new SimpleDateFormatTest("1", "2018-08-02"));

        Thread t2 = new Thread(new SimpleDateFormatTest("2", "2010-08-02"));
        Thread t3 = new Thread(new SimpleDateFormatTest("3", "2020-08-02"));
        Thread t4 = new Thread(new SimpleDateFormatTest("4", "2021-08-02"));

        t1.start();
//            try{
//                Thread.sleep(1000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
        t2.start();

        t3.start();
        t4.start();

    }
}
