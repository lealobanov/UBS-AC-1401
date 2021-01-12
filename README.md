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

Thus, I decided to proceed with a hashtable-based implementation: the main advantages include constant time look-up, addition, and removal operations, as well as capacity for scalability as more instruments and markets are introduced to the calculator. 


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

