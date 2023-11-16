# PROG1 - UFCG
# ALUNA: EDUARDA FARIAS |  DATA:20/03/2022  | MATRÍCULA: 121110641
# EXERCÍCIO: Programa que determina maior sequência crescente na lista

# algoritmo minimo / maximo
#def meumax(valores):
#    maior = valorres[0]
#    for v in valores:
#        if v > maior:
#            maior = v #troca 
#    return maior 
#num = [10, 2, 5, 2, 7, 8, 90, 1]

#print(meumax(num))


def maior_sequencia(sequencia):
    seq = [sequencia[0]]
    maior = []
    if len(sequencia) == 1:
        return sequencia

    for i in range(1, (len(sequencia))):
        if sequencia[i -1] < sequencia[i]:
            seq.append(sequencia[i])
        if sequencia[i] <= sequencia[i-1]:
            seq = [sequencia[i]]
        if(len(maior) <= len(seq)):
            maior = seq
    return maior

assert maior_sequencia([1, 2, 3, 4, 2, 0, 1]) == [1, 2, 3, 4]
assert maior_sequencia([7, 6, 2, 9, 10]) == [2, 9, 10]
assert maior_sequencia([7, 8, 9, 1, 2, 3, 0]) == [1, 2, 3]



