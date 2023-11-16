# PROG1 - UFCG
# ALUNA: EDUARDA FARIAS |  DATA:21/03/2022  | MATRÍCULA: 121110641
# EXERCÍCIO: Programa que recebe uma quatidade N de palavras e verifica
#            se a primeira palavra é menor que a última ou vice versa

N = int(input())
lista = []

for palavra in range(N):
    palavra = input()
    lista.append(palavra)

menor_palavra = lista[0] if len(lista[0]) < len(lista[-1]) else lista[-1]

print(f"A menor palavra dos extremos é: {menor_palavra}")





