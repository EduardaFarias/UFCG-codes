def busca(sequencia, numero):
    for i in range(len(sequencia)):
        if sequencia[i] == numero:
            return i
    return -1





sequencia = [8, 9, 2, 3, 6, 10, 7, 9]
assert busca(sequencia, 6) == 4
assert busca(sequencia, 4) == -1
assert busca(sequencia, 9) == 1
 
