def encontra_menores(num,lista):
    n_existe = -1
    for numero in lista:
        if numero < num:
            return numero
    return n_existe
assert encontra_menores(3, [2,1,1,1,1,1]) == 2



