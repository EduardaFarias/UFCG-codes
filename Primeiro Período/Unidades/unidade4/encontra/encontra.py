N = int(input())

sequencia = input().split()

for num in sequencia:
    if int(num) == N:  
        print("sim")
        break
else:
    print("não")
       

