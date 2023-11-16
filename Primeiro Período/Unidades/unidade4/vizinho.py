lista = [1, 1, 2, 3, 8, 9, 9, 4]
       # 0  1  2  3  4  5  6  7

ocorrencias = 0
for i in range(len(lista)-1):
    if int(lista[i]) == int(lista[i+1]): #lista[i+1], representa o acesso ao próximo item
        ocorrencias += 1
print(ocorrencias)

#poderia resolver também com um break(a questão do len(lista)), porém com limitações
#como por exemplo, se tivesse números repetidos
# if lista[i] ==  lista[-1]
#   break
