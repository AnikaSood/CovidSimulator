import numpy as np
from matplotlib import pyplot as plt 
import copy
import matplotlib.patches as mpatches
def start(arrayOfCurrentDemographic,changeMatrix,numberOfPeriods=100,newVaccineRate=.55,vaccineIntroMonth=0,debug=False):
    completeDemographic = copy.deepcopy(arrayOfCurrentDemographic)
    # keep a record of old stats here
    # arrayOfCurrentDemographic = [dead, infected, not infected, vaccinated] 
    # changeMatrix =[[3][3][3][3]] probability of one state to another
    for period in range(numberOfPeriods):

        arrayOfOldDemographic = copy.deepcopy(arrayOfCurrentDemographic)

        arrayOfCurrentDemographic = generalMatrixCalculator(arrayOfOldDemographic,changeMatrix)
        if debug:
            calculateTotalPeople(arrayOfCurrentDemographic)
            print("arrayOfOldDemographic\n",arrayOfOldDemographic)
            print("completeDemographic\n",completeDemographic)
            print("changeMatrix is:\n", changeMatrix)
        if period != 0:
            completeDemographic = np.vstack((completeDemographic,arrayOfOldDemographic))
            plt.title("Exit me to see the next iteration") 
            plt.xlabel("Time") 
            plt.ylabel("People") 
            #print(len(list(completeDemographic.transpose()[0])), "and ", list(completeDemographic.transpose()[0]),completeDemographic.transpose())
            plt.plot(np.arange(len(list(completeDemographic.transpose()[0]))),completeDemographic.transpose()[0],"-b") 
            plt.plot(np.arange(len(list(completeDemographic.transpose()[1]))),completeDemographic.transpose()[1],"-r") 
            plt.plot(np.arange(len(list(completeDemographic.transpose()[2]))),completeDemographic.transpose()[2],"-m") 
            plt.plot(np.arange(len(list(completeDemographic.transpose()[3]))),completeDemographic.transpose()[3],"-g") 
            red_patch = mpatches.Patch(color='red', label='total number infected')
            blue_patch = mpatches.Patch(color='blue', label='total number dead')
            magenta_patch = mpatches.Patch(color='magenta', label='total number not infected and not vaccinated')
            green_patch = mpatches.Patch(color='green', label='total number vaccinated and alive')
            plt.legend(handles=[red_patch,blue_patch,magenta_patch,green_patch])
            plt.show()
        print("complete history of all people\n",completeDemographic)
        if period == vaccineIntroMonth:
            changeMatrix[2][2] = 1.0 - (changeMatrix[2][0]+changeMatrix[2][1]+changeMatrix[2][3])
            changeMatrix[3][3] = 1.0 - (changeMatrix[3][0]+changeMatrix[3][1])
        
    
def calculateTotalPeople(matrix):
    total = 0
    for x in matrix:
        total += x
    if int(total) != 1500:
        print("ahhhhhhhhhhhhhhhhhhhhhhhhh")
    print("the total number of people: ",total)



def generalMatrixCalculator(arrayOfOldDemographic,changeMatrix):
    # arrayOfCurrentDemographic * changeMatrix
    arrayOfNewDemographic = np.dot(arrayOfOldDemographic,changeMatrix)
    return arrayOfNewDemographic


arrayOfCurrentDemographic = np.array([100, 400, 900, 100],dtype=float)
changeMatrix = np.array([[1, 0, 0, 0], # dead -> dead, infected, not infected, vaccinated
             [.05, .4, 0, .55], # infected -> dead, infected, not infected, vaccinated
             [.01, .19, .6, .2], # not infected -> dead, infected, not infected, vaccinated
             [0, 0.08, 0, 0.92]],dtype=float) # vaccinated -> dead, infected, not infected, vaccinated
# mortality rate = changeMatrix[1][0]
# recovery rate = changeMatrix[1][3]
# regular death rate (no virus) = changeMatrix[2][0] and changeMatrix[3][0]
# infection rate = changeMatrix[2][1]
# vaccination rate = changeMatrix[2][3]
# vaccine preventability rate = changeMatrix[3][1]

if __name__ == "__main__":
    inputs = input("Do you want to enter manual inputs? (Y/N)")
    if inputs == "Y" or inputs == "y":
        print("All inputs should be less than 1")
        changeMatrix[1][0] = input("What is the mortality rate? (enter in decimal form):")
        changeMatrix[1][3] = input("What is the recovery rate? (enter in decimal form):")
        while changeMatrix[1][3] >= 1.0-changeMatrix[1][0]:
            changeMatrix[1][3] = input("What is the recovery rate? (enter in decimal form) must be less than (1-mortality rate):")
        changeMatrix[2][0] = input("What is the normal death rate from without the virus? (enter in decimal form):")
        changeMatrix[3][0] = copy.deepcopy(changeMatrix[2][0])
        changeMatrix[2][1] = input("What is the infection rate? (enter in decimal form):")
        changeMatrix[1][1] = 1.0 - (changeMatrix[1][0]+changeMatrix[1][3])
        changeMatrix[2][2] = 1.0 - (changeMatrix[2][0]+changeMatrix[2][1]+changeMatrix[2][3])
        changeMatrix[3][3] = 1.0 - (changeMatrix[3][0]+changeMatrix[3][1])
    inputs = input("Do you want to enter manual vaccine inputs? (Y/N)")
    if inputs == "Y" or inputs == "y":
        print("All inputs should be less than 1")
        newVaccineRate = float(input("What is the vaccination rate? (enter in decimal form):"))
        while newVaccineRate >= 1.0-(changeMatrix[2][0]+changeMatrix[2][1]):
            newVaccineRate = input("What is the vaccination rate? (enter in decimal form): must be less than 1-(normal death rate + infection rate)")
        changeMatrix[3][1] = float(input("What is the preventability of the vaccine rate? (enter in decimal form):"))
        while changeMatrix[3][1] >= 1.0-changeMatrix[3][0]:
            changeMatrix[3][1] = input("What is the preventability of the vaccine rate? (enter in decimal form) must be less than (1-mortality rate):")
        vaccineIntroMonth = int(input("After what month is the vaccine introduced? (enter an integer)"))
        changeMatrix[2][3] = 0.0
        changeMatrix[3][1] = 0.0
        changeMatrix[1][1] = 1.0 - (changeMatrix[1][0]+changeMatrix[1][3])
        changeMatrix[2][2] = 1.0 - (changeMatrix[2][0]+changeMatrix[2][1]+changeMatrix[2][3])
        changeMatrix[3][3] = 1.0 - (changeMatrix[3][0]+changeMatrix[3][1])
    else:
        newVaccineRate = changeMatrix[2][3]
        vaccineIntroMonth = 0
    numberOfPeriods = 100
    numberOfPeriods = int(input("How many months do you want to run for? (enter a number)"))

    start(arrayOfCurrentDemographic,changeMatrix,numberOfPeriods,newVaccineRate,vaccineIntroMonth)
    # dead people do not count for vaccinated
    # people who recovered are considered vaccinated
    # one cannot get covid twice