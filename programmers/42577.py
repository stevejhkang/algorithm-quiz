def solution(phone_book):
    answer = True
    phone_book = sorted(phone_book, key=(lambda x: len(x)))
    for i, item in enumerate(phone_book):
        for j in range(0, i):
            if item.find(phone_book[j])==0:
                return False

    return answer
