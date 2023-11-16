# PROG1 - UFCG
# ALUNA: EDUARDA FARIAS |  DATA:21/03/2022  | MATRÍCULA: 121110641
# EXERCÍCIO: Programa que verifica se b é divisor de três numeros

b = int(input())

while True:
    contador = 0
    for i in range(3):
        numero = int(input())
        if numero % b == 0:
            contador += 1 
    if contador == 2:
        print("Quase lá, tente novamente!")
    elif contador <= 1:
        print("Tem que melhorar, tente novamente!")
    else:
        print("Acertou! Parabéns!")
        break
        

