package ubsassessmentcentre;

//Implement the the MarketUpdate interface
public class MarketUpdate_Calc implements MarketUpdate {

    //Initialize class attributes
    private final Market market;
	private final TwoWayPrice twoWayPrice;

    //Class constructor
	public MarketUpdate_Calc(Market market, TwoWayPrice twoWayPrice) {
		this.market = market;
		this.twoWayPrice = twoWayPrice;
	}

    //Getter methods for market and two-way price
	public Market getMarket() {
		return market;
	}

	public TwoWayPrice getTwoWayPrice() {
		return twoWayPrice;
	}
}