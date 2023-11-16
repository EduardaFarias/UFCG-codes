def my_extend(lista1, lista2):
    for elemento in lista2:         #Para cada elemento da lista 2, eu estou adicionando
        lista1.append(elemento)     #cada um deles(por meio do append) na lista1


lista1 = [1, 4, 6, 7]
lista2 = [8, 5, 3]

my_extend(lista1,lista2)

assert lista1 == [1, 4, 6, 7, 8, 5, 3]
assert lista2 == [8, 5, 3]
