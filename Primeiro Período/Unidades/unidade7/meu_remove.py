def meu_remove(lista, e):       # Retira os iguais
    for i in range(len(lista)-1, -1, -1):  
        if lista[i] == e:
            lista.pop(i)


lista = [1, 2, 2, 3]
meu_remove(lista, 2)
assert lista == [1, 3]

lista2 = [3, 3 ,3]
meu_remove(lista2, 3)
assert lista2 == []
