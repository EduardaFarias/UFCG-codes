def bubblesort(dados):
    while True:
        swapped = False
        for i in range(0, len(dados) - 1):
            if dados[i] > dados[i+1]:
                temp = dados[i]
                dados[i] = dados[i + 1]
                dados[i + 1] = temp
                swapped = True
        if not swapped: return 

lista = [3,9,1,2]
bubblesort(lista)
assert lista == [1,2,3,9]
