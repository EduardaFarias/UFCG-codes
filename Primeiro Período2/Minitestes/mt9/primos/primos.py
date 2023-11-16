def seleciona_primos(lista):
    lista_nova = []
    for num in lista:
       divisores = 0
       if num > 0:
            for i in range(1, num+1):
                if num % i == 0:
                    divisores += 1
            if divisores == 2:
                lista_nova.append(num)
    return lista_nova

lista = [3, 6, 9, 12, 15, 18, 19, 21]
assert seleciona_primos(lista) == [3, 19]
assert lista == [3, 6, 9, 12, 15, 18, 19, 21]
