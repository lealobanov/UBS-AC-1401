# Coding Exercise - UBS Assessment Centre 14/01

**Set task:**

Design an implementation of the provided Calculator interface, such that, on calling the applyMarketUpdate method, the VWAP two-way price is returned for the associated instrument. 

The VWAP two-way price for an instrument is defined as:

Bid = Sum(Market Bid Price * Market Bid Amount)/ Sum(Market Bid Amount)

Offer = Sum(Market Offer Price * Market Offer Amount)/ Sum(Market Offer Amount)

Sample interfaces and enums were provided as a starting point for the implementation. 

**Implementation details to note:**

In completing the set task, I made several assumptions about the nature of the VWAP calculation and expected formatting of program output. Additionally, in structuring the code, I made several key design choices, placing an emphasis on code modularity, reusability, and ease of future scalibility. These are discussed further below.

* Consideration of appropriate data structures and their efficiency: 2D nested array/matrix vs. hash table keyed by (instrument, market) tuple

Arrays are initialized as objects of fixed-size; may potentially cause issues if the number of markets and instruments is not fixed. If a new market or instrument were to be introduced after the program begins running and storage data structures are initialized, an error would result. Additionally, as we were instructed not to modify the supplied interface and enum structures, I did not add indexing capabilities to the Market or Instrument enums; hence, an array-based implementation would tend towards O(n) look-up time to locate a particular array element. As a 2D-array (matrix) would be required, this would require 2 array searches per instrument, market pair stored in the array. Even if indexing of markets and instruments were implemented, obscure market or instrument names in which naming does not correspond directly to array index (e.g. not Market1,2,3,...Instrument 1,2,3... etc.) would not allow for constant look up times as searching the array would be required.

Thus, I decided to proceed with a hashtable-based implementation: the main advantages include constant time O(1) look-up, addition, and removal operations, as well as capacity for scalability as more instruments and markets are introduced to the calculator. I assumed that in a more robust implementation, there will likely be a need to add new Markets and/or Instruments on the fly, and thus the proposed implementation should be able to handle such operations without error. A dynamically-sized hashtable allows for (market, instrument) pairs to be added or removed as needed, without comprimising program functionality. Additionally, unlike a 2D matrix, unnecessary (market, instrument) pairs (e.g. if an insturment is not traded on all markets in entered in the calculator) need not be intitialized in the hashtable implementation; this is more efficient in terms of space and avoids execution of redundant computations. The hashtable implementation is thus more more flexible in terms of scaling the code to include more (potentially varied) markets and instruments, as well introducing new features (e.g. different types of calculations, more complex Market and Instrument structures, etc.) to the program. 


* Consideration for market aggregate VWAP per instrument

It was unclear whether the desired VWAP two-way price should be returned for a per-instrument, per-market pairing or as an aggregate across all markets for a particular instrument. Thus, I developed an implementation which can support both types of calculations. As discussed previously, per-instrument/per-market VWAPs are stored in a hashtable keyed by (instrument, market) tuples. I have devised a second, simpler hashtable which stores strictly VWAPs as market aggregates: each instrument is mapped to a single VWAP value which is computed across all markets.

  * Introduction of 'flag' argument passed to applyMarketUpdate
  
  At present, the applyMarketUpdate method takes in a single argument, a MarketUpdate object; as per the task instructions, I have not modified this. However in realizing an implementation which would support both per-instrument/per-market VWAPs and instrument/market aggregate VWAPS, a second argument would need to be introduced. This second method argument would function as a boolean "flag" which would denote which type of VWAP is desired. This would require introducing an if/else statement to check for the presence of flag before returning the VWAP as a TwoWayPrice object (see code comments for a sample implementation). Alternatively, the program could be modified to return both computations. This is largely a matter of design preference; the computation and storage of calculations needed for both alternatives are already in place. 
  
* Exception handling and edge cases
  * Try/catch blocks to catch exceptions
  * Throw IllegalArgument exception in specific cases where supplied inputs are invalid or missing
  * Ensuring mathematically valid and avoiding redundant VWAP calculations
  * Return 0 when attempting to divide by 0
  * Do not perform unnecessary additions and multiplications when one of the arguments is 0, as this has no impact on the overall VWAP value
  
* Program implementation - main() method 
  * At present, the program has no valid entry point which instantiates a Calculator object and accepts a series of MarketUpdates
  * In developing an implementation, I assumed presence of some external program controller which will handle initialization of the Calculator object and manage a stream of MarketUpdates as they are fed into the calculator; thus I omitted this in my implementation. Additionally, some form of caching or database system would need to be implemented to avoid losing calculated values if the program is terminated. Ideally, the MarketUpdates would be serialized by date/time or an ordering index to maintain a sense of program state. This would allow for increased error handling to ensure that MarketUpdates are handled in the correct order (e.g. if they arise simultaneously), and that the VWAP returned is in fact reflective of the most recent MarketUpdate.
 
* Formatting of output 
  * Currently, a TwoWayPrice object is returned 

