#%%
import pandas as pd

class ETL():

    # Constructor to initialize a class list of dataframes
    def __init__(self):
        self.datasets = []

    # Loads the data into the datasets list
    def load_data(self, master_filename, match_filename):
        #TO DO - Handle exceptions

        # LOAD MASTER DATASET
        self.datasets.append(pd.read_csv(master_filename, 
            error_bad_lines=False, engine='python', warn_bad_lines=False))

        # LOAD MATCH DATASET
        self.datasets.append(pd.read_csv(match_filename, 
            error_bad_lines=False, engine='python', warn_bad_lines=False))

        #print("\nData loaded successfully.\n")

    # Changes all datatypes to strings for comparison
    def chtype(self):
        for i in range(len(self.datasets)):
            self.datasets[i] = self.datasets[i].astype('str')
    
    # Drops useless columns (FIRM_NAME, OFFICE_NAME) as they
    # are always the same value
    def drop_cols(self):
        for i in range(len(self.datasets)):
            self.datasets[i] = \
                self.datasets[i].drop(['FIRM_NAME', 'OFFICE_NAME'], axis=1)

    # Cleans the data with its inner functions
    def clean_data(self):

        # Replaces all the 'nan' strings that
        # occured during datatype transformation
        def replace_nan():
            for i in range(len(self.datasets)):
                self.datasets[i] = self.datasets[i].replace('nan', '')
        
        # Replaces all the trailing '.0's that occured
        # after transforming float64 types to string
        def replace_float():
            for i in range(len(self.datasets)):
                self.datasets[i] = \
                    self.datasets[i].replace('\.0', '', regex=True)

        # Combines all the address fields into one
        def combine_cols():
            for i in range(len(self.datasets)):
                self.datasets[i]['ADDRESS'] = \
                self.datasets[i]['ADDRESS_LINE_1'] + " " + \
                self.datasets[i]['ADDRESS_LINE_2'] + " " + \
                self.datasets[i]['ADDRESS_LINE_3']

                self.datasets[i]['ADDRESS'] = \
                    self.datasets[i]['ADDRESS'].str.strip()

                self.datasets[i] = \
                    self.datasets[i].drop([\
                'ADDRESS_LINE_1', \
                'ADDRESS_LINE_2', \
                'ADDRESS_LINE_3'], \
                axis=1)

        # Renames the columns for convenience
        def rename_cols():
            for i in range(len(self.datasets)):
                self.datasets[i].rename(columns={\
                'POSTAL_CODE_1': 'ZIP_1', \
                'POSTAL_CODE_2': 'ZIP_2'\
                }, inplace=True)

        # Reordered the columns similar to the original
        def reorder_cols():
            for i in range(len(self.datasets)):
                self.datasets[i] = self.datasets[i][[\
                'LAST_NAME', 'MIDDLE_NAME', 'FIRST_NAME', \
                'EMAIL_ADDRESS', 'BUSINESS_PHONE', 'ADDRESS', \
                'CITY', 'STATE_PROVINCE', 'ZIP_1', 'ZIP_2', \
                'COUNTRY_ID', 'CRD_NUMBER', 'CONTACT_ID']]

        replace_nan()
        combine_cols()
        rename_cols()
        reorder_cols()
        replace_float()

    def standardize(self):
        # TO DO - standardize & validate fields
        print("FINISH ME!")
        
    # Returns the pandas dataframes
    def getData(self):
        #           (master)          (match)
        return self.datasets[0], self.datasets[1]

    def toCSV(self):
        self.datasets[0].to_csv("../data/clean/master.csv", index=False)
        self.datasets[1].to_csv("../data/clean/match.csv", index=False)


#%%
dirty_master = pd.read_csv('../data/dirty/master.csv', 
            error_bad_lines=False, engine='python', warn_bad_lines=False)
dirty_match = pd.read_csv('../data/dirty/match.csv', 
            error_bad_lines=False, engine='python', warn_bad_lines=False)
#%%
etl = ETL()
etl.load_data("../data/dirty/master.csv", "../data/dirty/match.csv")
etl.chtype()
etl.drop_cols()
etl.clean_data()
etl.toCSV()
master, match = etl.getData()

#%%
dirty_master.head()

#%%
master.head()

