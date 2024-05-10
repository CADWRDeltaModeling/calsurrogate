#!/usr/bin/env python
# -*- coding: utf-8 -* 

import pandas as pd
import matplotlib.pyplot as plt
from dms_datastore.read_ts import read_ts
from dms_datastore.read_multi import read_ts_repo
from vtools.functions.unit_conversions import M2FT
from vtools import *

sfprev = pd.read_csv("sf_stage_noaa_screened.csv",parse_dates=[[0,1]],index_col=0,sep="\s+",header = None)
sfprev = sfprev.rolling('25H',center=True).max() - sfprev.rolling('25H',center=True).min()
sfprev = sfprev.resample('1D').asfreq()
print("Frame:",sfprev)
sfprev.fillna(value=6.,inplace=True)

sfraw = read_ts("noaa_sffpx_*elev*.csv")
sfraw*= M2FT
sfrange = sfraw.rolling('25H',center=True).max() - sfraw.rolling('25H',center=True).min()
sfday = sfrange.resample('1D').asfreq()

sfday.fillna(value=6.,inplace=True)
sfday = sfday.loc['2017-01-02':]

ax = sfprev.plot()
sfday.plot(ax=ax)
plt.show()

sfday.columns=["range"]
sfprev.columns=["range"]

sf = ts_merge([sfprev,sfday])
sf.to_csv('sftide.csv',date_format='%Y-%m-%d',float_format = "%.2f",sep=",")



