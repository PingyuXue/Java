package Stock;


import Stock.POJO.TotalStockInfo;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//sealed
public final class SPY_history {

    static Logger log = Logger.getLogger(String.valueOf(SPY_history.class));

    public static void main(String args[]){

        // get total benefit from 2000,2012
        TotalStockInfo totalStockInfo = buySPYMonthly(2009,2012);
        TotalStockInfo totalStockInfo1 = buySPYMonthly(2000,2007);
        totalStockInfo.combineTotalStockInfo(totalStockInfo1);
        System.out.println("1 : benefit:"+ totalStockInfo.getTotalBenefitString());


        TotalStockInfo totalStockInfo3 = buySPYMonthly(2000,2012);
        System.out.println("3 : benefit:"+ totalStockInfo3.getTotalBenefitString());
    }

    /**
     * Main API
     * description :
     * montly buy one share, avg year
     * @param startMonth
     * @param endMonth
     * @return
     */
    public static TotalStockInfo buySPYMonthly(int startMonth, int endMonth){

        List<String> buyInPrices = getSpecificYearRangPrice_Monthly(startMonth,endMonth);
        TotalStockInfo totalStockInfo = new TotalStockInfo();

        totalStockInfo.setTotal_share(buyInPrices.size());
        totalStockInfo.setCurrent_price(Double.parseDouble(buyInPrices.get(buyInPrices.size()-1)));
        totalStockInfo.setTotal_cost(getTotalBuyPrice(buyInPrices));
        totalStockInfo.setAvg_cost_of_each_share();
        totalStockInfo.getTotalBenefit();
        return  totalStockInfo;
    }

    /**
     * Buy more SPY in bad years
     * @param startMonth
     * @param endMonth
     * @return
     */
    // TODO FIX this method,
//    public static TotalStockInfo buySPY_BadYear(int startMonth, int endMonth){
//
//        List<String> buyInPrices = getSpecificYearRangPrice_Monthly(startMonth,endMonth);
//        TotalStockInfo totalStockInfo = new TotalStockInfo();
//
//        List<String> buyMoreSharesPrices = buyMoreShareBadYears(buyInPrices)；
//
//        totalStockInfo.setTotal_share(buyMoreSharesPrices.size());
//        totalStockInfo.setCurrent_price(Double.parseDouble(buyMoreSharesPrices.get(buyInPrices.size()-1)));
//        totalStockInfo.setTotal_cost(getTotalBuyPrice(buyMoreSharesPrices));
//        totalStockInfo.setAvg_cost_of_each_share();
//        totalStockInfo.getTotalBenefit();
//        return  totalStockInfo;
//    }

    private static List<String> buyMoreShareBadYears(List<String> buyInPrices) {
//        List<String> shares = new ArrayList<>();
//
//        for ( int i = 0; i < buyInPrices.size(); i++){
//
//        }

        return null;
    }


    private static List<String[]> getHistoryPriceFrom_1994_2023(){
        String filePath = "/Users/jamesxue/Desktop/git/file/";
        String fileName = "SPY1994-2023_montly.csv";

        try{
            FileReader fileReader = new FileReader(filePath + fileName);



            CSVReader reader = new CSVReader(fileReader);
            List<String[]> res = reader.readAll();
            return res;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }


    //todo : add maven spotless check.
    private static List<String> getSpecificYearRangPrice_Monthly(int startYear, int endYear){

        List<String []> all = getHistoryPriceFrom_1994_2023();
        if( validateYearRange(startYear,endYear)){
            return all.stream().skip(1).filter(strings -> Integer.valueOf(strings[0].substring(0,4))>=startYear && Integer.valueOf(strings[0].substring(0,4))<=endYear).map(strings -> strings[1]).collect(Collectors.toList());
        }else {
            // return default value : from 1994 to 2023
            return all.stream().skip(1).map(strings -> strings[1]).collect(Collectors.toList());
        }
    }

    private static boolean validateYearRange(int startYear, int endYear) {
        if( startYear < 1993 || endYear> 2023){
            return false;
        }
        return true;
    }

    private static int getTotalShareCount(List<String> prices){
        return prices.size();
    }

    private static double getTotalBuyPrice(List<String> prices){
        return prices.stream().map(string -> Double.parseDouble(string)).collect(Collectors.summingDouble(Double::doubleValue));
    }


    // fixme : incorrect method
    private static double getTotalShare(List<String> prices){
        List<Double> allPrice = prices.stream().map(string -> Double.parseDouble(string)).collect(Collectors.toList());

        double curShare = 0;
        double curPrice = 0;
        double avgRate = 0.0016; // 0.16%
        double totalnewShare = 0;
        double totalShare = allPrice.size();


        for ( int i = 0; i < allPrice.size(); i++ ){
            // quarter
            double curPayBack = 0;
            double payBack_ReInvestmentShare = 0;
            if( i % 3 ==0){
                curShare = i;
                curPrice = allPrice.get(i);
                curPayBack = curPrice * avgRate * curShare;
                payBack_ReInvestmentShare = curPayBack/curPrice;
                totalShare += payBack_ReInvestmentShare;

                totalnewShare += payBack_ReInvestmentShare;
            }
        }

        return 0;
    }


}
