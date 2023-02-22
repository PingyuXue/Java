package Stock.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalStockInfo {

    private double total_share;

    private double total_cost;

    private double total_benefit1;
    private double total_benefit2;
    private double total_benefit3;

    private String totalBenefitString ;

    private double total_marketValue;

    private double avg_cost_of_each_share;

    private double current_price;

    public void getTotalBenefit(){
        total_benefit1 = ((current_price - avg_cost_of_each_share ) * total_share) / total_cost;
        total_benefit2 = ( current_price * total_share -total_cost ) / total_cost ;
        total_benefit3 = ( current_price - avg_cost_of_each_share)/avg_cost_of_each_share;

        totalBenefitString = new StringBuilder(String.valueOf(total_benefit3 * 100)).append("%").toString();
        total_marketValue = current_price * total_share;
//        return new double[]{total_benefit1, total_benefit2};
    }

    public void setAvg_cost_of_each_share(){
        this.avg_cost_of_each_share = total_cost/ total_share;
    }


    public void combineTotalStockInfo(TotalStockInfo newTotalStockInfo){
        this.total_share += newTotalStockInfo.total_share;
        this.total_cost += newTotalStockInfo.total_cost;
        this.avg_cost_of_each_share = this.total_cost/ this.total_share;

        this.current_price = newTotalStockInfo.current_price;

        getTotalBenefit();


    }
}
