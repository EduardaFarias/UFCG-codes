N = int(input())
antes = 0
entre = 0
depois = 0

sequencia = []
for num in range(N):
    sequencia.append(int(input()))


print("---")

a = int(input())
b = int(input())
if a < b:
    for num in sequencia:
        if num < a:
            antes+= 1
        elif a < num < b:
            entre +=1
        elif b < num:
            depois += 1
    print(f"{antes} antes")
    print(f"{entre} entre") 
    print(f"{depois} depois")


