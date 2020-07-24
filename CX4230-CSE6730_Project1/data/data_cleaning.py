#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  2 10:05:25 2019

@author: robin
"""
#AIzaSyDQwfXw28VkcWPR8TYpIMU0Sj5y-N6A1JU
import pandas as pd
import numpy as np


    
def clean_data(file_name):
    data = pd.read_csv(file_name)
    return data

filedate = input('clean data date (filename):')
file_name = input("Please input file name need clean:")
data = clean_data(filedate+'/'+file_name)
temp = data['Occur Date']
temp = pd.to_datetime(temp).dt.floor('d')
temp = temp.apply(lambda x: x.strftime('%Y%m%d%H'))
temp = temp.astype(str)
for i in range(len(temp)):
    if int(temp[i][:4]) < 2009:
        data = data.drop(i, axis=0)
col = ['Report Number', 'Occur Date','Occur Time', 'Beat', 'Location', 'UCR Literal', 'UCR #', 'IBR Code', 'Neighborhood', 'NPU', 'Latitude', 'Longitude' ]
condition = pd.DataFrame([data['Location'].isnull(), data['Neighborhood'].isnull(), pd.DataFrame([data['Longitude'].isnull(), data['Latitude'].isnull()]).any()]).all()
data = data[condition == False]
data_clean_p1 = data.loc[:, col]
array = []


def clean_col(data_clean_p1, col):
    for i in range(data_clean_p1.shape[0]):
        try:
            if np.isnan(data_clean_p1[col][i]) == True:
                data_clean_p1 = data_clean_p1.drop(i, axis=0)
        except:
            pass
    return data_clean_p1
    
for i in range(data_clean_p1.shape[0]):
    try:
        if len(data_clean_p1['Occur Time'][i]) < 4 or not str(data_clean_p1['Occur Time'][i]).isnumeric():
            data_clean_p1 = data_clean_p1.drop(i, axis=0)
        if np.isnan(data_clean_p1['Neighborhood'][i]):
            data_clean_p1['Neighborhood'][i] = 'other'
    except:
        pass 
cols = ['Report Number', 'Occur Date','Occur Time', 'Neighborhood']
data_clean_p1 = data_clean_p1.loc[:, cols]
date =  data_clean_p1['Occur Date'] 
date = pd.to_datetime(date).dt.floor('d')
date = date.apply(lambda x: x.strftime('%Y%m%d'))
time = data_clean_p1['Occur Time']
time = time.astype(str)
times = []
for i in time:
    while len(i) < 4:
        i =  '0' + i
    times.append(i) 
    

data_clean_p1.to_csv('sample'+filedate+'.csv')

