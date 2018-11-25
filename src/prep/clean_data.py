#%%
import pandas as pd

#%%
master = pd.read_csv("../data/dirty/master.csv", error_bad_lines=False, engine='python', warn_bad_lines=False)
match = pd.read_csv("../data/dirty/match.csv", error_bad_lines=False, engine='python', warn_bad_lines=False)

#%%
master = master.astype('str')
match = match.astype('str')

#%%
master = master.replace('nan', '')
match = match.replace('nan', '')

#%%
master.to_csv("../data/clean/master.csv", index=False)
match.to_csv("../data/clean/match.csv", index=False)