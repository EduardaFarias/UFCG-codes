# PROG1 - UFCG
# ALUNA: EDUARDA FARIAS |  DATA:28/03/2022  | MATRÍCULA: 121110641
# EXERCÍCIO: Programa que recebe uma sequência de números e verifica 
#            a quantidade de sequência que possuem todos os ímpares
#            ímpares = 1, 3, 5, 7, 9   
sequencia = input().split()
impares = [1, 3, 5, 7, 9]
achados = 0
for num in sequencia:
    achou = [False] * 5
    for algarismo in num:
        for i in range(len(impares)):  
            impar = impares[i]
            if impar == int(algarismo):
                achou[i] = True
                break
    if achou == [True] * 5:
        achados += 1

print(achados)

