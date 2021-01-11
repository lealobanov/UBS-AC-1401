package ubsassessmentcentre;

// Import the HashMap class
import java.util.HashMap;

// VWAP 2-way price
    //Bid = Sum(Market Bid Price * Market Bid Amount)/ Sum(Market Bid Amount)
    //Offer = Sum(Market Offer Price * Market Offer Amount)/ Sum(Market Offer Amount)


//Implement the Calculator interface
public class VWAPCalculator implements Calculator {

    //Call initialization methods 
    //Instrument-individual market VWAPS
    private Map<PriorityTuple, VwapComputation> IM_vwaps = initializeIMpairs();
    //Instrument-market aggregate VWAPS
    private Map<Instrument, VwapComputation> aggregate_vwaps = initializeInstrumentMarketAggregates();

    //Initialize instrument-market mapping
    private Map<PriorityTuple, VwapComputation> initializeIMpairs() {
		//Initialize data structure to store VWAP values - per instrument, per market
        Map<PriorityTuple, VwapComputation> IM_vwaps = new HashMap<>();

		//Populate Instrument-Market pairs
        for(Instrument instrument : Instrument.values() ) {
            for (Market market : Market.values()) {
                add_update_IMpair(instrument, market, new VwapComputation());
            }
        }
		return IM_vwaps;
	}

    //Methods to add and retrieve entries from the tuple key hashmap
    // Add entry
    public static void add_update_IMpair(Instrument i, Market m, VwapComputation v) {
    if (null == i || null == m || null == v) {
        throw new IllegalArgumentException("Instrument, Market, and corresponding VwapComputation must be defined.");
    }
    IM_vwaps.put(new PriorityTuple(i, m), v);
    }

    // Get entry
    public static VwapComputation get_IMpair(Instrument i, Market m) {
        try {
        return IM_vwaps.get(new PriorityTuple(i, m));
        } catch (Exception e) {
            //Catch errors encountered when retrieving value associated with Instrument-Market tuple
            return ("Error retrieving Instrument-Market tuple.");
        }
    
    }

    //Initialize instrument-market aggregate mapping
    private Map<Instrument, VwapComputation> initializeInstrumentMarketAggregates() {

		//Initialize data structure to store aggregate VWAP values - per instrument only
        Map<Instrument, VwapComputation> aggregate_vwaps = new HashMap<>();

		//Populate Instrument-marketAggregate pairs
        for(Instrument instrument : Instrument.values() ) {
            aggregate_vwaps.put(instrument, new VwapComputation());
        }   
		return aggregate_vwaps;
	}

    //Structure to store numerical values, state needed for VWAP computation
     private class VwapComputation {
		private double SumBPbyBA = 0;
		private double SumBA = 0;
		private double SumOPbyOA = 0;
		private double SumOA = 0;
        //By default the state of the calculated VWAP two-way price is FIRM; this is updated to INDICATIVE if any one market update encountered is indicative.
        private State VWAPstate = FIRM;
	}

    TwoWayPrice applyMarketUpdate(final MarketUpdate twoWayMarketPrice) {

    //Exception handling - check for invalid or missing inputs

    //No market provided
    if (twoWayMarketPrice.getMarket() == null)  {
        throw new IllegalArgumentException("No market specified.");
    } else if (){
    //Market not in pre-defined enum or not of type Market
    } else {
        Market market = twoWayMarketPrice.getMarket();
    }

    //No two-way price object passed as an argument
    if (twoWayMarketPrice.getTwoWayPrice() == null) {
        throw new IllegalArgumentException("No TwoWayPrice object associated with this market update.");
    } else {
        TwoWayPrice twoWayPriceObj = twoWayMarketPrice.getTwoWayPrice();
    }
     
    //No instrument specified
    if (twoWayPriceObj.getInstrument() == null) {
        throw new IllegalArgumentException("No instrument specified.");
    } 
    else if (){
    //Instrument not in pre-defined enum or not of type Instrument
    } else {
      Instrument instrument = twoWayPriceObj.getInstrument();
    }

    //Two-way price missing state, bid price, bid amount, offer price, or offer amount attributes needed to perform VWAP computations
    if ((twoWayPriceObj.getState() == null) || 
        (twoWayPriceObj.getBidPrice() == null) ||
        (twoWayPriceObj.getOfferAmount() == null) ||
        (twoWayPriceObj.getOfferPrice() == null) ||
        (twoWayPriceObj.getBidAmount() == null)) {

        throw new IllegalArgumentException("Specified TwoWayPrice missing state, bid price, bid amount, offer price, or offer amount attributes. Cannot compute market update.");
    } 
    //Type-check attributes in two-way price: State or double

    //Update VWAP computations per market, per instrument
    VwapComputation current_vwap = get_IMpair(instrument, market);
        //Update bid values
        if (twoWayPriceObj.getBidAmount() != 0) {
			current_vwap.SumBPbyBA += (twoWayPriceObj.getBidAmount() * twoWayPriceObj.getBidPrice());
			current_vwap.SumBA += twoWayPriceObj.getBidAmount();
		}
        //Update offer values 
        if (twoWayPriceObj.getOfferAmount() != 0) {
			current_vwap.SumOPbyOA += (twoWayPriceObj.getOfferAmount() * twoWayPriceObj.getOfferPrice());
			current_vwap.SumOA += twoWayPriceObj.getOfferAmount();
		}
        //Update state - if an indicative market update is encountered at least once during the time series, state is updated to INDICATIVE
        if (twoWayPriceObj.getState() == INDICATIVE) {
            current_vwap.VWAPstate = INDICATIVE;
        }
        add_update_IMpair(instrument, market, current_vwap);

    //Update VWAP computations per instrument, using market aggregate
    VwapComputation current_aggregate_vwap = aggregate_vwaps.get(instrument);
        //Update bid values
        if (twoWayPriceObj.getBidAmount() != 0) {
			current_aggregate_vwap.SumBPbyBA   += twoWayPriceObj.getBidAmount() * twoWayPriceObj.getBidPrice();
			current_aggregate_vwap.SumBA += twoWayPriceObj.getBidAmount();
		}
        //Update offer values 
        if (twoWayPriceObj.getOfferAmount() != 0) {
			current_aggregate_vwap.SumOPbyOA   += price.getOfferAmount() * price.getOfferPrice();
			current_aggregate_vwap.SumOA += price.getOfferAmount();
		}
        //Update state - if an indicative market update is encountered at least once during the time series, state is updated to INDICATIVE
        if (twoWayPriceObj.getState() == INDICATIVE) {
            current_aggregate_vwap.VWAPstate = INDICATIVE;
        }
        aggregate_vwaps.put(instrument, current_aggregate_vwap);

    //Getter methods for VWAP computation
    private double getBA(VwapAsFraction vwap) {
		return vwap.SumBA;
	}

	private double getOA(VwapAsFraction vwap) {
		return vwap.SumOA;
	}

    private double getBidVWAP(VwapAsFraction vwap) {
        //Check for division by 0
		if (vwap.SumBA == 0) {
            return 0; 
        }
		return vwap.SumBPbyBA / vwap.SumBA;
	}

    private double getOfferVWAP(VwapAsFraction vwap) {
         //Check for division by 0
		if (vwap.SumOA == 0) {
            return 0;
        }
		return vwap.SumOPbyOA / vwap.SumOA;
	}
	
    //Return an instance of TwoWayPrice containing the final VWAP computation
    //In a more robust implementation, a flag can be passed to the applyMarketUpdate to indicate whether per market or marget aggregate VWAP is desired.

    //Here, I return only per instrument - per market VWAP as the task specification does not allow for changes to the supplied interfaces.
    //If a boolean flag were passed as a second arguement to applyMarketUpdate, (eg. ) , the return statement can be modified as follows: 
    
    /*
    if (useAggregateFlag == True) {
        vwap = current_aggregate_vwap;
    } else {
        vwap = current_vwap
    }

    return new TwoWayPrice_Calc(
			instrument,
			vwap.VWAPstate,
			getBidVWAP(vwap),
			getBA(vwap),
			getOfferVWAP(vwap),
			getOA(vwap));
    */
    return new TwoWayPrice_Calc(
			instrument,
			current_vwap.VWAPstate,
			getBidVWAP(current_vwap),
			getBA(current_vwap),
			getOfferVWAP(current_vwap),
			getOA(current_vwap));
    }
}