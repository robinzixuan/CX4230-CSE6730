#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Feb 17 23:37:42 2020
@author: robin
"""
import argparse
import numpy as np
import pandas as pd
from scipy import stats
from datetime import datetime
import matplotlib.pyplot as plt 


parser = argparse.ArgumentParser(description='Simulation of events in Emerency call.')
parser.add_argument('data', type=argparse.FileType('r'), help='Data for Visulization.')
parser.add_argument('-c', action='store_true', help='Show confidence interval')
parser.add_argument('--o1', action='store_true', help='get the plot of the data for confidence interval analysis')
parser.add_argument('-n', action='store_true', help='Show report number in January 2019')
parser.add_argument('--o2', action='store_true', help='get the plot of the data analysis for January 2019 Report')
args = parser.parse_args()
EventEngine = pd.read_csv(args.data, names=["Id", "Duration", "preState","BirthDate","OccurTime"])

WaitEngine = EventEngine[EventEngine['preState']== 'waiting']
AnswerEngine = EventEngine[EventEngine['preState']== 'answering']
handleEngine = EventEngine[EventEngine['preState']== 'handling']
Waitduration = WaitEngine['Duration']
Answerduration = AnswerEngine['Duration']
Handleduration = AnswerEngine['Duration']

if args.c:
    Waitduration = Waitduration.sort_values(ascending=True)
    Answerduration = Answerduration.sort_values(ascending=True)
    Handleduration = Handleduration.sort_values(ascending=True)
    print('Analysis the WaitEngine confidence interval:')
    conf_int_a = stats.t.interval(0.68, len(Waitduration)-1, loc=np.mean(np.array(Waitduration)), scale=np.std(Waitduration))
    print('{:0.1%} of the single draws of WaitEngine are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.68,conf_int_a[0],conf_int_a[1],((np.array(Waitduration) >= conf_int_a[0]) & (np.array(Waitduration) < conf_int_a[1])).sum()/float(len(Waitduration))))
    conf_int_a = stats.t.interval(0.90, len(Waitduration)-1, loc=np.mean(np.array(Waitduration)), scale=np.std(Waitduration))
    print('{:0.1%} of the single draws of WaitEngine are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.9,conf_int_a[0],conf_int_a[1],((np.array(Waitduration) >= conf_int_a[0]) & (np.array(Waitduration) < conf_int_a[1])).sum()/float(len(Waitduration))))
    conf_int_a = stats.t.interval(0.95, len(Waitduration)-1, loc=np.mean(np.array(Waitduration)), scale=np.std(Waitduration))
    print('{:0.1%} of the single draws of WaitEngine are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.95,conf_int_a[0],conf_int_a[1],((np.array(Waitduration) >= conf_int_a[0]) & (np.array(Waitduration) < conf_int_a[1])).sum()/float(len(Waitduration))))
    print("\n")
    print('Analysis the AnswerEngine confidence interval:')
    conf_int_a = stats.t.interval(0.68, len(Answerduration)-1, loc=np.mean(np.array(Answerduration)), scale=np.std(Answerduration))
    print('{:0.1%} of the single draws of AnswerEngine are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.68,conf_int_a[0],conf_int_a[1],((np.array(Answerduration) >= conf_int_a[0]) & (np.array(Answerduration) < conf_int_a[1])).sum()/float(len(Answerduration))))
    conf_int_a = stats.t.interval(0.90, len(Answerduration)-1, loc=np.mean(np.array(Answerduration)), scale=np.std(Answerduration))
    print('{:0.1%} of the single draws of AnswerEngine are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.9,conf_int_a[0],conf_int_a[1],((np.array(Answerduration) >= conf_int_a[0]) & (np.array(Answerduration) < conf_int_a[1])).sum()/float(len(Answerduration))))
    conf_int_a = stats.t.interval(0.95, len(Answerduration)-1, loc=np.mean(np.array(Answerduration)), scale=np.std(Answerduration))
    print('{:0.1%} of the single draws of AnswerEngine are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.95,conf_int_a[0],conf_int_a[1],((np.array(Answerduration) >= conf_int_a[0]) & (np.array(Answerduration) < conf_int_a[1])).sum()/float(len(Answerduration))))
    print("\n")
    print('Analysis the Handleduration confidence interval:')
    conf_int_a = stats.t.interval(0.68, len(Handleduration)-1, loc=np.mean(np.array(Handleduration)), scale=np.std(Handleduration))
    print('{:0.1%} of the single draws of Handleduration are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.68,conf_int_a[0],conf_int_a[1],((np.array(Handleduration) >= conf_int_a[0]) & (np.array(Handleduration) < conf_int_a[1])).sum()/float(len(Handleduration))))
    conf_int_a = stats.t.interval(0.90, len(Handleduration)-1, loc=np.mean(np.array(Handleduration)), scale=np.std(Handleduration))
    print('{:0.1%} of the single draws of Handleduration are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.9,conf_int_a[0],conf_int_a[1],((np.array(Handleduration) >= conf_int_a[0]) & (np.array(Handleduration) < conf_int_a[1])).sum()/float(len(Handleduration))))
    conf_int_a = stats.t.interval(0.95, len(Handleduration)-1, loc=np.mean(np.array(Handleduration)), scale=np.std(Handleduration))
    print('{:0.1%} of the single draws of Handleduration are in conf_int_a:({:0.4},{:0.4}), actually it is contain {:0.4%} of the single draws are in conf_int_a'.format(0.95,conf_int_a[0],conf_int_a[1],((np.array(Handleduration) >= conf_int_a[0]) & (np.array(Handleduration) < conf_int_a[1])).sum()/float(len(Handleduration))))
    if args.o1:
        plt.hist(Waitduration,  edgecolor="k")
        plt.title('WaitEngine Duration bar')
        plt.xlabel('Duaration')
        plt.ylabel('Event Number')
        plt.savefig('WaitEngine.png')
        plt.clf()
        plt.hist(Answerduration,  edgecolor="k")
        plt.title('AnswerEngine Duration bar')
        plt.xlabel('Duaration')
        plt.ylabel('Event Number')
        plt.savefig('AnswerEngine.png')
        plt.clf()
        plt.hist(Handleduration,  edgecolor="k")
        plt.title('HandleEngine Duration bar')
        plt.xlabel('Duaration')
        plt.ylabel('Event Number')
        plt.savefig('HandleEngine.png')
        plt.clf()
if args.n:
    AnsweringEngine = AnswerEngine.rename(columns={"Id": "Id", "Duration": "AnswerDuration", "preState":"Answer_preState","BirthDate":"AnswerBirthDate","OccurTime":"OccurTime"})
    HandlingEngine = handleEngine.rename(columns={"Id": "Id", "Duration": "HandleDuration", "preState": "HandlepreState","BirthDate":"HandleBirthDate","OccurTime":"OccurTime"})
    WaitingEngine = WaitEngine.rename(columns={"Id": "Id", "Duration": "WaitDuration", "preState": "WaitpreState","BirthDate":"WaitBirthDate","OccurTime":"OccurTime"})
    merged_AH = pd.merge(left=HandlingEngine,right=AnsweringEngine, left_on='Id', right_on='Id')
    merged_AH = pd.merge(left=merged_AH,right=WaitingEngine, left_on='Id', right_on='Id')
    merged_AH = merged_AH.drop_duplicates()
    duration = merged_AH['AnswerDuration'] + merged_AH['HandleDuration'] + merged_AH['WaitDuration']
    Occurtime = pd.to_datetime(merged_AH['OccurTime'])
    date = []
    for i in range(len(merged_AH)):
        date.append(datetime.fromtimestamp(duration[0] + datetime.timestamp(Occurtime.iloc[i])))
    
    date = pd.to_datetime(pd.Series(date)).dt.floor('d')
    date = date[date <= pd.to_datetime('2019/01/31')]
    date = date[date >= pd.to_datetime('2019/01/01')]
    unique, counts = np.unique(date, return_counts = True)
    x_pos = [i for i, _ in enumerate(unique)]
    if args.o2:
        plt.bar(x_pos, counts, color='green')
        plt.title('The report number of Every day in the January 2019')
        plt.xlabel('Day')
        plt.ylabel('Report Number')
        plt.savefig('JanuaryHandle.png')
        plt.clf()