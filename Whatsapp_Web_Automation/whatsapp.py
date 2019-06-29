# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

#chrome driver location
driver=webdriver.Chrome("C:/Users/Tharun Achanta/Downloads/chromedriver.exe")

driver.get("https://web.whatsapp.com")

wait = WebDriverWait(driver,600)
#name of person to whome we send message
target='"Kranthi"'
#message
string ="Virus in your Whatsapp"
x_arg = '//span[contains(@title,' + target + ')]' 

group_title = wait.until(EC.presence_of_element_located((By.XPATH, x_arg))) 

group_title.click() 
#inp_xpath = '//div[@class="input"][@dir="auto"][@data-tab="1"]' 

input_box = driver.find_element_by_class_name('_1Plpp')


for i in range(200):  
     input_box.send_keys(string + Keys.ENTER)
     
