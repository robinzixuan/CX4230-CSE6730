# Atlanta Eyes



This application is developed with Java SE7 (https://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html). 

The application uses Python 3.6 (https://www.python.org/download/releases/2.6/) as Simulation, with the package: Numpy and Matplotlib.

## Prepare

You need `clean data` and we provide the cleaned data for your test:

`sampleCOBRA-2019.csv`


If you went to clean data yourself, Then `cd ../data` into data directory, run 
```bash
data_cleaning.py
```
(which supported by Python 3.5+). Then you should input 2019 as year you need get for data and COBRA-2019-CVS.csv as the data clean target filename. 


## Build

For dev build, first you should run 
```bash
javac main.java
```
, then run following code with any police number you want input
```bash
java main
```
You will get some simulation text output file `Event.txt` and data for Visualization 
`EventEngine.csv`.


## Visualization
you can use pretrain result file `EventEngine.csv` (you can also run the `EventEngine1.csv` by get from simulation result of `sample2019.csv`)

The following commands are suppose to output video or picture, in the current directory. 

If you are in PACE system, you cannot output any images or videos because the PACE system cannot allow us to output any images or videos in terminal and give you the error when use the matilplot to output any images or videos but you can run the following command to test whether the visualization codes have error:
```bash
python visulization.py EventEngine.csv
```
When you run this code, you will not get any output.

If you want to output video or picture (which is not support on the PACE and you should run them in local system):
__1.__  If you want to  get the picture, run following command and the output will be stored in same folder, and you will get the default outfile named `visualization.png`
```bash
python visulization.py EventEngine.csv -p
```
__2.__  If you want to  get the video, run 
```bash
python visulization.py EventEngine.csv -v
```


## Analysis
you can use pretrain dataset `EventEngine.csv`
__1.__ If you want to make analysis data for confidence interval, run 
```bash
python analysis.py  EventEngine1.csv -c
```
__1a.__ And you can output the diagram for discription of the data for confidence interval analysis (not support on PACE),  run following command and the output will be stored in same folder, and you will get the default outfile named `AnswerEngine.png`, `HandleEngine.png`, `WaitEngine.png`
```bash
python analysis.py  EventEngine1.csv -c --o1
```
__2.__ If you want to make analysis of how many report case are handled in January 2019 and you need take 1-2 minutes to finish the process, run 
```bash
python analysis.py  EventEngine1.csv -n
```
__2a.__ And you can output the diagram for number of report case are handled in January 2019 (not support on PACE), run following command and the output will be stored in same folder, and you will get the default outfile named `JanuaryHandle.png`
```bash
python analysis.py  EventEngine1.csv -n --o2
```


## Reality Analysis (Not Support on PACE)
For the Reality analysis, please use the dataset `COBRA-2019-real.csv` and if you want to get all reality data's calling duration distrubution(need 3h), run following command and the output will be stored in same folder, and you will get the default outfile named `real_total.png`
```bash
python reality\ analysis.py  COBRA-2019-real.csv -o1
```

Because the time is so long and most data duration is inside the 30 mins, we also provide you a one for only 30 mins, run following command and the output will be stored in same folder, and you will get the default outfile named `real_30min.png`
```bash
python reality\ analysis.py  COBRA-2019-real.csv -o2
```
