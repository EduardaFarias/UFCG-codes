# Valores coincidentes: Escreva um programa que identifica valores coincidentes
# em duas listas de inteiros. Duas listas têm valores coincidentes se em uma 
# mesma posição, ambas têm o mesmo valor. Por exemplo, as listas [7, 1, 19, 22]
# e [5, 1, 15, 12, 19, 22] têm um único valor coincidente: o valor 1 na segunda 
# posição da lista
# ENTRADA: O programa deve ler as duas listas de números da entreada, sendo uma
# lista em cada linha

#SAÍDA: A saída deve indicar um dos valores coincidentes e a posição em que foi
# encontrado nas palavras por linha da saída. Na última linha da saídadeve ser 
# indicado o total de letras coincidentes e a porcentagem (arredondada para zero
# casas decimais) de valores coincidentes em relação ao total de números das duas
# palavras,(observe que o valor máximo será 50%, caso todos o valores coincidam)

listaDeInteiros1  = list(map(int, input().split()))             
listaDeInteiros2  = list(map(int, input().split()))  

tamanhoListDeInteiros1 = len(listaDeInteiros1)
tamanhoListDeInteiros2 = len(listaDeInteiros2)

valoresCoincidentes = 0
i = 0
while i < tamanhoListDeInteiros1 and tamanhoListDeInteiros2:
    if listaDeInteiros1[i] == listaDeInteiros2[i]:
        print(f"i = {i}: {listaDeInteiros1[i]}")
        valoresCoincidentes += 1
    i += 1
porcentagem = (valoresCoincidentes / (tamanhoListDeInteiros1 + tamanhoListDeInteiros2)) * 100

print(f"Valores coincidentes: {valoresCoincidentes} ({porcentagem:.0f}%)")
             

    
