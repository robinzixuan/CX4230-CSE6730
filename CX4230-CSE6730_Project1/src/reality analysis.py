#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Feb 22 04:15:22 2020

@author: robin
"""
import argparse
import numpy as np
import pandas as pd
from datetime import datetime
import matplotlib.pyplot as plt 

parser = argparse.ArgumentParser(description='Simulation of events in Emerency call.')
parser.add_argument('data2', type=argparse.FileType('r'), help='Data for Realtime Dataset.')
parser.add_argument('-o1', action='store_true', help='get the plot all of the reality  data for confidence interval analysis')
parser.add_argument('-o2', action='store_true', help='get the plot approximate inside 30 mins of the reality data for confidence interval analysis')

args = parser.parse_args()

real = pd.read_csv(args.data2)


occurtime = real['Occur Time']
times = []
for i in occurtime:
    i = str(i)
    while len(str(i))< 4:
        i =  '0' + i
    i = i[0:2] + ":" +i[2:] + ":00"
    i = "1/1/1970 " + i
    times.append(i) 
s = pd.to_datetime(times)
s=pd.Series(s)


OccurDate = real['Occur Date']
OccurDate = pd.to_datetime(OccurDate).dt.floor('d')


finishtime = real['Finished Time']
times = []
for i in finishtime:
    i = str(i)
    while len(str(i))< 4:
        i =  '0' + i
    i = i[0:2] + ":" +i[2:] + ":00"
    i = "1/1/1970 " + i
    times.append(i) 
s1 = pd.to_datetime(times)
s1=pd.Series(s1)

FinishedDate = real['Finished Date']
FinishedDate = pd.to_datetime(FinishedDate).dt.floor('d')
data = []

for i in range(len(FinishedDate)):
    data.append((datetime.fromtimestamp(datetime.timestamp(s1[i]) + datetime.timestamp(FinishedDate.iloc[i])) - datetime.fromtimestamp(datetime.timestamp(s[i]) + datetime.timestamp(OccurDate.iloc[i]))).total_seconds())

if args.o2:
    data=pd.Series(data)
    data = data[data<1800]
    data = data.sort_values(ascending=True)
    plt.hist(data/60, bins=np.arange(min(data/60), max(data/60) + 3, 3), edgecolor="k")
    plt.title('Real Duration bar inside 30 mins')
    plt.xlabel('Duaration')
    plt.ylabel('Event Number')
    plt.savefig('real_30min.png')
    plt.clf()

if args.o1:
    data=pd.Series(data)
    data = data.sort_values(ascending=True)
    plt.hist(data/60, bins=np.arange(min(data/60), max(data/60) + 5, 5), edgecolor="k")
    plt.title('Real Duration bar')
    plt.xlabel('Duaration')
    plt.ylabel('Event Number')
    plt.savefig('real_total.png')
    plt.clf()


