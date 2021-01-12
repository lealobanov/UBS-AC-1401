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
* Consideration for market aggregate VWAP per instrument
  * Introduction of 'flag' argument passed to applyMarketUpdate
  * Introduce if/else statement to check for presence of flag before returning VWAP
* Ensuring valid and avoiding redundant calculations
  * Try/catch
  * Throw exceptions
* Exception handling and edge cases
* Program implementation - main method 
  * Assume some external program controller; thus omitted in my implementation
* Formatting of output 

