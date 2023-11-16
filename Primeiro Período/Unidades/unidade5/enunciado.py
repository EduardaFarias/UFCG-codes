# Escreva um programa que leia e some números inteiros positivos. Quando o primeiro valor 
#negativo for lido, o programa deve parar e imprimir a soma e a média dos valores:

soma = 0
conta = 0

while True:
    num = int(input()) #Esse dado da entrada vai ser convertido em inteiro e salvo na variável num
    soma += num #vai atualizar soma para o número que foi posto no input 
    conta += 1 #vai adicionar 1 em conta
    if num < 0: break #se o número não for negativo, o laço se repete 
media = soma / conta
print(soma, media)
