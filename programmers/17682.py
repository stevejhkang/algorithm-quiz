import re

def solution(dartResult):
    answer = 0
    regex=re.compile('[SDT][*|#]|[SDT]')
    bonus=regex.findall(dartResult)
    regex2=re.compile("[0-9]*[0-9]")
    num=regex2.findall(dartResult)
    #regex3=re.compile("[*|#]")
    #option=regex3.findall(dartResult)
    num=list(map(int,num))
    print(num)
    
    tempResult=[]
    for i in range(0,3):
        if bonus[i]=='S':
            tempResult.append(num[i])
        if bonus[i]=='D':
            tempResult.append(pow(num[i],2))
        if bonus[i]=='T':
            tempResult.append(pow(num[i],3))
        if bonus[i]=='S*':
            if i==0:#첫번째일땐 그냥 두배
                tempResult.append(2*num[i])
            else:#두번째 이상일땐 전 값도 두배
                tempResult[i-1]=tempResult[i-1]*2
                tempResult.append(pow(num[i],1)*2)
        if bonus[i]=='D*':
            if i==0:#첫번째일땐 그냥 두배
                tempResult.append(pow(num[i],2)*2)
            else:#두번째 이상일땐 전 값도 두배
                tempResult[i-1]=tempResult[i-1]*2
                tempResult.append(pow(num[i],2)*2)
        if bonus[i]=='T*':
            if i==0:#첫번째일땐 그냥 두배
                tempResult.append(pow(num[i],3)*2)
            else:#두번째 이상일땐 전 값도 두배
                tempResult[i-1]=tempResult[i-1]*2
                tempResult.append(pow(num[i],3)*2)
        if bonus[i]=='S#':
            tempResult.append(pow(num[i],1)*(-1))
        if bonus[i]=='D#':
            tempResult.append(pow(num[i],2)*(-1))
        if bonus[i]=='T#':
            tempResult.append(pow(num[i],3)*(-1))
            
    #print(bonus)
    
    #print(tempResult)
    answer=sum(tempResult)
    return answer
