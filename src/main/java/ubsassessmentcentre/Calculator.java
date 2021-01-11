package ubsassessmentcentre;

public interface Calculator {
//Return VWAP 2-way price, for the instrument of the passed MarketUpdate
TwoWayPrice applyMarketUpdate(final MarketUpdate twoWayMarketPrice);
}