# PROG1 - UFCG
# ALUNA: EDUARDA FARIAS |  DATA:21/03/2022  | MATRÍCULA: 121110641
# EXERCÍCIO: Programa que vai lendo palavras até a quantidade de consoantes
#            em uma palavra seja maior que a de vogais
palavrasLidas = 0 
while True:
    consoantes = 0
    vogais = 0
    palavra = input()
    palavrasLidas += 1
    for i in range(len(palavra)):
        if palavra[i] in "aeiouAEIOU":
            vogais += 1
        else:
            consoantes += 1
    if consoantes > vogais:
        break
print(palavrasLidas)