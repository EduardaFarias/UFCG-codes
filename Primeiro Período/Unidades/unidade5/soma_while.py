#print("Digite uma sequência de valores terminada por zero.")
#soma = 0

#valor = 1
#while valor != 0:
#    valor = int(input("Digite um valor a ser somado:"))
#   soma = soma + valor
#print(soma)

#tamanho =  int(input("Digite o tamanho da sequência de números:"))

#produto = 1
#i = 0

#while i < tamanho:
#    valor = int(input("Digite um valor a ser multiplicado:"))
#    produto = produto * valor
#   i = i + 1
#print(i)
#print("O produto dos valores digitados é:", produto)

decrescente = True 
anterior = int(input("Digite o primeiro número da sequência: "))
valor = 1

while valor != 0 and decrescente:
    valor = int(input("Digite o próximo número da sequência =: "))  
        if valor > anterior:
            decrescente = False
            anterior = valor

    if decrescente:
        print("A sequência está em ordem descresente :-)")
    else:
        print("A sequência não está em ordem :-(")
