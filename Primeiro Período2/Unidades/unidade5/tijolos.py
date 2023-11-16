soma = 0
cont = 0
fornada_maiores = 0
lista = []

while(soma < 1000):
    fornada = int(input())
    soma += fornada
    cont += 1
    lista.append(fornada)

    media = soma / cont 
for num in lista:
    if num > media:
        fornada_maiores += 1
print(soma)
print(f"{media:.2f}")
print(fornada_maiores)