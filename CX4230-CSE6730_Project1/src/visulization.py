#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Feb  9 17:03:12 2020

@author: robin
"""
import argparse
import pandas as pd
import numpy as np
import scipy
import matplotlib.pyplot as plt 
import matplotlib.dates as md
import datetime as dt
import time
import matplotlib
from matplotlib.animation import FuncAnimation
import matplotlib.animation as animation
import matplotlib.patches as mpatches

parser = argparse.ArgumentParser(description='Simulation of events in Emerency call.')
parser.add_argument('data', type=argparse.FileType('r'), help='Data for Visulization.')
parser.add_argument('-v', action='store_true', help='Show Visulization video')
parser.add_argument('--output', type= str, default="visualization.mp4", help='Output the Visulization video')
parser.add_argument('-o', action = 'store_true', help='Output the Visulization video')
parser.add_argument('-p', action='store_true', help='Show Visulization picture')
args = parser.parse_args()


EventEngine = pd.read_csv(args.data, names=["Id", "Duration", "preState","BirthDate","OccurTime"])
EventEngine = EventEngine.drop(["OccurTime"], axis=1)
EventEngine = EventEngine.drop_duplicates()




EventEngine= EventEngine.replace('waiting', 'Wait/Answer')
EventEngine= EventEngine.replace('answering', 'Answer/Handle')
EventEngine= EventEngine.replace('handling', 'Handle/Finish')
EventEngine = EventEngine.sort_values(by=['BirthDate'])






def animate(i, xs, ys):
    if i < 55:         
        ys.append(EventEngine['Duration'].iloc[i])
        
        # Add x and y to lists
        time = EventEngine['BirthDate'].iloc[i]
        xs.append(time)
        
        # Limit x and y lists to 20 items
        xs = xs[-5:]
        ys = ys[-5:]
    
        # Draw x and y lists
        ax.clear()
        
        
        s=ax.stem(xs, ys, label = str(EventEngine['Id'].iloc[i]) + EventEngine['preState'].iloc[i] )
        plt.legend(handles=[s])
    # Format plot
    plt.subplots_adjust(bottom=0.30)
    plt.title('The visualization of Simulation model')
    plt.xlabel('Occur Time')
    plt.ylabel('Activity Duration Time')
    frame1 = plt.gca()
    




if args.v:  
    fig, ax = plt.subplots()
    xs =[]
    ys =[]
    red_patch = mpatches.Patch(color='red', label='Police1')
    blue_patch = mpatches.Patch(color='blue', label='Police2')
    yellow_patch = mpatches.Patch(color='yellow', label='Police3')
    ani = animation.FuncAnimation(fig, animate, fargs=(xs, ys), interval=1000)
    
    
    plt.show()
    if args.o:
        Writer = animation.writers['ffmpeg']
        writer = Writer(fps=10, metadata=dict(artist='Me'), bitrate=1800) 
        ani.save(args.output, writer=writer)
        
elif args.p:
    ys = EventEngine['Duration'].tolist()
    xs = EventEngine['BirthDate'].tolist()
    print(len(ys))
    plt.stem(xs, ys, use_line_collection=True)
    frame1 = plt.gca()
    plt.xticks([])
    xtick = [xs[0]]
    for i in range(len(xs)):
       xtick.append(' ')
    xtick.append(xs[-1]) 
    plt.xticks(np.arange(len(xtick)), xtick)
    plt.title('The visualization of Simulation model')
    plt.xlabel('Occur Time')
    plt.ylabel('Activity Duration Time')
    plt.savefig('visualization.png')
    plt.show()
