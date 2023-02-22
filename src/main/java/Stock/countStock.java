package Stock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class countStock {

    public static StockInfo stockInfo = new StockInfo();
    static class StockInfo{
        double avgBuyPrice;  // 每股 平均购买价格
        int shareCounts;  // 股票数量
        double totalCost; // 账户购买总支出
        double accountBenefit;  // 账户盈亏

        List<Double> sharePrice ;

        public StockInfo(){
            avgBuyPrice = 0;
            shareCounts = 0;
            totalCost = 0;
            accountBenefit = 0;
            sharePrice =new ArrayList<>();
        }
    }

    public static void main(String args[]){

        // 衰退5年
//        StockInfo stockInfo = badYearShareBuy(470,36,10,3,2,0);
//        System.out.printf("account benefit : %f, total cost : %f, share %d", stockInfo.accountBenefit,stockInfo.totalCost,stockInfo.shareCounts);
//        System.out.println();
//        System.out.println("*****************");
//
//        stockInfo = goodYearShareBuy(stockInfo.sharePrice.get(36-1),24,3,0,6,1);
//        System.out.printf("account benefit : %f, total cost : %f, share %d", stockInfo.accountBenefit,stockInfo.totalCost,stockInfo.shareCounts);
//        System.out.println("");


        StockInfo stockInfo2 =  historySPY500_2000_2007();
        System.out.printf("avg price : %f, account benefit : %f, total cost : %f, share %d",stockInfo2.avgBuyPrice, stockInfo2.accountBenefit,stockInfo2.totalCost,stockInfo2.shareCounts);
        System.out.println("");
    }


    /*
    * method : 经济衰退，每个月定投一股 spy500
    * 1.spy500 第一股购买价格： 470
    * 2.经济衰退周期, n , n 的单位月月
    * 3.预计每月跌幅 为： 5-6%
    * 4.预计每月增幅 为 ：1-2%
    * 5.返回 stockInfo
     * */

    public static StockInfo badYearShareBuy(double startPrice, int badYearMonth,
                                            int downMaxRate, int downMinRate, int growMaxRate, int growMinRate){

        double curPrice = startPrice;
        int maxGrow = (int) Math.min(10, 0.2*badYearMonth); //
        stockInfo.shareCounts++;
        stockInfo.totalCost+= curPrice;

        for ( int i = 0; i< badYearMonth; i++){
            double down = ThreadLocalRandom.current().nextDouble(downMinRate,downMaxRate)*0.01;
            double grow = ThreadLocalRandom.current().nextDouble(growMinRate,growMaxRate)*0.01;

            if( ThreadLocalRandom.current().nextInt(1,10)>5 && maxGrow>=0){
                curPrice = curPrice*(1 + grow);
                maxGrow--;
                System.out.printf("curPice: %f, grow :%f , left maxGrow : %d", curPrice, grow*100, maxGrow);
            }else {
                curPrice= curPrice*(1 - down);
                System.out.printf("curPice: %f, down :%f ", curPrice, down*100);
            }
            System.out.println("");
            stockInfo.sharePrice.add(curPrice);
            stockInfo.shareCounts++;
            stockInfo.totalCost += curPrice;
        }

        stockInfo.avgBuyPrice = stockInfo.totalCost/ stockInfo.shareCounts;
        stockInfo.accountBenefit = 100* ( (stockInfo.shareCounts * curPrice)  - stockInfo.totalCost ) / stockInfo.totalCost;
        System.out.println("Share benefit overview : " + (curPrice - startPrice)/startPrice);
        return stockInfo;
    }

    public static StockInfo goodYearShareBuy(double startPrice, int goodYearMonth,
                                            int downMaxRate, int downMinRate, int growMaxRate, int growMinRate){
        double curPrice = startPrice;
        int maxDown = (int) Math.min(10, 0.2*goodYearMonth); //
        stockInfo.shareCounts++;
        stockInfo.totalCost+= curPrice;

        for ( int i = 0; i< goodYearMonth; i++){
            double down = ThreadLocalRandom.current().nextDouble(downMinRate,downMaxRate)*0.01;
            double grow = ThreadLocalRandom.current().nextDouble(growMinRate,growMaxRate)*0.01;

            if( ThreadLocalRandom.current().nextInt(1,10)>5 && down>=0){
                curPrice = curPrice*(1 + down);
                maxDown--;
                System.out.printf("curPice: %f, down :%f , left maxDown : %d", curPrice, down*100, maxDown);
            }else {
                curPrice= curPrice*(1 - grow);
                System.out.printf("curPice: %f, grow :%f ", curPrice, grow*100);
            }
            System.out.println("");
            stockInfo.sharePrice.add(curPrice);
            stockInfo.shareCounts++;
            stockInfo.totalCost += curPrice;
        }

        stockInfo.avgBuyPrice = stockInfo.totalCost/ stockInfo.shareCounts;
        stockInfo.accountBenefit = 100* ( (stockInfo.shareCounts * curPrice)  - stockInfo.totalCost ) / stockInfo.totalCost;
        System.out.println("Share benefit overview : " + (curPrice - startPrice)/startPrice);
        return stockInfo;
    }


    public static StockInfo historySPY500_2000_2007(){
        double [] sharePriceDownMonthly = {1406.95,
                1444.80,
                1424.16,
                1416.42,
                1388.64,
                1363.38,
                1317.74,
                1287.15,
                1260.24,
                1253.17,
                1290.01,
                1302.17,
                1293.74,
                1276.65,
                1278.73,
                1262.07,
                1237.37,
                1191.96,
                1225.92,
                1224.27,
                1222.24,
                1202.25,
                1178.28,
                1164.43,
                1194.90,
                1199.63,
                1181.41,
                1199.21,
                1168.94,
                1117.21,
                1117.66,
                1088.94,
                1105.85,
                1132.76,
                1102.78,
                1133.36,
                1123.98,
                1143.36,
                1132.52,
                1080.64,
                1049.90,
                1038.73,
                1019.44,
                989.53,
                992.54,
                988.00,
                935.96,
                890.03,
                846.63,
                837.03,
                895.84,
                899.18,
                909.93,
                854.63,
                867.81,
                912.55,
                903.59};

        double [] sharePriceTotalMonthly = {1406.95,
                1444.80,
                1424.16,
                1416.42,
                1388.64,
                1363.38,
                1317.74,
                1287.15,
                1260.24,
                1253.17,
                1290.01,
                1302.17,
                1293.74,
                1276.65,
                1278.73,
                1262.07,
                1237.37,
                1191.96,
                1225.92,
                1224.27,
                122.24,
                1202.25,
                1178.28,
                1164.43,
                1194.90,
                1199.63,
                1181.41,
                1199.21,
                1168.94,
                1117.21,
                1117.66,
                1088.94,
                1105.85,
                1132.76,
                1102.78,
                1133.36,
                1123.98,
                1143.36,
                1132.52,
                1080.64,
                1049.90,
                1038.73,
                1019.44,
                989.53,
                992.54,
                988.00,
                935.96,
                890.03,
                846.63,
                837.03,
                895.84,
                899.18,
                909.93,
                854.63,
                867.81,
                912.55,
                903.59,
                1014.02,
                1079.25,
                1111.93,
                1153.79,
                1100.67,
                1140.21,
                1144.93,
                1129.68,
                1076.59,
                1044.64,
                1178.50,
                1204.45,
                1238.71,
                1270.37,
                1189.84,
                1185.85,
                1305.75,
                1335.63,
                1330.93,
                1378.04,
                1390.14,
                1468.05,
                1485.46,
                1473.00};

        int month = sharePriceDownMonthly.length;
        stockInfo.totalCost = Arrays.stream(sharePriceDownMonthly).sum();
        stockInfo.shareCounts =month;
        stockInfo.avgBuyPrice = stockInfo.totalCost/ stockInfo.shareCounts;

        stockInfo.accountBenefit =( sharePriceDownMonthly[month -1]/ stockInfo.avgBuyPrice )* 100;

        return stockInfo;
    }

}
