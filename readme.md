## Illumio Technical Assessment
This project processes the input log file and determines a) Unique tag value and count - Tag is based on the combination of dsport and protocol as given in the lookup table, and b) Different combinations of port-protocol as seen in the log file and the number of times each combination occurs.

Running this project generates two files - port_protocol_combo_count_output.csv and tag_count_output.csv

Tested the project with different combinations of log flow inputs and lookup table combinations.

### Implementation
- Metadataservice reads the static metadata from port and lookup csv files. The following maps are computed:
  - Lookup Key - Tag Map: generated from reading the lookup table. A map of port-protocol combination and corresponding tag
  - Protocol Number - Port Map: Map to store the protocol number and corresponding protocol

- LogProcessorService will first call MetadataService to compute the maps. Next, it will read the input log csv file in a batches 100 rows. The input log file will be processed in batches and final count maps (tag and port-protocol combination) are updated.
- Protocol value is converted to lowercase from both lookup table and protocol map to account for case insensitive matches

- WriterService is called in the end to export/write the computed count maps into output csv files.


### Assumptions
   1. Port and Protocol number are 6th and 7th columns of the input log file respectively.
   2. Protocol number - protocol mapping exists in the mapping csv file. 
   3. All logs have a valid protocol number


### Future enhancements
   1. Metadata (lookup table and key-tag mapping) can be cached
   2. The count maps (tag and port-protocol combination) can be computed using parallel streams
   3. Input data validation before processing 

### Running the Project
This is a Gradle project, can be run using the commands below-

./gradlew build 

./gradlew run