package ubsassessmentcentre;

//Implement the TwoWayPrice interface
public class TwoWayPrice_Calc implements TwoWayPrice {

    //Initialize class attributes
    private final Instrument instrument;
	private final State state;
	private final double bidPrice;
	private final double offerAmount;
	private final double offerPrice;
	private final double bidAmount;
	
    //Class constructor
	public TwoWayPrice_Calc(Instrument instrument, State state, double bidPrice, double bidAmount, double offerPrice, double offerAmount) {
		this.instrument = instrument;
		this.state = state;
		this.bidPrice = bidPrice;
		this.offerAmount = offerAmount;
		this.offerPrice = offerPrice;
		this.bidAmount = bidAmount;
	}
 
    //Getter methods for class attributes
    public Instrument getInstrument() {
		return instrument;
	}

	public State getState() {
		return state;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public double getOfferAmount() {
		return offerAmount;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public double getBidAmount() {
		return bidAmount;
	}

}

