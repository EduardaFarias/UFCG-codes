def elemento_faltando(lista1, lista2):
    soma1 = 0
    soma2 = 0
    for valores1 in lista1:
        soma1 += int(valores1)
    for valores2 in lista2:
        soma2 += int(valores2)
    return soma2 - soma1

lista1 = [1, 2, 3, 4]
lista2 = [1, 2, 3, 4, 5]
print(elemento_faltando(lista1, lista2))
