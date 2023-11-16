def remove_maiores(lista):
    if lista == []:
        return None
    maior = lista[0]
    for j in lista:
        if j > maior:
            maior = j
    for i in range(len(lista) -1, -1, -1):
        if lista[i] == maior:
            lista.pop(i)
    return None
    


lista1 = []
assert remove_maiores(lista1) == None
assert lista1 == []
