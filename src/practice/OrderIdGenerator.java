package practice;


import org.apache.commons.lang.math.RandomUtils;

import java.util.Objects;

/**
 * Created by lijin9 on 2016/8/17.
 */
public class OrderIdGenerator {

    private final Integer MOD = 10;

    //失败最大重试次数
    private final int MAX_RETRY_COUNT = 3;





    public Long nextOrderId() throws Exception {

        Long key = Long.valueOf(RandomUtils.nextInt(100) % MOD);
        Long id = null;
        int retryCount = 0;
        while(retryCount < MAX_RETRY_COUNT) {
            try {
                id = innerNextOrderId(key);
                break;
            } catch (Exception e) {
                id = null;
                key = (key + 1) % MOD;
                retryCount++;
            }
        }

        if(id == null) {
            throw new Exception("order id generator error.");
        }
        return id;
    }

    private Long innerNextOrderId(Long key) {
        int skip = RandomUtils.nextInt(3);
        Long id = null;
        for(int i = 0; i <= skip; i++) {
            while(true) {
                //id = sequences.get("order_" + key);
                if (Objects.equals(id % MOD, key)) {
                    break;
                }
            }
        }

        return id;
    }

    public static void main(String[] args) throws Exception{

        //Long id = new OrderIdGenerator().nextOrderId();
        //System.out.println(id);
        int a = 0;
        for(int i=0;i<3;i++){
            while(true){
               System.out.print(i);
                a = i;
                    break;
            }
        }
        System.out.print(a + "");

//        System.out.print("a=====" +a+""+b+ "======b"+ "====" +c);

    }


}
