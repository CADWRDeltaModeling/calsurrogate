# calsurrogate
calsurrogate is the plug-in library for implementing surrogates with wrappers, with TensorFlow being the prototype implementation 

calsurrogate is part of an associated suite of tools
                                                                
```mermaid

%%{init: { "themeVariables": { "fontSize": "24px" } }}%%
flowchart LR
    subgraph Model Simulations 
    doe[Design of experiments] ---> indsm2@{ shape: docs, label: "DSM2 Inputs"}
	doe ---> inschism@{shape: docs, label: "SCHISM Inputs"}
	doe ---> inrma@{shape: docs, label: "RMA Inputs"}
    indsm2 ---> dsm2[DSM2]
    inschism ---> schism[SCHISM]
    inrma ---> rma[RMA]
	dsm2 ---> cassdata@{shape: cyl, label: "casanntra\n/data"}
	schism --->cassdata
	rma ---> cassdata
    end

    subgraph ANN Training
    cassdata ---> casanntra@{shape: rect, label: "cassantra \nANN training library\Python"}
    casanntra --training--> tf[[TensorFlow 
                     Saved Model]]
    end

    subgraph CalSim Application
    tf --> calsur@{shape: rect, label: "calsurrogate\nJava library"}
    wresl[WRESL new linearization] --revised--> CalSim
    CalSim o--plugin jar--o calsur
    wresl ---> calsur
    end

```	

The calsurrogate project repo is [here]([http://example.com/](https://github.com/CADWRDeltaModeling/casanntra) "Title")
An [example CalSim study]([http://example.com/](https://github.com/CADWRDeltaModeling/calsurrogate-test) "Title") is with the new surrogates. This example is not suitable for production study.

Notes:
# Repo for main example analogous to DCR
# Sequester the materials for eliminating NDOI and for making D-1641 small/large

Todo:
# Eliminate reduced_calls files, both at the station and the ANN level. 
# Fix MRDO implementations. Use required_flow instead.
# Fix X2 and find Fall X2
# Deal with all the extra stations which we do/don't have implemented.









