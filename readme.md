1. Implemetation:
- ReaderService contains a static block which executes when the project is run. This will read and store the following static metadata:
  - Lookup Key - Tag Map: generated from reading the lookup table. A map of port-protocol combination and corresponding tag
  - Protocol Number - Port Map: Map to store the protocol number and corresponding protocol

- LogProcessorSevice will read the input log csv file is read in a batches 100 rows. It will maintain count maps for the given requirements and update the maps   
Assumptions:
1. Port and Protocol number are 6th and 7th columns of the log file respectively.
2. Protocol number and protocol mapping exists in the mapping csv file. 
3. All logs have a valid protocol number

Future enhancements:
1. lookup table and key-tag mapping can be cached
2. determine the two counts (tag and port-protocol combination) using parallel streams