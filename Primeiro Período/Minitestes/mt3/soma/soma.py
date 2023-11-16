n1 = int(input())
n2= int(input()) 
n3 = int(input())
soma = n1 + n2 + n3
soma1 = n2 + n3
soma2 = n1 + n3
soma3 = n1 + n2
if n1 < 0 or n1 % 2 == 0:
    print(f"{soma1}")

if n2 < 0 or n2 % 2 == 0:
    print(f"{soma2}")

if n3 < 0 or n3 % 2 == 0:
    print(f"{soma3}")

else:
    print(f"{soma}")

if soma > 100:
    print("A soma ultrapassou o limite")
