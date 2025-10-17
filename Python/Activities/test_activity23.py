def test_sum(numlist):
    sum = 0
    for i in numlist:
        sum=sum+i
    
    assert sum == 55
    