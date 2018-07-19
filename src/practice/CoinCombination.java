package practice;

/**
 * 硬币组合问题 2角，5角，1角硬币，问有多少种组合可得到1块钱
 *
 * @author lijin9
 *
 */
public class CoinCombination {
	public static void main(String[] args) {
		System.out.println("2角---1角---5角");
		for(int i=0;i<=5;i++){
			for(int j=0;j<=10;j++){
				for(int k=0;k<=2;k++){
					int total = i*2 + j*1 + k*5;
					if(total == 10){
						System.out.println(i +"---"+j+"---"+k);
						break;
					}
				}
			}
		}
	}

}
