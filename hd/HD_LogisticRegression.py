#!/usr/bin/env python
# coding: utf-8

# In[21]:


import numpy as np
import pandas as pd

import matplotlib.pyplot as plt
import seaborn as sns

from datetime import datetime

from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import GridSearchCV,train_test_split,cross_val_score
from sklearn.metrics import classification_report,confusion_matrix
from sklearn.linear_model import LogisticRegression
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.svm import SVC
from sklearn.metrics import roc_curve, auc
import os
import warnings
import sys
warnings.filterwarnings('ignore')


# In[22]:


data=pd.read_csv('heart.csv')


# In[23]:


data=data.rename(columns={'age':'Age','sex':'Sex','cp':'Cp','trestbps':'Trestbps','chol':'Chol','fbs':'Fbs','restecg':'Restecg','thalach':'Thalach','exang':'Exang','oldpeak':'Oldpeak','slope':'Slope','ca':'Ca','thal':'Thal','target':'Target'})


# In[24]:


dataX=data.drop('Target',axis=1)
dataY=data['Target']


# In[25]:


X_train,X_test,y_train,y_test=train_test_split(dataX,dataY,test_size=0.2,random_state=42)


# In[26]:


inpt = [float(numeric_string) for numeric_string in sys.argv[1:]]

new_data_x = [inpt]
new_data_y = [0]
nd_x = pd.DataFrame(new_data_x, columns=data.columns[:-1])
nd_y = pd.DataFrame(new_data_y, columns=data.columns[13:])
X_test = X_test.append(nd_x, sort=False)
y_test = y_test.append(pd.Series([0]))


# In[27]:


#Normalization as the first process
# Normalize
X_train=(X_train-np.min(X_train))/(np.max(X_train)-np.min(X_train)).values
X_test=(X_test-np.min(X_test))/(np.max(X_test)-np.min(X_test)).values

X = X_test
X.columns


# In[28]:


from sklearn.decomposition import PCA
pca=PCA().fit(X_train)


# In[29]:


pca = PCA(n_components=8)
pca.fit(X_train)
reduced_data_train = pca.transform(X_train)


# In[30]:


pca = PCA(n_components=8)
pca.fit(X_test)
reduced_data_test = pca.transform(X_test)


# In[31]:


reduced_data_train = pd.DataFrame(reduced_data_train, columns=['Dim1', 'Dim2','Dim3','Dim4','Dim5','Dim6','Dim7','Dim8'])
reduced_data_test = pd.DataFrame(reduced_data_test, columns=['Dim1', 'Dim2','Dim3','Dim4','Dim5','Dim6','Dim7','Dim8'])
X_train=reduced_data_train
X_test=reduced_data_test


# In[32]:


def plot_roc_(false_positive_rate,true_positive_rate,roc_auc):
    plt.figure(figsize=(5,5))
    plt.title('Receiver Operating Characteristic')
    plt.plot(false_positive_rate,true_positive_rate, color='red',label = 'AUC = %0.2f' % roc_auc)
    plt.legend(loc = 'lower right')
    plt.plot([0, 1], [0, 1],linestyle='--')
    plt.axis('tight')
    plt.ylabel('True Positive Rate')
    plt.xlabel('False Positive Rate')
    plt.show()
    
def plot_feature_importances(gbm):
    n_features = X_train.shape[1]
    plt.barh(range(n_features), gbm.feature_importances_, align='center')
    plt.yticks(np.arange(n_features), X_train.columns)
    plt.xlabel("Feature importance")
    plt.ylabel("Feature")
    plt.ylim(-1, n_features)


# In[42]:


combine_features_list=[
    ('Dim1','Dim2','Dim3','Dim7', 'Dim8'),
    ('Dim7','Dim8','Dim1'),
    ('Dim4','Dim8','Dim5')
]


# In[43]:


parameters=[
{
    'penalty':['l1','l2'],
    'C':[0.1,0.4,0.5],
    'random_state':[0]
    },
]

for features in combine_features_list:
    X_train_set=X_train.loc[:,features]
    X_test_set=X_test.loc[:,features]
    
    gslog=GridSearchCV(LogisticRegression(),parameters,scoring='accuracy')
    gslog.fit(X_train_set,y_train)

    predictions=[
    (gslog.predict(X_train_set),y_train,'Train'),
    (gslog.predict(X_test_set),y_test,'Test'),
    ]
    
    print (predictions[1][0][-1])
    


# In[ ]:




