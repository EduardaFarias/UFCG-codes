def multiplica_lista(n,lista):
    nova_lista = []
#esse primeiro for remete a quantas vezes o for vai passar o segundo for
    for tamanho in range(n):
        for item in range(len(lista)):
            nova_lista.append(lista[item])
    return nova_lista  

assert multiplica_lista(2,['joao', 'pedro']) == ['joao', 'pedro', 'joao', 'pedro']


