#Para alterarmos listas, podemos usar apenas duas funções:

#s.append(x); Adicionar um item no fim da lista

#s.pop() ou s.pop(i)

lista = [1, 2]

lista.append(5) #adiciona 5 na lista
print(lista)

lista.pop() #pop sem parâmetro remove o último elemento da lista 

removido = lista.pop(5) # o elemento saiu da lista, e foi armazenado em removido
lista.pop(5) #pop com parâmetro, retira por índices 

lista = [3, 6, 8, 9, 3, 1] 
lista.pop(2) #ele vai tirar lista[i] == 8; ele vai retirar o "8" da lista 
